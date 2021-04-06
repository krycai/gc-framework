## 1.什么是zuul
   Zuul包含了对请求的路由和过滤两个最主要的功能:
   
   其中路由功能负责将外部请求转发到具体的微服务实例上,是实现外部访问统一入口的基础而过滤器功能则负责对请求的处理过程进行干预,是实现请求校验、服务聚合等功能的基础.
   
   Zuul和Eureka进行整合,将Zuul自身注册为Eureka服务治理下的应用,同时从Eureka中获得其他微服务的消息,也即以后的访问微服务都是通过Zuul跳转后获得.
   
## 2.zuul用来做限流，但是为什么要限流？
   * 防止不需要频繁请求服务的请求恶意频繁请求服务，造成服务器资源浪费。
   * 防止不法分子恶意攻击系统，击穿系统盗取数据，防止数据安全隐患。
   * 防止系统高峰时期，对系统对频繁访问，给服务器带来巨大压力。
   * 限流策略
## 3.zuul的工作原理
   1、过滤器机制
   
   zuul的核心是一系列的filters, 其作用可以类比Servlet框架的Filter，或者AOP。
   
   zuul把Request route到 用户处理逻辑 的过程中，这些filter参与一些过滤处理，比如Authentication，Load Shedding等。
   
   ![](https://img-blog.csdnimg.cn/20200128094408502.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1MTIyMDEw,size_16,color_FFFFFF,t_70)
   
   Zuul提供了一个框架，可以对过滤器进行动态的加载，编译，运行。
   
   Zuul的过滤器之间没有直接的相互通信，他们之间通过一个RequestContext的静态类来进行数据传递的。RequestContext类中有ThreadLocal变量来记录每个Request所需要传递的数据。
   
   Zuul的过滤器是由Groovy写成，这些过滤器文件被放在Zuul Server上的特定目录下面，Zuul会定期轮询这些目录，修改过的过滤器会动态的加载到Zuul Server中以便过滤请求使用。
   
   下面有几种标准的过滤器类型：
   
   Zuul大部分功能都是通过过滤器来实现的。Zuul中定义了四种标准过滤器类型，这些过滤器类型对应于请求的典型生命周期。
   
   (1) PRE：这种过滤器在请求被路由之前调用。我们可利用这种过滤器实现身份验证、在集群中选择请求的微服务、记录调试信息等。
   
   (2) ROUTING：这种过滤器将请求路由到微服务。这种过滤器用于构建发送给微服务的请求，并使用Apache HttpClient或Netfilx Ribbon请求微服务。
   
   (3) POST：这种过滤器在路由到微服务以后执行。这种过滤器可用来为响应添加标准的HTTP Header、收集统计信息和指标、将响应从微服务发送给客户端等。
   
   (4) ERROR：在其他阶段发生错误时执行该过滤器。
   
   内置的特殊过滤器
   
   zuul还提供了一类特殊的过滤器，分别为：StaticResponseFilter和SurgicalDebugFilter
   
   StaticResponseFilter：StaticResponseFilter允许从Zuul本身生成响应，而不是将请求转发到源。
   
   SurgicalDebugFilter：SurgicalDebugFilter允许将特定请求路由到分隔的调试集群或主机。
   
   自定义的过滤器
   
   除了默认的过滤器类型，Zuul还允许我们创建自定义的过滤器类型。
   
   如，我们可以定制一种STATIC类型的过滤器，直接在Zuul中生成响应，而不将请求转发到后端的微服务。
   
   2、过滤器的生命周期
   
   Zuul请求的生命周期如图，该图详细描述了各种类型的过滤器的执行顺序。
   
   ![](https://img-blog.csdnimg.cn/20200128102310439.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1MTIyMDEw,size_16,color_FFFFFF,t_70)
   
     try {
       preRoute();
     } catch (ZuulException e) {
        error(e);
        postRoute();
        return;
     }
     
     try {
        route();
     } catch (ZuulException e) {
        error(e);
        postRoute();
        return;
     }
     
     
     try {
        postRoute();
     } catch (ZuulException e) {
        error(e);
        return;
     }
     
## 4.zuul 的作用
   Zuul可以通过加载动态过滤机制，从而实现以下各项功能：
   
   * 验证与安全保障: 识别面向各类资源的验证要求并拒绝那些与要求不符的请求。
   * 审查与监控: 在边缘位置追踪有意义数据及统计结果，从而为我们带来准确的生产状态结论。
   * 动态路由: 以动态方式根据需要将请求路由至不同后端集群处。
   * 压力测试: 逐渐增加指向集群的负载流量，从而计算性能水平。
   * 负载分配: 为每一种负载类型分配对应容量，并弃用超出限定值的请求。
   * 静态响应处理: 在边缘位置直接建立部分响应，从而避免其流入内部集群。
   * 多区域弹性: 跨越AWS区域进行请求路由，旨在实现ELB使用多样化并保证边缘位置与使用者尽可能接近。
   
## 5.Zuul和Nginx的区别
   相同点：Zuul和Nginx都可以实现负载均衡、反向代理（隐藏真实ip地址），过滤请求，实现网关的效果
   
   不同点:
   
   * Nginx–c语言开发
   * Zuul–java语言开发
   * Zuul负载均衡实现：采用ribbon+eureka实现本地负载均衡
   * Nginx负载均衡实现：采用服务器实现负载均衡
   * Nginx相比zuul功能会更加强大，因为Nginx整合一些脚本语言（Nginx+lua）
   * Nginx适合于服务器端负载均衡
   * Zuul适合微服务中实现网关
   
## 6.zuul限流
   流算法
   
   漏桶: leakey bucket,原理：桶的下方的小孔会以一个相对恒定的速率漏水，而不管入桶的水流量，这样就达到了控制出水口的流量
   
   令牌桶: token bucket,原理：以相对恒定的速率向桶中加入令牌，请求来时于桶中取令牌，取到了就放行，没能取到令牌的请求则丢弃
   
   限流粒度
   
   粗粒度
   
   网关限流
   
   单个服务
   
## 7.细粒度
   * user: 认证用户或者匿名，针对某个用户粒度进行限流
   * origin: 客户机的IP,针对请求客户机的IP进行限流
   * url: 特定url,针对请求的url粒度进行限流
   * serviceId: 特定服务，针对某个服务的id粒度进行限流

     
   
  
 