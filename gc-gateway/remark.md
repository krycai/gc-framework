## 网关配置
 * 请求admin服务方式 ：localhost:9999/admin/admin/operationLog/list
 
 
    配置说明：
     spring.cloud.gateway.routes.id ：表示微服务的name
     spring.cloud.gateway.routes.uri ：可以使用http路径如:http://localhost:8080，
     也可以使用lb：//开头（lb 代表从注册中心获取服务），后面接的就是你需要转发到的服务名称，这个服务名称必须跟 Eureka 中的对应，否则会找不到服务.
     
     spring.cloud.gateway.routes.predicates.- Path ：predicates表示断言 ,path 表示匹配路径
     
     spring.cloud.gateway.routes.filters：filters表示过滤配置 
     
     
     spring.cloud.gateway.discovery.locator.enabled ：true表示开启之后我们就可以通过地址去访问服务了
     spring.cloud.gateway.discovery.locator.lowerCaseServiceId ：true表示可以通过小写的服务名称进行访问了
     
     args: 参数配置
        filter 名称必须是 RequestRateLimiter。
        redis-rate-limiter.replenishRate：允许用户每秒处理多少个请求。
        redis-rate-limiter.burstCapacity：令牌桶的容量，允许在 1s 内完成的最大请求数。
        key-resolver：使用 SpEL 按名称引用 bean。
        
        name: fallbackcmd   熔断名称
        fallbackUri: forward:/fallback  是发生熔断时回退的 URI 地址，目前只支持 forward 模式的 URI。如果服务被降级，该请求会被转发到该 URI 中
        
         retries: 3   重试次数，默认值是 3 次。
         series: SERVER_ERROR  状态码配置（分段），符合某段状态码才会进行重试逻辑，默认值是 SERVER_ERROR，值是 5，也就是 5XX（5 开头的状态码），共有 5 个值
         
         重试代码：
         public enum Series {
             INFORMATIONAL(1), SUCCESSFUL(2), REDIRECTION(3), CLIENT_ERROR(4), SERVER_ERROR(5);
         }
         statuses：状态码配置，和 series 不同的是这里是具体状态码的配置，取值请参考 org.springframework.http.HttpStatus。
         methods：指定哪些方法的请求需要进行重试逻辑，默认值是 GET 方法 。   
         
         方法代码：
         public enum HttpMethod {
             GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE;
         }
         
         exceptions：指定哪些异常需要进行重试逻辑。默认值是 java.io.IOException 和 org.springframework.cloud.gateway.support.TimeoutException。  
            
     跨域配置：   
     spring:
       cloud:
         gateway:
           globalcors:
             corsConfigurations:
               '[/**]':
                 allowedOrigins: "*"
                 exposedHeaders:
                   - content-type
                 allowedHeaders:
                   - content-type
                 allowCredentials: true
                   allowedMethods:
                   - GET
                   - OPTIONS
                   - PUT
                   - DELETE
                   - POST