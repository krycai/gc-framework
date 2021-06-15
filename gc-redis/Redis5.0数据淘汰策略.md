## 一、参数设置
我们的redis数据库的最大缓存、主键失效、淘汰机制等参数都是**通过配置文件来配置**的。这个文件是我们的**redis.config**文件，我们的redis装在了/usr/local/redis目录下，所以配置文件也在这里。首先说明一下我使用的redis是5。也是目前最新的版本。

### 1、最大内存参数
![](https://pic1.zhimg.com/80/v2-646134395f65379e0f3b6b0d31c3a7a0_1440w.jpg)
### 2、内存淘汰策略
![](https://pic1.zhimg.com/80/v2-7ec5e55aeb286c7b31ddf49d108476cc_1440w.jpg)

不同于之前的版本，redis5.0为我们提供了八个不同的内存置换策略。很早之前提供了6种。

* （1）volatile-lru：从已设置过期时间的数据集中挑选最近最少使用的数据淘汰。

* （2）volatile-ttl：从已设置过期时间的数据集中挑选将要过期的数据淘汰。

* （3）volatile-random：从已设置过期时间的数据集中任意选择数据淘汰。

* （4）volatile-lfu：从已设置过期时间的数据集挑选使用频率最低的数据淘汰。

* （5）allkeys-lru：从数据集中挑选最近最少使用的数据淘汰

* （6）allkeys-lfu：从数据集中挑选使用频率最低的数据淘汰。

* （7）allkeys-random：从数据集（server.db[i].dict）中任意选择数据淘汰

* （8） no-enviction（驱逐）：禁止驱逐数据，这也是默认策略。意思是当内存不足以容纳新入数据时，新写入操作就会报错，请求可以继续进行，线上任务也不能持续进行，采用no-enviction策略可以保证数据不被丢失。

这八种大体上可以分为4中，**lru、lfu、random、ttl**。

## 二、淘汰机制的实现
### 1、删除失效主键
既然是淘汰，那就需要把这些数据给删除，然后保存新的。Redis 删除失效主键的方法主要有两种：

（1）**消极方法（passive way）**，在主键被访问时如果发现它已经失效，那么就删除它。
redis在实现GET、MGET、HGET、LRANGE等所有涉及到读取数据的命令时都会调用 expireIfNeeded，
它存在的意义就是在读取数据之前先检查一下它有没有失效，如果失效了就删除它。
 
    int expireIfNeeded(redisDb *db, robj *key) {
        //获取主键的失效时间
        long long when = getExpire(db,key);
        //假如失效时间为负数，说明该主键未设置失效时间（失效时间默认为-1），直接返回0
        if (when < 0) return 0;
        //假如Redis服务器正在从RDB文件中加载数据，暂时不进行失效主键的删除，直接返回0
        if (server.loading) return 0;
        /*假如当前的Redis服务器是作为Slave运行的，那么不进行失效主键的删除，因为Slave
        上失效主键的删除是由Master来控制的，但是这里会将主键的失效时间与当前时间进行
        一下对比，以告知调用者指定的主键是否已经失效了*/
        if (server.masterhost != NULL) {
            return mstime() > when;
        }
        /*如果以上条件都不满足，就将主键的失效时间与当前时间进行对比，如果发现指定的主键
        还未失效就直接返回0*/
        if (mstime() <= when) return 0;
        /*如果发现主键确实已经失效了，那么首先更新关于失效主键的统计个数，然后将该主键失
        效的信息进行广播，最后将该主键从数据库中删除*/
        server.stat_expiredkeys++;
        propagateExpire(db,key);
        return dbDelete(db,key);
    }

expireIfNeeded函数中调用的另外一个函数propagateExpire，这个函数用来在正式删除失效主键，并且广播告诉其他地方，
目的地有俩：AOF文件，将删除失效主键的这一操作以DEL Key的标准命令格式记录下来；
另一个就是发送到当前Redis服务器的所有Slave，同样将删除失效主键的这一操作以DEL Key的标准命令格式告知这些Slave删除各自的失效主键。

    void propagateExpire(redisDb *db, robj *key) {
        robj *argv[2];
        //shared.del是在Redis服务器启动之初就已经初始化好的一个常用Redis对象，即DEL命令
        argv[0] = shared.del;
        argv[1] = key;
        incrRefCount(argv[0]);
        incrRefCount(argv[1]);
        //检查Redis服务器是否开启了AOF，如果开启了就为失效主键记录一条DEL日志
        if (server.aof_state != REDIS_AOF_OFF)
            feedAppendOnlyFile(server.delCommand,db->id,argv,2);
        /*检查Redis服务器是否拥有Slave，如果是就向所有Slave发送DEL失效主键的命令，这就是
        上面expireIfNeeded函数中发现自己是Slave时无需主动删除失效主键的原因了，因为它
        只需听从Master发送过来的命令就OK了*/
        if (listLength(server.slaves))
            replicationFeedSlaves(server.slaves,db->id,argv,2);
        decrRefCount(argv[0]);
        decrRefCount(argv[1]);
    }
（2）**积极方法**（active way），周期性地探测，发现失效就删除。消极方法的缺点是，如果key 迟迟不被访问，就会占用很多内存空间，所以才有积极方式。

（3）**主动删除**：当内存超过maxmemory限定时，触发主动清理策略，该策略由**启动参数的配置**决定

主键具体的失效时间全部都维护在expires这个字典表中：

    typedef struct redisDb {
        dict *dict; //key-value
        dict *expires;  //维护过期key
        dict *blocking_keys;
        dict *ready_keys;
        dict *watched_keys;
        int id;
    } redisDb;

### 2、淘汰数据的量

既然是淘汰数据，那么淘汰多少合适呢？

为了避免频繁的触发淘汰策略，每次会淘汰掉一批数据，淘汰的数据的大小其实是和置换的大小来确定的，如果置换的数据量大，淘汰的肯定也多。

### 3、置换策略是如何工作

理解置换策略的执行方式是非常重要的，比如：

（1）客户端执行一条新命令，导致数据库需要增加数据（比如set key value）

（2）Redis会检查内存使用，如果内存使用超过maxmemory，就会按照置换策略删除一些key

（3）新的命令执行成功

https://zhuanlan.zhihu.com/p/102513831

