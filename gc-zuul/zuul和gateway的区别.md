## 概述
  spring-cloud-Gateway是spring-cloud的一个子项目。而zuul则是netflix公司的项目，只是spring将zuul集成在spring-cloud中使用而已。
  因为zuul2.0连续跳票和zuul1的性能表现不是很理想，所以催生了spring团队开发了Gateway项目。
  
## Zuul：
  
  使用的是阻塞式的 API，不支持长连接，比如 websockets。
  
  **底层是servlet，Zuul处理的是http请求**
  
  没有提供异步支持，流控等均由hystrix支持。
  
  依赖包spring-cloud-starter-netflix-zuul。
  
## Gateway：
  
  Spring Boot和Spring Webflux提供的Netty底层环境，不能和传统的Servlet容器一起使用，也不能打包成一个WAR包。
  
  依赖spring-boot-starter-webflux和/ spring-cloud-starter-gateway
  
  提供了异步支持，提供了抽象负载均衡，提供了抽象流控，并默认实现了RedisRateLimiter。
  
## 二、相同点：
  1、底层都是servlet
  
  2、两者均是web网关，处理的是http请求
  
## 三、不同点：
  
  **1、内部实现：**
  
  　　gateway对比zuul多依赖了spring-webflux，在spring的支持下，功能更强大，内部实现了限流、负载均衡等，扩展性也更强，但同时也限制了仅适合于Spring Cloud套件
  
  　　zuul则可以扩展至其他微服务框架中，其内部没有实现限流、负载均衡等。
  
  **2、是否支持异步**
  
  　　zuul仅支持同步
  
  　　gateway支持异步。理论上gateway则更适合于提高系统吞吐量（但不一定能有更好的性能），最终性能还需要通过严密的压测来决定
  
  **3、框架设计的角度**
  
  　　gateway具有更好的扩展性，并且其已经发布了2.0.0的RELESE版本，稳定性也是非常好的
  
  **4、性能**
  
  　　WebFlux 模块的名称是 spring-webflux，名称中的 Flux 来源于 Reactor 中的类 Flux。Spring webflux 有一个全新的非堵塞的函数式 Reactive Web 框架，可以用来构建异步的、非堵塞的、事件驱动的服务，在伸缩性方面表现非常好。使用非阻塞API。 Websockets得到支持，并且由于它与Spring紧密集成，所以将会是一个更好的 开发 体验。
  　　
  Zuul 1.x，是一个基于阻塞io的API Gateway。Zuul已经发布了Zuul 2.x，基于Netty，也是非阻塞的，支持长连接，但Spring Cloud暂时还没有整合计划。
  
## 四、总结
  总的来说，在微服务架构，如果使用了Spring Cloud生态的基础组件，则Spring Cloud Gateway相比而言更加具备优势，单从流式编程+支持异步上就足以让开发者选择它了。
  　　
  对于小型微服务架构或是复杂架构（不仅包括微服务应用还有其他非Spring Cloud服务节点），zuul也是一个不错的选择。
  
  
  Spring  WebFlux是随Spring 5推出的响应式Web框架。
  
  1、spring-webflux支持两种开发模式：
  
          （1）类似于Spring WebMVC的基于注解（@Controller、@RequestMapping）的开发模式；
  
          （2）Java 8 lambda风格的函数式开发模式。
  
  2、WebFlux是基于响应式流的，可以用来建立**异步、非阻塞、事件驱动**的服务。默认采用Reactor作为响应式流的实现库，也提供对RxJava的支持。
  
  3、结论
  
  * 两者均是web网关，处理的是http请求
  * gateway对比zuul多依赖了spring-webflux，在spring的支持下，功能更强大，内部实现了限流、负载均衡等，扩展性也更强，但同时也限制了仅适合于Spring Cloud套件，而zuul则可以扩展至其他微服务框架中，其内部没有实现限流、负载均衡等
  * gateway很好的支持异步，而zuul仅支持同步，那么理论上gateway则更适合于提高系统吞吐量（但不一定能有更好的性能），最终性能还需要通过严密的压测来决定
  * 从框架设计的角度看，gateway具有更好的扩展性，并且其已经发布了2.0.0的RELESE版本，稳定性也是非常好的
  * 编码上看，zuul更加简洁易懂，注释规范清晰，而gateway作为Spring家族的一份子，竟然几乎不注释…
  
  总的来说，在微服务架构，如果使用了Spring Cloud生态的基础组件，则Spring Cloud Gateway相比而言更加具备优势，单从流式编程+支持异步上就足以让开发者选择它了。
  
  而对于小型微服务架构或是复杂架构（不仅包括微服务应用还有其他非Spring Cloud服务节点），zuul也是一个不错的选择，当然，这种场景下一般会选择nginx，因为nginx从各个方面都会表现的更好…