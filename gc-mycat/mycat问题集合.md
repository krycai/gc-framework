## 1、单表数据达到多少的时候会影响数据库的查询性能？为什么？
  一般mysql达到100w，就影响数据库的查询性能，如果命中索引，情况还好一点。
  
## 2、 主从复制机制的原理概述是怎样的？常见的存在形式有哪些？
  mysql主从复制是master将所有的事务操作写入到**binlog**,slave获取binlog读入自己的中继区，然后再进行执行。
  
## 3、 分库分表中解释一下垂直和水平2种不同的拆分？
  * 垂直拆分：是将单表，或者是有关联的表放在一个数据库，把原有的一个数据库拆分成若干个数据库。
  * 水平拆分：是将一个很大的表，通过取模，按照日期范围等等拆分成若干个小表
  
## 4、 分库分表中垂直分库方案会带来哪些问题？
  单表的数据量还是会很大
  
## 5、分布式数据存储中间件如mycat的核心流程是什么？
   sql解析 ->数据源分配 -> 请求响应 -> 结果整合
   
## 6、 概述一下mycat？
  分布式关系型数据库解决方案
  
## 7、 解释一下全局表，ER表，分片表？
  * 全局表：一个字典类数据的表，每个表都有可能用到，在各个数据节点上都会冗余。
  * 分片表：按照一定的规则后，表按照设置的primaryKey来分配到不同的数据节点上。
  * ER表：和分片表有外键关系的表，也是通过设置的primaryKey即与分片表的外键，和分片表按照一样的规则分配到不同的数据节点上。
  
## 8、 Mycat的在分库分表之后，它是怎么支持联表查询的？
  * 使用好ER表
  * 善用全局表
  * 在sql上添加注解
 
 
       /*!mycat:catlet=io.mycat.catlets.ShareJoin */

## 9、 进行库表拆分时，拆分规则怎么取舍？
  * 不存在热点数据时，则使用连续分片
  
  * 存在热点数据时，使用离散分片或者是综合分片
  
  * 离散分片暂时迁移比较麻烦（但是mycat给出了数据迁移的脚本，虽然现在还是不是很完美），综合分片占用总机器数量多

## 10、 Mycat中全局ID方案有哪些？程序自定义全局ID的方案有哪些？
  一、mycat的全局id方案
  
  本地文件方式 
  
      sequnceHandlerType = 0
      配置sequence_conf.properties
      使用next value for MYCATSEQ_XXX
  
  数据库方式
  
      sequnceHandlerType = 1
      配置sequence_db_conf.properties
      使用next value for MYCATSEQ_XXX或者指定autoIncrement
  
  本地时间戳方式
  
      ID= 64 位二进制 (42(毫秒)+5(机器 ID)+5(业务编码)+12(重复累加)
      sequnceHandlerType = 2
      配置sequence_time_conf.properties
      指定autoIncrement
      
  二、 程序方式
  
      Snowflake
      UUID
      Redis
## 11、 简述一下一致性hash的原理？这样设计的好处是什么？
  ![](https://img-blog.csdnimg.cn/20190701222525406.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2ppb25nc3VpNzYwNQ==,size_16,color_FFFFFF,t_70)
  
  做数据迁移时，不像离散分片那样，全部数据都会动，一致性hash的话，就只需要动一小部分数据。
  
## 12、 4层负载和7层负载谁性能更高？为什么？这2者区别是什么？
  4层的高一点，是基于TCP的，7层是应用层，是基于应用协议，比如HTTP/FTP/SMTP
  
## 13、 讲一讲高可用方案
  ![](https://img-blog.csdnimg.cn/20190701224331325.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2ppb25nc3VpNzYwNQ==,size_16,color_FFFFFF,t_70)
  