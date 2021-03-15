## 1. string
介绍 ：string 数据结构是简单的 key-value 类型。虽然 Redis 是用 C 语言写的，但是 Redis 并没有使用 C 的字符串表示，
而是自己构建了一种 **简单动态字符串**（simple dynamic string，SDS）。相比于 C 的原生字符串，Redis 的 SDS 不光可以保存**文本数据还可以保存二进制数据**，
并且获取字符串长度复杂度为 O(1)（C 字符串为 O(N)）,除此之外,Redis 的 SDS API 是安全的，不会造成缓冲区溢出。

常用命令: set,get,strlen,exists,dect,incr,setex 等等。

应用场景 ：一般常用在需要计数的场景，比如用户的访问次数、热点文章的点赞转发数量等等。

1)、普通字符串处理

      127.0.0.1:6379> set key value #设置 key-value 类型的值
      OK
      127.0.0.1:6379> get key # 根据 key 获得对应的 value
      "value"
      127.0.0.1:6379> exists key  # 判断某个 key 是否存在
      (integer) 1
      127.0.0.1:6379> strlen key # 返回 key 所储存的字符串值的长度。
      (integer) 5
      127.0.0.1:6379> del key # 删除某个 key 对应的值
      (integer) 1
      127.0.0.1:6379> get key
      (nil)

2)、批量处理

     127.0.0.1:6379> mset key1 value1 key2 value2 # 批量设置 key-value 类型的值
     OK
     127.0.0.1:6379> mget key1 key2 # 批量获取多个 key 对应的 value
     1) "value1"
     2) "value2"

3)、计数器（字符串的内容为整数的时候可以使用）

     
    127.0.0.1:6379> set number 1
    OK
    127.0.0.1:6379> incr number # 将 key 中储存的数字值增一
    (integer) 2
    127.0.0.1:6379> get number
    "2"
    127.0.0.1:6379> decr number # 将 key 中储存的数字值减一
    (integer) 1
    127.0.0.1:6379> get number
    "1"

4）、过期：

     127.0.0.1:6379> expire key  60 # 数据在 60s 后过期
     (integer) 1
     127.0.0.1:6379> setex key 60 value # 数据在 60s 后过期 (setex:[set] + [ex]pire)
     OK
     127.0.0.1:6379> ttl key # 查看数据还有多久过期
     (integer) 56

## 2. list
介绍 ：list 即是 **链表**。链表是一种非常常见的数据结构，特点是**易于数据元素的插入和删除并且且可以灵活调整链表长度，但是链表的随机访问困难**。
许多高级编程语言都内置了链表的实现比如 Java 中的 LinkedList，但是 C 语言并没有实现链表，所以 Redis 实现了自己的链表数据结构。

Redis 的 list 的实现为一个 **双向链表**，即可以支持反向查找和遍历，更方便操作，不过带来了部分额外的内存开销。

常用命令: rpush,lpop,lpush,rpop,lrange、llen 等。

应用场景: 发布与订阅或者说消息队列、慢查询。

1)、通过 rpush/lpop 实现队列：

    127.0.0.1:6379> rpush myList value1 # 向 list 的头部（右边）添加元素
    (integer) 1
    127.0.0.1:6379> rpush myList value2 value3 # 向list的头部（最右边）添加多个元素
    (integer) 3
    127.0.0.1:6379> lpop myList # 将 list的尾部(最左边)元素取出
    "value1"
    127.0.0.1:6379> lrange myList 0 1 # 查看对应下标的list列表， 0 为 start,1为 end
    1) "value2"
    2) "value3"
    127.0.0.1:6379> lrange myList 0 -1 # 查看列表中的所有元素，-1表示倒数第一
    1) "value2"
    2) "value3"
    
2)、通过 rpush/rpop 实现栈：

    127.0.0.1:6379> rpush myList2 value1 value2 value3
    (integer) 3
    127.0.0.1:6379> rpop myList2 # 将 list的头部(最右边)元素取出
    "value3"

![](https://snailclimb.gitee.io/javaguide/docs/database/Redis/images/redis-all/redis-list.png)

3)、通过 lrange 查看对应下标范围的列表元素：

    127.0.0.1:6379> rpush myList value1 value2 value3
    (integer) 3
    127.0.0.1:6379> lrange myList 0 1 # 查看对应下标的list列表， 0 为 start,1为 end
    1) "value1"
    2) "value2"
    127.0.0.1:6379> lrange myList 0 -1 # 查看列表中的所有元素，-1表示倒数第一
    1) "value1"
    2) "value2"
    3) "value3"

通过 lrange 命令，你可以基于 list 实现**分页查询**，性能非常高！

4)、通过 llen 查看链表长度：

    127.0.0.1:6379> llen myList
    (integer) 3
## 3. hash
介绍 ：hash 类似于 JDK1.8 前的 HashMap，内部实现也差不多(数组 + 链表)。不过，Redis 的 hash 做了更多优化。
另外，hash 是一个 string 类型的 field 和 value 的映射表，特别适合用于存储对象，后续操作的时候，你可以直接仅仅修改这个对象中的某个字段的值。
 比如我们可以 hash 数据结构来存储用户信息，商品信息等等。
 
常用命令： hset,hmset,hexists,hget,hgetall,hkeys,hvals 等。

应用场景: 系统中对象数据的存储。

    127.0.0.1:6379> hset userInfoKey name "guide" description "dev" age "24"
    OK
    127.0.0.1:6379> hexists userInfoKey name # 查看 key 对应的 value中指定的字段是否存在。
    (integer) 1
    127.0.0.1:6379> hget userInfoKey name # 获取存储在哈希表中指定字段的值。
    "guide"
    127.0.0.1:6379> hget userInfoKey age
    "24"
    127.0.0.1:6379> hgetall userInfoKey # 获取在哈希表中指定 key 的所有字段和值
    1) "name"
    2) "guide"
    3) "description"
    4) "dev"
    5) "age"
    6) "24"
    127.0.0.1:6379> hkeys userInfoKey # 获取 key 列表
    1) "name"
    2) "description"
    3) "age"
    127.0.0.1:6379> hvals userInfoKey # 获取 value 列表
    1) "guide"
    2) "dev"
    3) "24"
    127.0.0.1:6379> hset userInfoKey name "GuideGeGe" # 修改某个字段对应的值
    127.0.0.1:6379> hget userInfoKey name
    "GuideGeGe"

## 4. set
介绍 ： set 类似于 Java 中的 HashSet 。Redis 中的 set 类型是一种无序集合，集合中的元素没有先后顺序。当你需要存储一个列表数据，又不希望出现重复数据时，
set 是一个很好的选择，并且 set 提供了判断某个成员是否在一个 set 集合内的重要接口，这个也是 list 所不能提供的。可以基于 set 轻易实现交集、并集、差集的操作。
比如：你可以将一个用户所有的关注人存在一个集合中，将其所有粉丝存在一个集合。Redis 可以非常方便的实现如共同关注、共同粉丝、共同喜好等功能。
这个过程也就是求交集的过程。

常用命令： sadd,spop,smembers,sismember,scard,sinterstore,sunion 等。

应用场景: 需要存放的数据不能重复以及需要获取多个数据源交集和并集等场景

    127.0.0.1:6379> sadd mySet value1 value2 # 添加元素进去
    (integer) 2
    127.0.0.1:6379> sadd mySet value1 # 不允许有重复元素
    (integer) 0
    127.0.0.1:6379> smembers mySet # 查看 set 中所有的元素
    1) "value1"
    2) "value2"
    127.0.0.1:6379> scard mySet # 查看 set 的长度
    (integer) 2
    127.0.0.1:6379> sismember mySet value1 # 检查某个元素是否存在set 中，只能接收单个元素
    (integer) 1
    127.0.0.1:6379> sadd mySet2 value2 value3
    (integer) 2
    127.0.0.1:6379> sinterstore mySet3 mySet mySet2 # 获取 mySet 和 mySet2 的交集并存放在 mySet3 中
    (integer) 1
    127.0.0.1:6379> smembers mySet3
    1) "value2"

##5. sorted set
介绍： 和 set 相比，sorted set 增加了一个权重参数 score，使得集合中的元素能够按 score 进行有序排列，还可以通过 score 的范围来获取元素的列表。
有点像是 Java 中 HashMap 和 TreeSet 的结合体。

常用命令： zadd,zcard,zscore,zrange,zrevrange,zrem 等。

应用场景： 需要对数据根据某个权重进行排序的场景。比如在直播系统中，实时排行信息包含直播间在线用户列表，各种礼物排行榜，弹幕消息（可以理解为按消息维度的消息排行榜）等信息。

    127.0.0.1:6379> zadd myZset 3.0 value1 # 添加元素到 sorted set 中 3.0 为权重
    (integer) 1
    127.0.0.1:6379> zadd myZset 2.0 value2 1.0 value3 # 一次添加多个元素
    (integer) 2
    127.0.0.1:6379> zcard myZset # 查看 sorted set 中的元素数量
    (integer) 3
    127.0.0.1:6379> zscore myZset value1 # 查看某个 value 的权重
    "3"
    127.0.0.1:6379> zrange  myZset 0 -1 # 顺序输出某个范围区间的元素，0 -1 表示输出所有元素
    1) "value3"
    2) "value2"
    3) "value1"
    127.0.0.1:6379> zrange  myZset 0 1 # 顺序输出某个范围区间的元素，0 为 start  1 为 stop
    1) "value3"
    2) "value2"
    127.0.0.1:6379> zrevrange  myZset 0 1 # 逆序输出某个范围区间的元素，0 为 start  1 为 stop
    1) "value1"
    2) "value2"

## 6 bitmap
介绍 ： bitmap 存储的是连续的二进制数字（0 和 1），通过 bitmap, 只需要一个 bit 位来表示某个元素对应的值或者状态，key 就是对应元素本身 。
我们知道 8 个 bit 可以组成一个 byte，所以 bitmap 本身会极大的节省储存空间。

常用命令： setbit 、getbit 、bitcount、bitop

应用场景: 适合需要保存状态信息（比如是否签到、是否登录...）并需要进一步对这些信息进行分析的场景。比如用户签到情况、活跃用户情况、用户行为统计（比如是否点赞过某个视频）。

    # SETBIT 会返回之前位的值（默认是 0）这里会生成 7 个位
    127.0.0.1:6379> setbit mykey 7 1
    (integer) 0
    127.0.0.1:6379> setbit mykey 7 0
    (integer) 1
    127.0.0.1:6379> getbit mykey 7
    (integer) 0
    127.0.0.1:6379> setbit mykey 6 1
    (integer) 0
    127.0.0.1:6379> setbit mykey 8 1
    (integer) 0
    # 通过 bitcount 统计被被设置为 1 的位的数量。
    127.0.0.1:6379> bitcount mykey
    (integer) 2

1)、使用场景一：用户行为分析 很多网站为了分析你的喜好，需要研究你点赞过的内容

    # 记录你喜欢过 001 号小姐姐
    127.0.0.1:6379> setbit beauty_girl_001 uid 1

2)、使用场景二：统计活跃用户

使用时间作为 key，然后用户 ID 为 offset，如果当日活跃过就设置为 1

那么我该如果计算某几天/月/年的活跃用户呢(暂且约定，统计时间内只有有一天在线就称为活跃)，有请下一个 redis 的命令

    # 对一个或多个保存二进制位的字符串 key 进行位元操作，并将结果保存到 destkey 上。
    # BITOP 命令支持 AND 、 OR 、 NOT 、 XOR 这四种操作中的任意一种参数
    BITOP operation destkey key [key ...]

初始化数据：

     127.0.0.1:6379> setbit 20210308 1 1
     (integer) 0
     127.0.0.1:6379> setbit 20210308 2 1
     (integer) 0
     127.0.0.1:6379> setbit 20210309 1 1
     (integer) 0

统计 20210308~20210309 总活跃用户数: 1

    127.0.0.1:6379> bitop and desk1 20210308 20210309
    (integer) 1
    127.0.0.1:6379> bitcount desk1
    (integer) 1

统计 20210308~20210309 在线活跃用户数: 2

    127.0.0.1:6379> bitop or desk2 20210308 20210309
    (integer) 1
    127.0.0.1:6379> bitcount desk2
    (integer) 2

使用场景三：用户在线状态

对于获取或者统计用户在线状态，使用 bitmap 是一个节约空间效率又高的一种方法。

只需要一个 key，然后用户 ID 为 offset，如果在线就设置为 1，不在线就设置为 0。
