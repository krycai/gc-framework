## 1. 什么是网关
  网关是整个微服务API请求的入口，负责拦截所有请求，分发到服务上去。可以实现日志拦截、权限控制、解决跨域问题、限流、熔断、负载均衡，隐藏服务端的ip，黑名单与白名单拦截、授权等，
  常用的网关有zuul(netflix的，但是已经停更了)和spring cloud gateway (springcloudalibaba)。这里主要讲springcloud gateway，springcloud gateway是一个全新的项目,
  其基于spring5.0 以及springboot2.0和项目Reactor等技术开发的网关,其主要的目的是为微服务架构提供一种简单有效的API路由管理方式.
  
## 2.过滤器和网关的对比
  过滤器：对单个服务器的请求进行拦截控制
  
  网关：对所有的服务器的请求进行拦截控制
  
## 3.zuul和spring cloud gateway的对比
  zuul：是Netflix的，是基于servlet实现的，阻塞式的api，不支持长连接。

  gateway：是springcloud自己研制的微服务网关，是基于Spring5构建，能够实现响应式非阻塞式的Api，支持长连接

## 4.网关与nginx区别
  相同点：都是可以实现对api接口的拦截，负载均衡、反向代理、请求过滤等，可以实现和网关一样的效果。

  不同点：Nginx采用C语言编写，Gateway属于Java语言编写的， 能够更好让我们使用java语言来实现对请求的处理。

  Nginx 属于服务器端负载均衡器。

  Gateway 属于本地负载均衡器。

## 5.gateway的组成
  路由 : 网关的基本模块，有ID，目标URI，一组断言和一组过滤器组成

  断言：就是访问该旅游的访问规则，可以用来匹配来自http请求的任何内容，例如headers或者参数

  过滤器：这个就是我们平时说的过滤器，用来过滤一些请求的，gateway有自己默认的过滤器，具体请参考官网，我们也可以自定义过滤器，但是要实现两个接口，ordered和globalfilter

## 6.gateway的流程
  ![](https://upload-images.jianshu.io/upload_images/23484242-f4e3147373cd2e08.png?imageMogr2/auto-orient/strip|imageView2/2/w/443/format/webp)
  
  a. 客户端发送请求，会到达网关的DispatcherHandler处理，匹配到RoutePredicateHandlerMapping。
  
  b. 根据RoutePredicateHandlerMapping匹配到具体的路由策略。
  
  c. FilteringWebHandler获取的路由的GatewayFilter数组，创建 GatewayFilterChain 处理过滤请求
  
  d. 执行我们的代理业务逻辑访问。
  


  