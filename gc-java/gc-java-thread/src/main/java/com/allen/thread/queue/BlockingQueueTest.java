package com.allen.thread.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @program: MultiThread
 * @description: BlockingQueueTest
 * @author: allen小哥
 * @Date: 2019-12-14 19:46
 **/
@Slf4j
public class BlockingQueueTest {

    public static final LinkedBlockingDeque LINKED_BLOCKING_DEQUE = new LinkedBlockingDeque();

    public static void main(String[] args) {
        //数组实现的有界阻塞队列
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(5);
        blockingQueue.add("早上好");
        boolean offer = blockingQueue.offer("中午好");
        log.info("offer==="+offer);
        Object peek = blockingQueue.peek();
        log.info("peek="+peek);

        blockingQueue.remove();

        Object poll = blockingQueue.poll();
        log.info("poll="+poll);

        //是一个存放实现Delayed接口的数据的无界阻塞队列
        DelayQueue delayQueue = new DelayQueue();

        //基于链表数据结构的有界阻塞双端队列
        LinkedBlockingDeque linkedBlockingDeque = new LinkedBlockingDeque();

        //链表实现的有界阻塞队列
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();

        //链表数据结构构成的无界阻塞队列
        LinkedTransferQueue linkedTransferQueue = new LinkedTransferQueue();

        //支持优先级的无界阻塞队列
        PriorityBlockingQueue priorityBlockingQueue = new PriorityBlockingQueue();

        //每个插入操作必须等待另一个线程进行相应的删除操作
        SynchronousQueue synchronousQueue = new SynchronousQueue();
    }

}
