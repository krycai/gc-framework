## ThreadLocal
  ThreadLocal对象可以提供线程局部变量，每个线程Thread拥有一份自己的副本变量，多个线程互不干扰。
  1)ThreadLocal的数据结构
   ![](https://snailclimb.gitee.io/javaguide/docs/java/multi-thread/images/thread-local/2.png)
   
   2)Java的四种引用类型
   * 强引用：我们常常new出来的对象就是强引用类型，只要强引用存在，垃圾回收器将永远不会回收被引用的对象，哪怕内存不足的时候
   * 软引用：使用SoftReference修饰的对象被称为软引用，软引用指向的对象在内存要溢出的时候被回收
   * 弱引用：使用WeakReference修饰的对象被称为弱引用，只要发生垃圾回收，若这个对象只被弱引用指向，那么就会被回收
   * 虚引用：虚引用是最弱的引用，在 Java 中使用 PhantomReference 进行定义。虚引用中唯一的作用就是用队列接收对象即将死亡的通知
   
   2)set()方法的源码，其中包括set数据、清理数据、优化数据桶的位置等操作(**重点**)
   
   