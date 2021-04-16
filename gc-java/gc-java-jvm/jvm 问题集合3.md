## 1.Class 的作用了解么？
  在 Java 中，JVM 可以理解的代码就叫做字节码（即扩展名为 .class 的文件），它不面向任何特定的处理器，只面向虚拟机。Java 语言通过字节码的方式，在一定程度上解决了传统解释型语言执行效率低的问题，同时又保留了解释型语言可移植的特点。所以 Java 程序运行时比较高效，而且，由于字节码并不针对一种特定的机器，因此，Java 程序无须重新编译便可在多种不同操作系统的计算机上运行。
  
  Clojure（Lisp 语言的一种方言）、Groovy、Scala 等语言都是运行在 Java 虚拟机之上。下图展示了不同的语言被不同的编译器编译成.class文件最终运行在 Java 虚拟机之上。.class文件的二进制格式可以使用 WinHex 查看。
  
  ![](https://images.xiaozhuanlan.com/photo/2020/4a7b12cb39cd42bdee49085b63fb0fbb.png)
  
  可以说.class文件是不同的语言在 Java 虚拟机之间的重要桥梁，同时也是支持 Java 跨平台很重要的一个原因。
  
## 2.Class 文件结构介绍一下
   根据 Java 虚拟机规范，类文件由单个 ClassFile 结构组成：
   
      ClassFile {
          u4             magic; //Class 文件的标志
          u2             minor_version;//Class 的小版本号
          u2             major_version;//Class 的大版本号
          u2             constant_pool_count;//常量池的数量
          cp_info        constant_pool[constant_pool_count-1];//常量池
          u2             access_flags;//Class 的访问标记
          u2             this_class;//当前类
          u2             super_class;//父类
          u2             interfaces_count;//接口
          u2             interfaces[interfaces_count];//一个类可以实现多个接口
          u2             fields_count;//Class 文件的字段属性
          field_info     fields[fields_count];//一个类会可以有多个字段
          u2             methods_count;//Class 文件的方法数量
          method_info    methods[methods_count];//一个类可以有个多个方法
          u2             attributes_count;//此类的属性表中的属性数
          attribute_info attributes[attributes_count];//属性表集合
      }
   下面详细介绍一下 Class 文件结构涉及到的一些组件。
   
   Class 文件字节码结构组织示意图 （之前在网上保存的，非常不错，原出处不明）：
   
   ![](https://images.xiaozhuanlan.com/photo/2020/d8dc3aaa58378fb546049cd0e2830e4c.png)
   
### 2.1 魔数
   
       u4             magic; //Class 文件的标志
       
   每个 Class 文件的头四个字节称为魔数（Magic Number）,它的唯一作用是确定这个文件是否为一个能被虚拟机接收的 Class 文件。
   
   程序设计者很多时候都喜欢用一些特殊的数字表示固定的文件类型或者其它特殊的含义。
   
### 2.2 Class 文件版本
 
         u2             minor_version;//Class 的小版本号
         u2             major_version;//Class 的大版本号
   紧接着魔数的四个字节存储的是 Class 文件的版本号：第五和第六是次版本号，第七和第八是主版本号。
   
   高版本的 Java 虚拟机可以执行低版本编译器生成的 Class 文件，但是低版本的 Java 虚拟机不能执行高版本编译器生成的 Class 文件。所以，我们在实际开发的时候要确保开发的的 JDK 版本和生产环境的 JDK 版本保持一致。
   
## 2.3 常量池
  
        u2             constant_pool_count;//常量池的数量
        cp_info        constant_pool[constant_pool_count-1];//常量池
   
   紧接着主次版本号之后的是常量池，常量池的数量是 constant_pool_count-1（常量池计数器是从 1 开始计数的，将第 0 项常量空出来是有特殊考虑的，索引值为 0 代表“不引用任何一个常量池项”）。
   
   常量池主要存放两大常量：**字面量和符号引用**。字面量比较接近于 Java 语言层面的的常量概念，如文本字符串、声明为 final 的常量值等。而符号引用则属于编译原理方面的概念。包括下面三类常量：
   
   * 类和接口的全限定名
   * 字段的名称和描述符
   * 方法的名称和描述符
   
   常量池中每一项常量都是一个表，这 14 种表有一个共同的特点：开始的第一位是一个 u1 类型的标志位 -tag 来标识常量的类型，代表当前这个常量属于哪种常量类型．
   
      类型	                          标志（tag）	描述
      CONSTANT_utf8_info	           1	UTF-8 编码的字符串
      CONSTANT_Integer_info            3	整形字面量
      CONSTANT_Float_info	           4	浮点型字面量
      CONSTANT_Long_info	           ５	长整型字面量
      CONSTANT_Double_info	           ６	双精度浮点型字面量
      CONSTANT_Class_info	           ７	类或接口的符号引用
      CONSTANT_String_info	           ８	字符串类型字面量
      CONSTANT_Fieldref_info	       ９	字段的符号引用
      CONSTANT_Methodref_info	       10	类中方法的符号引用
      CONSTANT_InterfaceMethodref_info	11	接口中方法的符号引用
      CONSTANT_NameAndType_info	      12	字段或方法的符号引用
      CONSTANT_MothodType_info	      16	标志方法类型
      CONSTANT_MethodHandle_info	  15	表示方法句柄
      CONSTANT_InvokeDynamic_info	  18	表示一个动态方法调用点
      
   .class 文件可以通过javap -v class类名 指令来看一下其常量池中的信息(javap -v class类名-> temp.txt ：将结果输出到 temp.txt 文件)。
   
### 2.4 访问标志
   在常量池结束之后，紧接着的两个字节代表访问标志，这个标志用于识别一些类或者接口层次的访问信息，包括：这个 Class 是类还是接口，是否为 public 或者 abstract 类型，如果是类的话是否声明为 final 等等。
    
   类访问和属性修饰符:
   
   ![](https://images.xiaozhuanlan.com/photo/2020/8138feb1bab19e31dfb07b4bd78743c2.png)
   
   我们定义了一个 Employee 类
   
      package top.snailclimb.bean;
      public class Employee {
         ...
      }
      
   通过javap -v class类名 指令来看一下类的访问标志。
   
   ![](https://images.xiaozhuanlan.com/photo/2020/2ea03fa3ba8e47a413573b932bd17a0e.png)
   
### 2.5 当前类索引,父类索引与接口索引集合
 
         u2             this_class;//当前类
         u2             super_class;//父类
         u2             interfaces_count;//接口
         u2             interfaces[interfaces_count];//一个类可以实现多个接口
         
  类索引用于确定这个类的全限定名，父类索引用于确定这个类的父类的全限定名，由于 Java 语言的单继承，所以父类索引只有一个，除了 java.lang.Object 之外，所有的 java 类都有父类，因此除了 java.lang.Object 外，所有 Java 类的父类索引都不为 0。
  
  接口索引集合用来描述这个类实现了那些接口，这些被实现的接口将按 implements (如果这个类本身是接口的话则是extends) 后的接口顺序从左到右排列在接口索引集合中。
  
### 2.6 字段表集合
         u2             fields_count;//Class 文件的字段的个数
         field_info     fields[fields_count];//一个类会可以有个字段
         
   字段表（field info）用于描述接口或类中声明的变量。字段包括类级变量以及实例变量，但不包括在方法内部声明的局部变量。
   
   field info(字段表) 的结构:
   
   ![](https://images.xiaozhuanlan.com/photo/2020/ca98d0d604a782b38934f9996b6f6aa4.png)
   
   * access_flags: 字段的作用域（public ,private,protected修饰符），是实例变量还是类变量（static修饰符）,可否被序列化（transient 修饰符）,可变性（final）,可见性（volatile 修饰符，是否强制从主内存读写）。
   * name_index: 对常量池的引用，表示的字段的名称；
   * descriptor_index: 对常量池的引用，表示字段和方法的描述符；
   * attributes_count: 一个字段还会拥有一些额外的属性，attributes_count 存放属性的个数；
   * attributes[attributes_count]: 存放具体属性具体内容。
   
   上述这些信息中，各个修饰符都是布尔值，要么有某个修饰符，要么没有，很适合使用标志位来表示。而字段叫什么名字、字段被定义为什么数据类型这些都是无法固定的，只能引用常量池中常量来描述。
   
   字段的 access_flag 的取值:
   
   ![](https://images.xiaozhuanlan.com/photo/2020/f01faf73c589ec30a7114303e905e21c.png)
   
### 2.7 方法表集合
         u2             methods_count;//Class 文件的方法的数量
         method_info    methods[methods_count];//一个类可以有个多个方法
         
   methods_count 表示方法的数量，而 method_info 表示方法表。
   
   Class 文件存储格式中对方法的描述与对字段的描述几乎采用了完全一致的方式。方法表的结构如同字段表一样，依次包括了访问标志、名称索引、描述符索引、属性表集合几项。
   
   method_info(方法表的) 结构:
   
   ![](https://images.xiaozhuanlan.com/photo/2020/b627a859891abefea32d89720c529c2a.png)
   
   方法表的 access_flag 取值：
   
   ![](https://images.xiaozhuanlan.com/photo/2020/88456294be49d87321309073f5e76624.png)
   
   注意：因为volatile修饰符和transient修饰符不可以修饰方法，所以方法表的访问标志中没有这两个对应的标志，但是增加了synchronized、native、abstract等关键字修饰方法，所以也就多了这些关键字对应的标志。
   
### 2.8 属性表集合
        u2             attributes_count;//此类的属性表中的属性数
        attribute_info attributes[attributes_count];//属性表集合
   在 Class 文件，字段表，方法表中都可以携带自己的属性表集合，以用于描述某些场景专有的信息。与 Class 文件中其它的数据项目要求的顺序、长度和内容不同，属性表集合的限制稍微宽松一些，不再要求各个属性表具有严格的顺序，并且只要不与已有的属性名重复，任何人实现的编译器都可以向属性表中写 入自己定义的属性信息，Java 虚拟机运行时会忽略掉它不认识的属性。
   
## 3.知道类加载的过程吗？
   Class 文件需要加载到虚拟机中之后才能运行和使用，那么虚拟机是如何加载这些 Class 文件呢？
   
   系统加载 Class 类型的文件主要三步:**加载->连接->初始化**。连接过程又可分为三步:**验证->准备->解析**。
   
   ![](https://images.xiaozhuanlan.com/photo/2020/e7675c4c23eee1e1a9ba035d7375fb84.png)
   
## 4.那加载这一步做了什么?
   类加载过程的第一步，主要完成下面 3 件事情：
   
   * 通过全类名获取定义此类的二进制字节流
   * 将字节流所代表的静态存储结构转换为方法区的运行时数据结构
   * 在内存中生成一个代表该类的 Class 对象,作为方法区这些数据的访问入口
   
   虚拟机规范上面这 3 点并不具体，因此是非常灵活的。比如："通过全类名获取定义此类的二进制字节流" 并没有指明具体从哪里获取、怎样获取。比如：比较常见的就是从 ZIP 包中读取（日后出现的 JAR、EAR、WAR 格式的基础）、其他文件生成（典型应用就是 JSP）等等。
   
   一个非数组类的加载阶段（加载阶段获取类的二进制字节流的动作）是可控性最强的阶段，这一步我们可以去完成还可以自定义类加载器去控制字节流的获取方式（重写一个类加载器的 loadClass() 方法）。数组类型不通过类加载器创建，它由 Java 虚拟机直接创建。
   
   类加载器、双亲委派模型也是非常重要的知识点，这部分内容会在后面的文章中单独介绍到。
   
   加载阶段和连接阶段的部分内容是交叉进行的，加载阶段尚未结束，连接阶段可能就已经开始了。
   
## 5.知道哪些类加载器?
   JVM 中内置了三个重要的 ClassLoader，除了 BootstrapClassLoader 其他类加载器均由 Java 实现且全部继承自java.lang.ClassLoader：
   
   * BootstrapClassLoader(启动类加载器) ：最顶层的加载类，由 C++实现，负责加载 %JAVA_HOME%/lib目录下的 jar 包和类或者或被 -Xbootclasspath参数指定的路径中的所有类。
   * ExtensionClassLoader(扩展类加载器) ：主要负责加载目录 %JRE_HOME%/lib/ext 目录下的 jar 包和类，或被 java.ext.dirs 系统变量所指定的路径下的 jar 包。
   * AppClassLoader(应用程序类加载器) :面向我们用户的加载器，负责加载当前应用 classpath 下的所有 jar 包和类。
   
## 6.双亲委派模型知道吗？能介绍一下吗?
   每一个类都有一个对应它的类加载器。系统中的 ClassLoder 在协同工作的时候会默认使用 双亲委派模型 。即在类加载的时候，系统会首先判断当前类是否被加载过。已经被加载的类会直接返回，否则才会尝试加载。加载的时候，首先会把该请求委派该父类加载器的 loadClass() 处理，因此所有的请求最终都应该传送到顶层的启动类加载器 BootstrapClassLoader 中。当父类加载器无法处理时，才由自己来处理。当父类加载器为 null 时，会使用启动类加载器 BootstrapClassLoader 作为父类加载器。
   
   ![](https://images.xiaozhuanlan.com/photo/2020/cbcf86f2b1e58ad4915d67579088c023.png)
   
   每个类加载都有一个父类加载器，我们通过下面的程序来验证。
   
     public class ClassLoaderDemo {
         public static void main(String[] args) {
             System.out.println("ClassLodarDemo's ClassLoader is " + ClassLoaderDemo.class.getClassLoader());
             System.out.println("The Parent of ClassLodarDemo's ClassLoader is " + ClassLoaderDemo.class.getClassLoader().getParent());
             System.out.println("The GrandParent of ClassLodarDemo's ClassLoader is " + ClassLoaderDemo.class.getClassLoader().getParent().getParent());
         }
     }
     
   Output
   
     ClassLodarDemo's ClassLoader is sun.misc.Launcher$AppClassLoader@18b4aac2
     The Parent of ClassLodarDemo's ClassLoader is sun.misc.Launcher$ExtClassLoader@1b6d3586
     The GrandParent of ClassLodarDemo's ClassLoader is null
     
  AppClassLoader的父类加载器为ExtClassLoader
  
  ExtClassLoader的父类加载器为 null，null 并不代表ExtClassLoader没有父类加载器，而是 BootstrapClassLoader 。
  
  其实这个双亲翻译的容易让别人误解，我们一般理解的双亲都是父母，这里的双亲更多地表达的是“父母这一辈”的人而已，并不是说真的有一个 Mother ClassLoader 和一个 Father ClassLoader 。
  另外，类加载器之间的“父子”关系也不是通过继承来体现的，是由“优先级”来决定。官方 API 文档对这部分的描述如下:
  
     The Java platform uses a delegation model for loading classes. The basic idea is that every class loader has a "parent" class loader.
      When loading a class, a class loader first "delegates" the search for the class to its parent class loader before attempting to find the class itself.
      
## 7.双亲委派模型实现源码简单分析一下
   双亲委派模型的实现代码非常简单，逻辑非常清晰，都集中在 java.lang.ClassLoader 的 loadClass() 中，相关代码如下所示。
   
     private final ClassLoader parent;
     protected Class<?> loadClass(String name, boolean resolve)
             throws ClassNotFoundException
         {
             synchronized (getClassLoadingLock(name)) {
                 // 首先，检查请求的类是否已经被加载过
                 Class<?> c = findLoadedClass(name);
                 if (c == null) {
                     long t0 = System.nanoTime();
                     try {
                         if (parent != null) {//父加载器不为空，调用父加载器loadClass()方法处理
                             c = parent.loadClass(name, false);
                         } else {//父加载器为空，使用启动类加载器 BootstrapClassLoader 加载
                             c = findBootstrapClassOrNull(name);
                         }
                     } catch (ClassNotFoundException e) {
                        //抛出异常说明父类加载器无法完成加载请求
                     }
     
                     if (c == null) {
                         long t1 = System.nanoTime();
                         //自己尝试加载
                         c = findClass(name);
     
                         // this is the defining class loader; record the stats
                         sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                         sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                         sun.misc.PerfCounter.getFindClasses().increment();
                     }
                 }
                 if (resolve) {
                     resolveClass(c);
                 }
                 return c;
             }
         }
## 8.双亲委派模型的好处了解么？
   双亲委派模型保证了 Java 程序的稳定运行，可以避免类的重复加载（JVM 区分不同类的方式不仅仅根据类名，相同的类文件被不同的类加载器加载产生的是两个不同的类），
   也保证了 Java 的核心 API 不被篡改。如果没有使用双亲委派模型，而是每个类加载器加载自己的话就会出现一些问题，比如我们编写一个称为 java.lang.Object 类的话，
   那么程序运行的时候，系统就会出现多个不同的 Object 类。
   
## 9.如果我们不想用双亲委派模型怎么办？
  自定义加载器的话，需要继承 ClassLoader 。如果我们不想打破双亲委派模型，就重写 ClassLoader 类中的 findClass() 方法即可，
  无法被父类加载器加载的类最终会通过这个方法被加载。但是，如果想打破双亲委派模型则需要重写 loadClass() 方法
  
## 10.如何自定义类加载器?
  除了 BootstrapClassLoader 其他类加载器均由 Java 实现且全部继承自java.lang.ClassLoader。如果我们要自定义自己的类加载器，很明显需要继承 ClassLoader。
     
  
   