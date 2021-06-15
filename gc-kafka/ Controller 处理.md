## 概述
![](https://static001.geekbang.org/resource/image/13/5f/13c0d8b3f52c295c70c71a154dae185f.jpg)

## 1、Controller元数据：Controller都保存有哪些东西？有几种状态
![](https://static001.geekbang.org/resource/image/f1/54/f146aceb78a5da31d887618303b5ff54.jpg)

ControllerContext   --> updatePartitionStateMetrics()   --> addLiveBrokersAndEpochs()、removeLiveBrokers() 和 updateBrokerMetadata()

KafkaController --> onBrokerFailure() -->processTopicChange()

ControllerStats

### Controller 的重要元数据
* Controller 元数据：Controller 当前定义了 17 种元数据，涵盖 Kafka 集群数据的方方面面。
* ControllerContext：定义元数据以及操作它们的类。
* 关键元数据字段：最重要的元数据包括 offlinePartitionCount、liveBrokers、partitionAssignments 等。
* ControllerContext 工具方法：ControllerContext 类定义了很多实用方法来管理这些元数据信息。

## 2、ControllerChannelManager：Controller如何管理请求发送？
![](https://static001.geekbang.org/resource/image/3e/f7/3e8b0a34f003db5d67d5adafe8781ef7.jpg)

LeaderAndIsrRequest、StopReplicaRequest 和 UpdateMetadataRequest

* LeaderAndIsrRequest：最主要的功能是，告诉 Broker 相关主题各个分区的 Leader 副本位于哪台 Broker 上、ISR 中的副本都在哪些 Broker 上。
  在我看来，**它应该被赋予最高的优先级，毕竟，它有令数据类请求直接失效的本领**。试想一下，如果这个请求中的 Leader 副本变更了，之前发往老的 Leader 的 PRODUCE 请求是不是全部失效了？因此，我认为它是非常重要的控制类请求。

* StopReplicaRequest：告知指定 Broker 停止它上面的副本对象，该请求甚至还能删除副本底层的日志数据。这个请求主要的使用场景，**是分区副本迁移和删除主题**。在这两个场景下，都要涉及停掉 Broker 上的副本操作。

* UpdateMetadataRequest：顾名思义，该请求会更新 Broker 上的元数据缓存。集群上的所有元数据变更，都首先发生在 Controller 端，然后再经由这个请求广播给集群上的所有 Broker。在我刚刚分享的案例中，正是因为这个请求被处理得不及时，才导致集群 Broker 无法获取到最新的元数据信息。

AbstractControlRequest  

它依然是一个线程安全的阻塞队列，Controller 事件处理线程（第 13 节课会详细说它）负责向这个队列写入待发送的请求，而一个名为 RequestSendThread 的线程负责执行真正的请求发送。如下图所示：

![](https://static001.geekbang.org/resource/image/82/21/825d084eb1517daace5532d1c93b0321.jpg)

RequestSendThread  -->doWork() 

doWork 的执行逻辑：

![](https://static001.geekbang.org/resource/image/86/19/869727e22f882509a149d1065a8a1719.jpg)

ControllerChannelManager 这个类和 RequestSendThread 是合作共赢的关系。在我看来，它有两大类任务。
* 管理 Controller 与集群 Broker 之间的连接，并为每个 Broker 创建 RequestSendThread 线程实例；
* 将要发送的请求放入到指定 Broker 的阻塞队列中，等待该 Broker 专属的 RequestSendThread 线程进行处理。

ControllerChannelManager  --> addBroker()，以及底层相关的私有方法 addNewBroker 和 startRequestSendThread 方法。

## 3、ControllerEventManager：变身单线程后的Controller如何处理事件？
![](https://static001.geekbang.org/resource/image/67/31/67fbf8a12ebb57bc309188dcbc18e231.jpg)

* ControllerEventProcessor：Controller 端的事件处理器接口。
* ControllerEvent：Controller 事件，也就是事件队列中被处理的对象。
* ControllerEventManager：事件处理器，用于创建和管理 ControllerEventThread。
* ControllerEventThread：专属的事件处理线程，唯一的作用是处理不同种类的 ControllEvent。这个类是 ControllerEventManager 类内部定义的线程类。

目前，Controller 总共定义了 25 类事件和 17 种状态，它们的对应关系如下表所示：

![](https://static001.geekbang.org/resource/image/a4/63/a4bd821a8fac58bdf9c813379bc28e63.jpg)

QueuedEvent   -->process()  --> ControllerEventProcessor 的 process()

ControllerEventThread 类的 doWork() 流程：

![](https://static001.geekbang.org/resource/image/db/d1/db4905db1a32ac7d356317f29d920dd1.jpg)

ControllerEventThread  -- >put() --> clearAndPut()

**重点:**
* ControllerEvent：定义 Controller 能够处理的各类事件名称，目前总共定义了 25 类事件。
* ControllerState：定义 Controller 状态。你可以认为，它是 ControllerEvent 的上一级分类，因此，ControllerEvent 和 ControllerState 是多对一的关系。
* ControllerEventManager：Controller 定义的事件管理器，专门定义和维护专属线程以及对应的事件队列。
* ControllerEventThread：事件管理器创建的事件处理线程。该线程排他性地读取事件队列并处理队列中的所有事件。

![](https://static001.geekbang.org/resource/image/4e/26/4ec79e1ff2b83d0a1e850b6acf30b226.jpg)

## 4、如何理解Controller在Kafka集群中的作用？
**集群成员管理：**
* 成员数量的管理，主要体现在新增成员和移除现有成员；
* 单个成员的管理，如变更单个 Broker 的数据等。

KafkaController -- >BrokerChangeHandler  --- 》processBrokerChange() 、onBrokerFailure()和 onBrokerStartup()

![](https://static001.geekbang.org/resource/image/ff/d3/fffc8456d8ede9219462e607fa4241d3.jpg)

**成员数量管理:**

Controller 也是通过 ZooKeeper 监听器的方式来应对 Broker 的变化。这个监听器就是 BrokerModificationsHandler。一旦 Broker 的信息发生变更，该监听器的 handleDataChange 方法就会被调用，向事件队列写入 BrokerModifications 事件。

KafkaController  --> processBrokerModification () \ onBrokerUpdate() \ processTopicChange() \ processTopicDeletion()

processTopicDeletion 流程：

![](https://static001.geekbang.org/resource/image/97/c9/976d35f7771f4cd5ef94eda856fb53c9.jpg)

TopicChangeHandler 、 TopicDeletionHandler

* **集群成员管理**：Controller 负责对集群所有成员进行有效管理，包括自动发现新增 Broker、自动处理下线 Broker，以及及时响应 Broker 数据的变更。
* **主题管理**：Controller 负责对集群上的所有主题进行高效管理，包括创建主题、变更主题以及删除主题，等等。对于删除主题而言，实际的删除操作由底层的 TopicDeletionManager 完成。

![](https://static001.geekbang.org/resource/image/00/37/0035a579a02def8f5234831bf0857f37.jpg)




