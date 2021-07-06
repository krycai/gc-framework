## synchronized实现原理
Java中每一个对象都可以作为锁，这是synchronized实现同步的基础：

* 普通同步方法，锁是当前实例对象
* 静态同步方法，锁是当前类的class对象
* 同步方法块，锁是括号里面的对象

当一个线程访问同步代码块时，它首先是需要得到锁，当退出或者抛出异常时必须要释放锁，那么它是如何来实现这个机制的呢？我们先看一段简单的代码：

     package cn.alibab.javap;
     
     public class SynchronizedTest {
     
         public synchronized void test1(){
     
         }
         public void test2(){
             synchronized (this){
     
             }
         }
     }
     
 利用javap工具（javap是java编译之后的class文件的分解器）查看生成的class文件信息来分析Synchronized的实现
 
![](https://img-blog.csdn.net/20180906184330574?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2hlZmVuZ2xpYW4=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

![](https://img-blog.csdn.net/2018090618435727?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2hlZmVuZ2xpYW4=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

从上面可以看出，同步代码块是使用**monitorenter和monitorexit**指令实现的，同步方法（在这看不出来需要看JVM底层实现）依靠的是方法修饰符上的**ACC_SYNCHRONIZED**实现。

**同步代码块**：monitorenter指令是在编译后插入到同步代码块的开始位置，monitorexit指令插入到同步代码块的结束位置，JVM需要保证每一个monitorenter都有一个monitorexit与之相对应。任何对象都有一个monitor与之相关联，当且一个monitor被持有之后，他将处于锁定状态。线程执行到monitorenter指令时，将会尝试获取对象所对应的monitor所有权，即尝试获取对象的锁；【摘自并发编程艺术】

**同步方法**：synchronized方法则会被翻译成普通的方法调用和返回指令如:invokevirtual、areturn指令，在VM字节码层面并没有任何特别的指令来实现被synchronized修饰的方法，而是在Class文件的方法表中将该方法的access_flags字段中的synchronized标志位置1，表示该方法是同步方法并使用调用该方法的对象或该方法所属的Class在JVM的内部对象表示Klass做为锁对象。(摘自：http://www.cnblogs.com/javaminer/p/3889023.html)

## synchronized和lock的区别
![](https://img-blog.csdn.net/20180904143958577?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2hlZmVuZ2xpYW4=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

**区别如下：**

1、来源：lock是一个接口，而synchronized是java的一个关键字，synchronized是内置的语言实现；

2、异常是否释放锁：synchronized在发生异常时候会自动释放占有的锁，因此不会出现死锁；而lock发生异常时候，不会主动释放占有的锁，必须手动unlock来释放锁，可能引起死锁的发生。（所以最好将同步代码块用try catch包起来，finally中写入unlock，避免死锁的发生。）

3、是否响应中断：lock等待锁过程中可以用interrupt来中断等待，而synchronized只能等待锁的释放，不能响应中断；

4、是否知道获取锁：Lock可以通过trylock来知道有没有获取锁，而synchronized不能；

5、Lock可以提高多个线程进行读操作的效率。（可以通过readwritelock实现读写分离）

6、在性能上来说，如果竞争资源不激烈，两者的性能是差不多的，而当竞争资源非常激烈时（即有大量线程同时竞争），此时Lock的性能要远远优于synchronized。所以说，在具体使用时要根据适当情况选择。

7、synchronized使用Object对象本身的wait 、notify、notifyAll调度机制，而Lock可以使用Condition进行线程之间的调度。

    //Condition定义了等待/通知两种类型的方法
    Lock lock=new ReentrantLock();
    Condition condition=lock.newCondition();
    ...
    condition.await();
    ...
    condition.signal();
    condition.signalAll();
    
### 1、synchronized和lock的用法区别
synchronized：在需要同步的对象中加入此控制，synchronized可以加在方法上，也可以加在特定代码块中，括号中表示需要锁的对象。

lock：一般使用ReentrantLock类做为锁。在加锁和解锁处需要通过lock()和unlock()显示指出。所以一般会在finally块中写unlock()以防死锁。

### 2、synchronized和lock性能区别
synchronized是托管给JVM执行的，而lock是java写的控制锁的代码。

在Java1.5中，synchronize是性能低效的。因为这是一个重量级操作，需要调用操作接口，导致有可能加锁消耗的系统时间比加锁以外的操作还多。相比之下使用Java提供的Lock对象，性能更高一些。

但是到了Java1.6，发生了变化。synchronize在语义上很清晰，可以进行很多优化，有适应自旋，锁消除，锁粗化，轻量级锁，偏向锁等等。导致在Java1.6上synchronize的性能并不比Lock差。官方也表示，他们也更支持synchronize，在未来的版本中还有优化余地。

**2种机制的具体区别：**

synchronized原始采用的是CPU悲观锁机制，即线程获得的是独占锁。独占锁意味着其他线程只能依靠阻塞来等待线程释放锁。而在CPU转换线程阻塞时会引起线程上下文切换，当有很多线程竞争锁的时候，会引起CPU频繁的上下文切换导致效率很低。

而Lock用的是乐观锁方式。所谓乐观锁就是，每次不加锁而是假设没有冲突而去完成某项操作，如果因为冲突失败就重试，直到成功为止。乐观锁实现的机制就是CAS操作（Compare and Swap）。我们可以进一步研究ReentrantLock的源代码，会发现其中比较重要的获得锁的一个方法是compareAndSetState。这里其实就是调用的CPU提供的特殊指令。

现代的CPU提供了指令，可以自动更新共享数据，而且能够检测到其他线程的干扰，而 compareAndSet() 就用这些代替了锁定。这个算法称作非阻塞算法，意思是一个线程的失败或者挂起不应该影响其他线程的失败或挂起的算法。

### 3、synchronized和lock用途区别
synchronized原语和ReentrantLock在一般情况下没有什么区别，但是在非常复杂的同步应用中，请考虑使用ReentrantLock，特别是遇到下面2种需求的时候。

* 1.某个线程在等待一个锁的控制权的这段时间需要中断
* 2.需要分开处理一些wait-notify，ReentrantLock里面的Condition应用，能够控制notify哪个线程
* 3.具有公平锁功能，每个到来的线程都将排队等候

下面细细道来……

先说第一种情况，ReentrantLock的lock机制有2种，忽略中断锁和响应中断锁，这给我们带来了很大的灵活性。比如：如果A、B 2个线程去竞争锁，A线程得到了锁，B线程等待，但是A线程这个时候实在有太多事情要处理，就是一直不返回，B线程可能就会等不及了，想中断自己，不再等待这个锁了，转而处理其他事情。这个时候ReentrantLock就提供了2种机制：**可中断/可不中断**
* 第一，B线程中断自己（或者别的线程中断它），但是ReentrantLock不去响应，继续让B线程等待，你再怎么中断，我全当耳边风（synchronized原语就是如此）；
* 第二，B线程中断自己（或者别的线程中断它），ReentrantLock处理了这个中断，并且不再等待这个锁的到来，完全放弃。

https://blog.csdn.net/hefenglian/article/details/82383569