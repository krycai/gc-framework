## 1.什么叫混合切分
   项目组中如果有水平切分，那项目组里的开发方式就叫混合切分。或者项目组里就是单纯的垂直切分。
   
## 2.在项目组中，切分后的库从哪里而来？
  在开发中是基于原有库创建出来，并且原有库和切分后的库是数据表的设计是保持一致的。dm_order1,dm_order2,dm_order3这些库是需要和dm_order的设计保持一致的！！！！
  
  附注：所以，切分后的库例如dm_order1,dm_order2,dm_order3这些都是有数据库维护团队创建出来的。
  
## 3.搭建mycat的核心配置文件有哪些？
  schem.xml 配置参数：逻辑库，逻辑表，数据节点。节点主机
  
  ![](https://img-blog.csdnimg.cn/20210406095223470.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2JhaWR1XzI4MDY4OTg1,size_16,color_FFFFFF,t_70)
  
  rule.xml：分片规则
  
  server.xml：连接mycat的用户信息（账号和密码）
  
  这里是使用中间件做数据切分，感兴趣的小伙伴还可以了解一下mysql的分库分表高可用方案
  
## 4.mycat分库可以分成100个库吗？
  我们目前项目组分的是3个库，我们说一般数据量大的话我们使用的是mycat中间件进行分片处理，如果更大的话，我们可以使用oracle数据库，
  如果更大的话可以使用hadoop或是云存储数据，不需要mycat作为工具手段。**衡量的标准是项目有没有对应的硬件设备**。 如果没有，基本就是使用mysql 
  因为搭建一套云环境或者大数据的环境基本都是超大型的公司。比如大数据中的所有的技术，例如hbase 或者是一大堆的服务器 一大堆的网络路由设备 或是私有云。
  或者是一大堆的数据库运维实施人员都是成本
  
## 5.进行库表拆分时，拆分规则怎么取舍？
   
   1.不存在热点数据时，则使用连续分片
   
   2.存在热点数据时，使用离散分片或者是综合分片
   
   3.离散分片暂时迁移比较麻烦（但是mycat给出了数据迁移的脚本，虽然现在还是不是很完美），综合分片占用总机器数量多
   
## 6. Mycat中全局ID方案有哪些？程序自定义全局ID的方案有哪些？
   
   1.mycat的全局id方案
   
   （1）本地文件方式
   
       sequnceHandlerType = 0
       
       配置sequence_conf.properties
       
       使用next value for MYCATSEQ_XXX
   
   （2）数据库方式
   
       sequnceHandlerType = 1
       
       配置sequence_db_conf.properties
       
       使用next value for MYCATSEQ_XXX或者指定autoIncrement
   
   （3）本地时间戳方式
   
       ID= 64 位二进制 (42(毫秒)+5(机器 ID)+5(业务编码)+12(重复累加)
       
       sequnceHandlerType = 2
       
       配置sequence_time_conf.properties
       
       指定autoIncrement
   
   2. 程序方式
   
   （1）Snowflake
   
   （2）UUID
   
   （3）Redis
   
## 7.mycat是怎样实现分库分表的？
   
   mycat里面通过定义路由规则来（路由规则里面会定义分片字段，以及分片算法）。分片算法有多种，你所说的hash是其中一种，还有取模、按范围分片等等。
   在mycat里面，会对所有传递的sql语句做路由处理（路由处理的依据就是表是否分片，如果分片，那么需要依据分片字段和对应的分片算法来判断sql应该传递到哪一个、或者哪几个、
   又或者全部节点去执行）。
   
## 8.mycat适用于哪些场景？相对于海量存储的Nosql的适用场景又如何？
   
   数据量大到单机hold不住，而又不希望调整架构切换为NoSQL数据库，这个场景下可以考虑适用mycat。当然，使用前也应该做规划，哪些表需要分片等等。
   另外mycat对跨库join的支持不是很好，在使用mycat的时候要注意规避这种场景。
   
## 9.Mycat 中，旧系统数据如何迁移到 Mycat 中？
   
   答：旧数据迁移目前可以手工导入，在 mycat 中提取配置好分配规则及后端分片数据库，然后通过 dump或 loaddata 方式导入，后续 Mycat 就做旧数据自动数据迁移工具。
   
## 10.Mycat 如何对旧分片数据迁移或扩容，支持自动扩容么？
   
   答：目前除了一致性 hash 规则分片外其他数据迁移比较困难，目前暂时可以手工迁移，未提供自动迁移方案。
   
   
   