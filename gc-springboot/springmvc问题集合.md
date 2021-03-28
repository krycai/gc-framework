## 1. Springmvc 工作原理是什么？
  * 客户端发送请求到 DispatcherServlet
  * DispatcherServlet 查询 handlerMapping 找到处理请求的 Controller
  * Controller 调用业务逻辑后，返回 ModelAndView
  * DispatcherServlet 查询 ModelAndView，找到指定视图
  * 视图将结果返回到客户端
  
## 2. Springmvc 执行流程是什么?
  * 用户发送请求至前端控制器DispatcherServlet；
  * DispatcherServlet收到请求后，调用HandlerMapping处理器映射器，请求获取Handle；
  * 处理器映射器根据请求url找到具体的处理器，生成处理器对象及处理器拦截器(如果有则生成)一并返回给DispatcherServlet；
  * DispatcherServlet 调用 HandlerAdapter处理器适配器；
  * HandlerAdapter 经过适配调用 具体处理器(Handler，也叫后端控制器)；
  * Handler执行完成返回ModelAndView；
  * HandlerAdapter将Handler执行结果ModelAndView返回给DispatcherServlet；
  * DispatcherServlet将ModelAndView传给ViewResolver视图解析器进行解析；
  * ViewResolver解析后返回具体View；
  * DispatcherServlet对View进行渲染视图（即将模型数据填充至视图中）
  * DispatcherServlet响应用户。
  
  ![](https://img-blog.csdn.net/20180708224853769?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2E3NDUyMzM3MDA=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)
  
## 3. Springmvc 中如何解决 GET | POST请求中文乱码问题？
  (1)、GET方式：
      
   * 每次发生请求之前对URL进行编码：
      
      例如：Location.href="/encodeURI"(“http://localhost/test/s?name=中文&sex=女”);
      
   * 更简便的方法，在服务器端配置URL编码格式：修改tomcat的配置文件server.xml：
      
      只需增加 URIEncoding=“UTF-8” 这一句，然后重启tomcat即可。
      
      
        <ConnectorURIEncoding="UTF-8" 
            port="8080"  maxHttpHeaderSize="8192"  maxThreads="150" 
            minSpareThreads="25"  maxSpareThreads="75"connectionTimeout="20000" 		
            disableUploadTimeout="true" URIEncoding="UTF-8" />

  (2)、POST方式：
      
   可以每次在request解析数据时设置编码格式：request.setCharacterEncoding(“utf-8”);
      
   也可以使用编码过滤器来解决，最常用的方法是使用Spring提供的编码过滤器：
      
   在Web.xml中增加如下配置（要注意的是它的位置一定要是第一个执行的过滤器）
   
      <filter>
          <filter-name>charsetFilter</filter-name>
          <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
          <init-param>
              <param-name>encoding</param-name>
              <param-value>UTF-8</param-value>
          </init-param>
          <init-param>
              <param-name>forceEncoding</param-name>
              <param-value>true</param-value>
          </init-param>
      </filter>

   该过滤器要做的其实就是强制为所有请求和响应设置编码格式：
   
     request.setCharacterEncoding(“utf-8”);
     response.setCharacterEncoding(“utf-8”);
## 4. Springmvc 怎么样设定重定向和转发的？
  在返回值前面加"forward:“就可以让结果转发,譬如"forward:user.do?name=method4”
  
  在返回值前面加"redirect:“就可以让返回值重定向,譬如"redirect:http://www.baidu.com”
  
## 5. Springmvc 怎么和AJAX相互调用的？
  通过Jackson框架就可以把Java里面的对象直接转化成Js可以识别的Json对象。具体步骤如下 ：
  
  * 加入Jackson.jar
  * 在配置文件中配置json的映射
  * 在接受Ajax方法里面可以直接返回Object,List等,但方法前面要加上@ResponseBody注解。
  
## 6. Springmvc 如何做异常处理 ？
  可以将异常抛给Spring框架，由Spring框架来处理；自定义实现spring的全局异常解析器HandlerExceptionResolver，在异常处理器中添视图页面即可。
  
## 7. Springmvc 的控制器是不是单例模式,如果是,有什么问题,怎么解决？
  是单例模式,所以在多线程访问的时候有线程安全问题,不要用同步,会影响性能的,解决方案是在控制器里面不能写字段。
  
## 8. Springmvc 中 如果拦截get方式提交的方法,怎么配置？
  可以在@RequestMapping注解里面加上method=RequestMethod.GET
  
## 9. Springmvc 怎么样把ModelMap里面的数据放入Session里面？
  可以在类上面加上@SessionAttributes注解,里面包含的字符串就是要放入session里面的key

## 10. Springmvc 中系统如何分层 ？
  * 系统分为表现层（UI）：数据的展现，操作页面，请求转发。
  * 业务层（服务层）：封装业务处理逻辑
  * 持久层（数据访问层）：封装数据访问逻辑
  
  各层之间的关系： 表示层通过接口调用业务层，业务层通过接口调用持久层，这样，当下一层发生变化改变，不影响上一层的数据。 MVC是一种表现层的架构.
  
  ![](https://7n.w3cschool.cn/attachments/image/20171124/1511516651564380.png)
  
## 11. Springmvc 和struts2的区别有哪些?
  * springmvc的入口是一个servlet即前端控制器（DispatchServlet），而struts2入口是一个filter过虑器（StrutsPrepareAndExecuteFilter）。
  * springmvc是基于方法开发(一个url对应一个方法)，请求参数传递到方法的形参，可以设计为单例或多例(建议单例)，struts2是基于类开发，传递参数是通过类的属性，只能设计为多例。
  * Struts采用值栈存储请求和响应的数据，通过OGNL存取数据，springmvc通过参数解析器是将request请求内容解析，并给方法形参赋值，将数据和视图封装成ModelAndView对象，最后又将ModelAndView中的模型数据通过reques域传输到页面。Jsp视图解析器默认使用jstl。

## 12. Springmvc 用什么对象从后台向前台传递数据的？
  通过ModelMap对象,可以在这个对象里面用put方法,把对象加到里面,前台就可以通过el表达式拿到。
  
## 13. springmvc 中当一个方法向AJAX返回特殊对象,譬如Object,List等,需要做什么处理？
  要加上@ResponseBody注解。
  
## 14. Springmvc 中对于文件的上传有哪些需要注意
  * 在页面form中提交enctype="multipart/form-data"的数据时，需要springmvc对multipart类型的数据进行解析。
  * 在springmvc.xml中配置multipart类型解析器。
  * 方法中使用：MultipartFile attach (单个文件上传) 或者 MultipartFile[] attachs (多个文件上传)

## 15. Springmvc 中拦截器如何使用
  * 定义拦截器，实现HandlerInterceptor接口。接口中提供三个方法。
  
    1)、preHandle ：进入 Handler方法之前执行，用于身份认证、身份授权，比如身份认证，如果认证通过表示当前用户没有登陆，需要此方法拦截不再向下执行
    
    2)、postHandle：进入Handler方法之后，返回modelAndView之前执行，应用场景从modelAndView出发：将公用的模型数据(比如菜单导航)在这里传到视图，也可以在这里统一指定视图
    
    3)、afterCompletion：执行Handler完成执行此方法，应用场景：统一异常处理，统一日志处理
    
  * 拦截器配置：
     1)、针对HandlerMapping配置(不推荐)：springmvc拦截器针对HandlerMapping进行拦截设置，如果在某个HandlerMapping中配置拦截，经过该 HandlerMapping映射成功的handler最终使用该 拦截器。(一般不推荐使用)

     2)、类似全局的拦截器：springmvc配置类似全局的拦截器，springmvc框架将配置的类似全局的拦截器注入到每个HandlerMapping中
 


