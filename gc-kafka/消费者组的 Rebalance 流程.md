## 在Rebalance中，Coordinator如何处理成员入组? 
**加入组（JoinGroup）**，是指**消费者组下的各个成员向 Coordinator 发送 JoinGroupRequest 请求加入进组的过程**。这个过程有一个超时时间，如果有成员在超时时间之内，无法完成加入组操作，它就会被排除在这轮 Rebalance 之外。

**组同步（SyncGroup）**，是指**当所有成员都成功加入组之后，Coordinator 指定其中一个成员为 Leader，然后将订阅分区信息发给 Leader 成员**。
接着，所有成员（包括 Leader 成员）向 Coordinator 发送 SyncGroupRequest 请求。需要注意的是，**只有 Leader 成员发送的请求中包含了订阅分区消费分配方案，在其他成员发送的请求中，这部分的内容为空。**
当 Coordinator 接收到分配方案后，会通过向成员发送响应的方式，通知各个成员要消费哪些分区。

## 源码文件
  * **加入组**的源码实现，它们位于 GroupCoordinator.scala 文件中。

  KafkaApis.scala  ---》handle(request: RequestChannel.Request)  ---》  case ApiKeys.JOIN_GROUP => handleJoinGroupRequest(request) --》 groupCoordinator.handleJoinGroup（）

  GroupCoordinator --》handleJoinGroup（） --》 doUnknownJoinGroup() | doJoinGroup()


  ![](https://static001.geekbang.org/resource/image/b7/20/b7ed79cbf4eba29b39f32015b527c220.jpg)

* **组同步**的源码实现，它们位于 GroupCoordinator.scala 文件中。

KafkaApis.scala  ---》handle(request: RequestChannel.Request)  ---》  case ApiKeys.SYNC_GROUP => handleSyncGroupRequest(request)  --> groupCoordinator.handleSyncGroup()

GroupCoordinator  -->handleSyncGroup() --> doSyncGroup()

 * 重点

**组同步**：成员向 Coordinator 发送 SyncGroupRequest 请求以获取分配方案。

**handleSyncGroup** 方法：接收 KafkaApis 发来的 SyncGroupRequest 请求体数据，执行组同步逻辑。

**doSyncGroup** 方法：真正执行组同步逻辑的方法，执行组元数据保存、分配方案下发以及状态变更操作。

![](https://static001.geekbang.org/resource/image/fc/e9/fccc73c2867102f2ec6e8e3666f101e9.jpg)

