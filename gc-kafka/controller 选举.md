## ZooKeeper /controller 节点
   在一个 Kafka 集群中，某段时间内只能有一台 Broker 被选举为 Controller。随着时间的推移，可能会有不同的 Broker 陆续担任过 Controller 的角色，但是在某一时刻，Controller 只能由一个 Broker 担任。

  Controller 的选举过程依赖 ZooKeeper 完成。ZooKeeper 除了扮演集群元数据的“真理之源”角色，还定义了 **/controller 临时节点（Ephemeral Node**），以协助完成 Controller 的选举。
  
  既然是临时节点，那么，一旦 Broker 与 ZooKeeper 的会话终止，该节点就会消失。Controller 选举就依靠了这个特性。每个 Broker 都会监听 /controller 节点随时准备应聘 Controller 角色。下图展示了 Broker 与 /controller 节点的交互关系：
  
  ![](https://static001.geekbang.org/resource/image/2e/83/2e75cdbfb68c86169ec83f58e59e1283.jpg)
  
  集群上所有的 Broker 都在实时监听 ZooKeeper 上的这个节点。这里的“监听”有两个含义。
  
  * **监听这个节点是否存在**。倘若发现这个节点不存在，Broker 会立即“抢注”该节点，即创建 /controller 节点。创建成功的那个 Broker，即当选为新一届的 Controller。
  * **监听这个节点数据是否发生了变更**。同样，一旦发现该节点的内容发生了变化，Broker 也会立即启动新一轮的 Controller 选举。
  
## KafkaController 文件的代码结构如下图所示：
![](https://static001.geekbang.org/resource/image/7e/88/7e5ddb69df585b5bbbcc91336ab8f588.jpg)

* **选举触发器**（ElectionTrigger）：这里的选举不是指 Controller 选举，而是指**主题分区副本的选举**，即为哪些分区选择 Leader 副本。
* **KafkaController Object**：KafkaController 伴生对象，仅仅定义了一些**常量和回调函数类型**。
* **ControllerEvent**：定义 Controller 事件类型。上节课我们详细学习过 Controller 事件以及基于事件的单线程事件队列模型。这部分的代码看着很多，但实际上都是千篇一律的。你看懂了一个事件的定义，其他的也就不在话下了。
* **各种 ZooKeeper 监听器**：定义 ZooKeeper 监听器，去监听 ZooKeeper 中各个节点的变更。今天，我们重点关注监听 /controller 节点的那个监听器。
* **KafkaController Class**：定义 KafkaController 类以及实际的处理逻辑。这是我们今天的重点学习对象。

## Controller 选举流程
![](https://static001.geekbang.org/resource/image/a8/98/a8cbc562518f93f9befc6bd7a87d5b98.jpg)

这三个场景是：
* 集群从零启动时；
* Broker 侦测 /controller 节点消失时；
* Broker 侦测到 /controller 节点数据发生变更时。

**场景一：集群从零启动**

集群首次启动时，Controller 尚未被选举出来。于是，Broker 启动后，首先将 Startup 这个 ControllerEvent 写入到事件队列中，
然后启动对应的事件处理线程和 **ControllerChangeHandler ZooKeeper 监听器**，最后依赖事件处理线程进行 Controller 的选举。

在源码中，KafkaController 类的 startup 方法就是做这些事情的。当 Broker 启动时，它会调用这个方法启动 ControllerEventThread 线程。值得注意的是，**每个 Broker 都需要做这些事情，不是说只有 Controller 所在的 Broker 才需要执行这些逻辑。**

首先，startup 方法会注册 ZooKeeper 状态变更监听器，用于监听 Broker 与 ZooKeeper 之间的会话是否过期。接着，写入 Startup 事件到事件队列，然后启动 ControllerEventThread 线程，开始处理事件队列中的 Startup 事件。
    
    def startup() = {
      // 第1步：注册ZooKeeper状态变更监听器，它是用于监听Zookeeper会话过期的
      zkClient.registerStateChangeHandler(new StateChangeHandler {
        override val name: String = StateChangeHandlers.ControllerHandler
        override def afterInitializingSession(): Unit = {
          eventManager.put(RegisterBrokerAndReelect)
        }
        override def beforeInitializingSession(): Unit = {
          val queuedEvent = eventManager.clearAndPut(Expire)
          queuedEvent.awaitProcessing()
        }
      })
      // 第2步：写入Startup事件到事件队列
      eventManager.put(Startup)
      // 第3步：启动ControllerEventThread线程，开始处理事件队列中的ControllerEvent
      eventManager.start()
    }



**场景二：/controller 节点消失**

Broker 检测到 /controller 节点消失时，就意味着，此时整个集群中没有 Controller。因此，所有检测到 /controller 节点消失的 Broker，都会立即调用 **elect 方法执行竞选逻辑**。

你可能会问：“Broker 是怎么侦测到 ZooKeeper 上的这一变化的呢？”实际上，这是 ZooKeeper 监听器提供的功能，换句话说，这是 Apache ZooKeeper 自己实现的功能，所以我们才说，Kafka 依赖 ZooKeeper 完成 Controller 的选举。

讲到这里，我说点题外话，社区最近正在酝酿彻底移除 ZooKeeper 依赖。具体到 Controller 端的变化，就是在 Kafka 内部实现一个类似于 Raft 的共识算法来选举 Controller。我会在后面的特别放送里详细讲一下社区移除 ZooKeeper 的全盘计划。

**场景三：/controller 节点数据变更**

Broker 检测到 /controller 节点数据发生变化，通常表明，**Controller“易主”了**，这就分为两种情况：

* 如果 Broker 之前是 Controller，那么该 Broker 需要首先执行卸任操作，然后再尝试竞选；
* 如果 Broker 之前不是 Controller，那么，该 Broker 直接去竞选新 Controller。

具体到代码层面，maybeResign 方法形象地说明了这两种情况。你要注意方法中的 maybe 字样，这表明，Broker 可能需要执行卸任操作，也可能不需要。
Kafka 源码非常喜欢用 maybe*** 来命名方法名，以表示那些在特定条件下才需要执行的逻辑。以下是 maybeResign 的实现：

    
    private def maybeResign(): Unit = {
      // 非常关键的一步！这是判断是否需要执行卸任逻辑的重要依据！
      // 判断该Broker之前是否是Controller
      val wasActiveBeforeChange = isActive
      // 注册ControllerChangeHandler监听器  
      zkClient.registerZNodeChangeHandlerAndCheckExistence(
        controllerChangeHandler)
      // 获取当前集群Controller所在的Broker Id，如果没有Controller则返回-1
      activeControllerId = zkClient.getControllerId.getOrElse(-1)
      // 如果该Broker之前是Controller但现在不是了
      if (wasActiveBeforeChange && !isActive) {
        onControllerResignation() // 执行卸任逻辑
      }
    }

代码的第一行非常关键，它是决定是否需要执行卸任的重要依据。毕竟，如果 Broker 之前不是 Controller，那何来“卸任”一说呢？之后代码要注册 ControllerChangeHandler 监听器，获取当前集群 Controller 所在的 Broker ID，如果没有 Controller，则返回 -1。有了这些数据之后，maybeResign 方法需要判断该 Broker 是否之前是 Controller 但现在不是了。如果是这种情况的话，则调用 onControllerResignation 方法执行 Controller 卸任逻辑。

说到“卸任”，你可能会问：“卸任逻辑是由哪个方法执行的呢？”实际上，这是由 onControllerResignation 方法执行的，它主要是用于清空各种数据结构的值、取消 ZooKeeper 监听器、关闭各种状态机以及管理器，等等。我用注释的方式给出它的逻辑实现：
    
    private def onControllerResignation(): Unit = {
      debug("Resigning")
      // 取消ZooKeeper监听器的注册
      zkClient.unregisterZNodeChildChangeHandler(
        isrChangeNotificationHandler.path)
      zkClient.unregisterZNodeChangeHandler(
        partitionReassignmentHandler.path)
      zkClient.unregisterZNodeChangeHandler(
        preferredReplicaElectionHandler.path)
      zkClient.unregisterZNodeChildChangeHandler(
        logDirEventNotificationHandler.path)
      unregisterBrokerModificationsHandler(
        brokerModificationsHandlers.keySet)
      // 关闭Kafka线程调度器，其实就是取消定期的Leader重选举
      kafkaScheduler.shutdown()
      // 将统计字段全部清0
      offlinePartitionCount = 0
      preferredReplicaImbalanceCount = 0
      globalTopicCount = 0
      globalPartitionCount = 0
      topicsToDeleteCount = 0
      replicasToDeleteCount = 0
      ineligibleTopicsToDeleteCount = 0
      ineligibleReplicasToDeleteCount = 0
      // 关闭Token过期检查调度器
      if (tokenCleanScheduler.isStarted)
        tokenCleanScheduler.shutdown()
      // 取消分区重分配监听器的注册
      unregisterPartitionReassignmentIsrChangeHandlers()
      // 关闭分区状态机
      partitionStateMachine.shutdown()
      // 取消主题变更监听器的注册
      zkClient.unregisterZNodeChildChangeHandler(topicChangeHandler.path)
      // 取消分区变更监听器的注册
      unregisterPartitionModificationsHandlers(
        partitionModificationsHandlers.keys.toSeq)
      // 取消主题删除监听器的注册
      zkClient.unregisterZNodeChildChangeHandler(
        topicDeletionHandler.path)
      // 关闭副本状态机
      replicaStateMachine.shutdown()
      // 取消Broker变更监听器的注册
      zkClient.unregisterZNodeChildChangeHandler(brokerChangeHandler.path)
      // 关闭Controller通道管理器
      controllerChannelManager.shutdown()
      // 清空集群元数据
      controllerContext.resetContext()
      info("Resigned")
    }

## 选举 Controller
    
    private def elect(): Unit = {
        // 第1步：获取当前Controller所在Broker的序号，如果Controller不存在，显式标记为-1
        activeControllerId = zkClient.getControllerId.getOrElse(-1)

    // 第2步：如果当前Controller已经选出来了，直接返回即可
    if (activeControllerId != -1) {
      debug(s"Broker $activeControllerId has been elected as the controller, so stopping the election process.")
      return
    }

    try {
      // 第3步：注册Controller相关信息
      // 主要是创建/controller节点
      val (epoch, epochZkVersion) = zkClient.registerControllerAndIncrementControllerEpoch(config.brokerId)
      controllerContext.epoch = epoch
      controllerContext.epochZkVersion = epochZkVersion
      activeControllerId = config.brokerId

      info(s"${config.brokerId} successfully elected as the controller. Epoch incremented to ${controllerContext.epoch} " +
        s"and epoch zk version is now ${controllerContext.epochZkVersion}")

      // 第4步：执行当选Controller的后续逻辑
      onControllerFailover()
    } catch {
      case e: ControllerMovedException =>
        maybeResign()

        if (activeControllerId != -1)
          debug(s"Broker $activeControllerId was elected as controller instead of broker ${config.brokerId}", e)
        else
          warn("A controller has been elected but just resigned, this will result in another round of election", e)

      case t: Throwable =>
        error(s"Error while electing or becoming controller on broker ${config.brokerId}. " +
          s"Trigger controller movement immediately", t)
        triggerControllerMove()
    }
    }
  
  ![](https://static001.geekbang.org/resource/image/23/1b/2331395774956a61f37836c46d65d01b.jpg)
  
  该方法首先检查 Controller 是否已经选出来了。要知道，集群中的所有 Broker 都要执行这些逻辑，因此，非常有可能出现某些 Broker 在执行 elect 方法时，Controller 已经被选出来的情况。如果 Controller 已经选出来了，那么，自然也就不用再做什么了。相反地，如果 Controller 尚未被选举出来，那么，代码会尝试创建 /controller 节点去抢注 Controller。
  
  一旦抢注成功，就调用 onControllerFailover 方法，执行选举成功后的动作。这些动作包括注册各类 ZooKeeper 监听器、删除日志路径变更和 ISR 副本变更通知事件、启动 Controller 通道管理器，以及启动副本状态机和分区状态机。
  
  如果抢注失败了，代码会抛出 ControllerMovedException 异常。这通常表明 Controller 已经被其他 Broker 抢先占据了，那么，此时代码调用 maybeResign 方法去执行卸任逻辑。
  
  
## 总结
![](https://static001.geekbang.org/resource/image/e2/74/e28c134e4fd11ff8ed87933aee88d374.jpg)

## 源码

KafkaController（）【原生字段、辅助字段、统计字段】 --》 process() \ startup() \ maybeResign() \onControllerResignation() -->elect()

ControllerChangeHandler（）【监听器】 --》handleCreation()、handleDeletion() 和 handleDataChange()




https://time.geekbang.org/column/article/238614






  