## 1、什么是 Spring Boot？
  Spring Boot 是 Spring 开源组织下的子项目，是 Spring 组件一站式解决方案，主要是简化了使用 Spring 的难度，简省了繁重xml的配置，提供了各种启动器，在运行过程中自定配置, 开发者能快速上手。

## 2、为什么要用 Spring Boot？
  Spring Boot 优点非常多，如：独立运行 简化配置 自动配置 无代码生成和XML配置 无需部署war文件

## 3、Spring Boot 的核心配置文件有哪几个？它们的区别是什么？
  Spring Boot 的核心配置文件是 application 和 bootstrap 配置文件。

  application 配置文件这个容易理解，主要用于 Spring Boot 项目的自动化配置。

  bootstrap 配置文件有以下几个应用场景:
    * 1）、使用SpringCloudConfig配置中心时，这时需要在 bootstrap 配置文件中添加连接到配置中心的配置属性来加载外部配置中心的配置信息；
    * 2）、一些固定的不能被覆盖的属性；
    * 3）、一些加密/解密的场景；

## 4、Spring Boot 的配置文件有哪几种格式？它们有什么区别？
  .properties 和 .yml，它们的区别主要是书写格式不同。

  * 1).properties

      app.user.name = javastack

  2).yml

    app:
     user:
        name: javastack

  另外，.yml 格式不支持 @PropertySource 注解导入配置。
  
## 5、SpringBoot的核心注解是哪个？它主要由哪几个注解组成的？
  启动类上面的注解是@SpringBootApplication，它也是 Spring Boot 的核心注解，主要组合包含了以下 3 个注解：
  
    @SpringBootConfiguration：组合了 @Configuration 注解，实现配置文件的功能。
    @EnableAutoConfiguration：打开自动配置的功能，也可以关闭某个自动配置的选项，如关闭数据源自动配置功能： @SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })。
    @ComponentScan：Spring组件扫描。
    
## 6、开启SpringBoot特性有哪几种方式？
  1）继承spring-boot-starter-parent项目
  
     <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.5.6.RELEASE</version>
     </parent>

  2）导入spring-boot-dependencies项目依赖
  
    <dependencyManagement>
       <dependencies>
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-dependencies</artifactId>
               <version>1.5.6.RELEASE</version>
               <type>pom</type>
               <scope>import</scope>
           </dependency>
    </dependencyManagement>

   
  Spring Boot依赖包里面的组件的版本都是和当前Spring Boot绑定的，如果要修改里面组件的版本，只需要添加如下属性覆盖即可，但这种方式只对继承有效，导入的方式无效。
  
     <properties>
        <slf4j.version>1.7.25<slf4j.version>
     </properties>

  如果导入的方式要实现版本的升级，达到上面的效果，这样也可以做到，把要升级的组件依赖放到Spring Boot之前。
   
      <dependencyManagement>
         <dependencies>
             <!-- Override Spring Data release train provided by Spring Boot -->
             <dependency>
                 <groupId>org.springframework.data</groupId>
                 <artifactId>spring-data-releasetrain</artifactId>
                 <version>Fowler-SR2</version>
                 <scope>import</scope>
                 <type>pom</type>
             </dependency>
             <dependency>
                 <groupId>org.springframework.boot</groupId>
                 <artifactId>spring-boot-dependencies</artifactId>
                 <version>1.5.6.RELEASE</version>
                 <type>pom</type>
                 <scope>import</scope>
             </dependency>
         </dependencies>
      </dependencyManagement>

## 7、SpringBoot需要独立的容器运行吗？
  可以不需要，内置了 Tomcat/ Jetty 等容器。
  
## 8、运行SpringBoot有哪几种方式？
  1）打包用命令或者放到容器中运行
  
  2）用 Maven/ Gradle 插件运行
  
  3）直接执行 main 方法运行
  
## 9、Spring Boot 自动配置原理是什么？
  注解 @EnableAutoConfiguration, @Configuration, @ConditionalOnClass 就是自动配置的核心，首先它得是一个配置文件，其次根据类路径下是否有这个类去自动配置。
  
  Spring Boot的自动配置注解是@EnableAutoConfiguration， 从上面的@Import的类可以找到下面自动加载自动配置的映射。
  
     org.springframework.core.io.support.SpringFactoriesLoader.loadFactoryNames(Class<?>, ClassLoader)
     
     public static List<String> loadFactoryNames(Class<?> factoryClass, ClassLoader classLoader) {
       String factoryClassName = factoryClass.getName();
       try {
          Enumeration<URL> urls = (classLoader != null ? classLoader.getResources(FACTORIES_RESOURCE_LOCATION) :
                   lassLoader.getSystemResources(FACTORIES_RESOURCE_LOCATION));
           List<String> result = new ArrayList<String>();
           while (urls.hasMoreElements()) {
               URL url = urls.nextElement();
               Properties properties = PropertiesLoaderUtils.loadProperties(new UrlResource(url));
               String factoryClassNames = properties.getProperty(factoryClassName);
               result.addAll(Arrays.asList(StringUtils.commaDelimitedListToStringArray(factoryClassNames)));
           }
           return result;
       }
       catch (IOException ex) {
           throw new IllegalArgumentException("Unable to load [" + factoryClass.getName() +
                   "] factories from location [" + FACTORIES_RESOURCE_LOCATION + "]", ex);
       }
     
     }

  这个方法会加载类路径及所有jar包下META-INF/spring.factories配置中映射的自动配置的类。
  
    /**
    * The location to look for factories.
    * <p>Can be present in multiple JAR files.
    */
    public static final String FACTORIES_RESOURCE_LOCATION = "META-INF/spring.factories";
    查看Spring Boot自带的自动配置的包： spring-boot-autoconfigure-1.5.6.RELEASE.jar，打开其中的META-INF/spring.factories文件会找到自动配置的映射。
    
    org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
    org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration,\
    org.springframework.boot.autoconfigure.aop.AopAutoConfiguration,\
    org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration,\
    org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration,\
    org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration,\
    org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration,\
    org.springframework.boot.autoconfigure.cloud.CloudAutoConfiguration,\
    ...

  再来看看数据源自动配置的实现注解
  
    @Configuration
    @ConditionalOnClass({ DataSource.class, EmbeddedDatabaseType.class })
    @EnableConfigurationProperties(DataSourceProperties.class)
    @Import({ Registrar.class, DataSourcePoolMetadataProvidersConfiguration.class })
    public class DataSourceAutoConfiguration {
    ...
    @Configuration,@ConditionalOnClass就是自动配置的核心，首先它得是一个配置文件，其次根据类路径下是否有这个类去自动配置。

## 10、Spring Boot 的目录结构是怎样的？
  
    cn
     +- javastack
         +- MyApplication.java
         |
         +- customer
         |   +- Customer.java
         |   +- CustomerController.java
         |   +- CustomerService.java
         |   +- CustomerRepository.java
         |
         +- order
             +- Order.java
             +- OrderController.java
             +- OrderService.java
             +- OrderRepository.java
  
  这个目录结构是主流及推荐的做法，而在主入口类上加上 @SpringBootApplication 注解来开启 Spring Boot 的各项能力，如自动配置、组件扫描等。。
  
## 11、你如何理解 Spring Boot 中的 Starters？
   Starters可以理解为启动器，它包含了一系列可以集成到应用里面的依赖包，你可以一站式集成 Spring 及其他技术，而不需要到处找示例代码和依赖包。如你想使用 Spring JPA 访问数据库，只要加入 spring-boot-starter-data-jpa 启动器依赖就能使用了。
   
   Starters包含了许多项目中需要用到的依赖，它们能快速持续的运行，都是一系列得到支持的管理传递性依赖。具体请看这篇文章《Spring Boot Starters启动器》。

## 12、如何在 Spring Boot 启动的时候运行一些特定的代码？
  可以实现接口 ApplicationRunner 或者 CommandLineRunner，这两个接口实现方式一样，它们都只提供了一个 run 方法，具体请看这篇文章《Spring Boot Runner启动器》。
  
## 13、Spring Boot 有哪几种读取配置的方式？
  Spring Boot 可以通过 @PropertySource,@Value,@Environment, @ConfigurationProperties 来绑定变量，具体请看这篇文章《Spring Boot读取配置的几种方式》。
  
  读取application文件
  
  在application.yml或者properties文件中添加：
  
      info.address=USA
      info.company=Spring
      info.degree=high
  
  @Value注解读取方式

    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.stereotype.Component;
     
    @Component
    public class InfoConfig1 {
     
       @Value("${info.address}")
       private String address;
     
       @Value("${info.company}")
       private String company;
     
       @Value("${info.degree}")
       private String degree;
     
       public String getAddress() {
           return address;
       }
     
       public void setAddress(String address) {
           this.address = address;
       }
     
       public String getCompany() {
           return company;
       }
     
       public void setCompany(String company) {
           this.company = company;
       }
     
       public String getDegree() {
           return degree;
       }
     
       public void setDegree(String degree) {
           this.degree = degree;
       }
     
    }
  
  @ConfigurationProperties注解读取方式
  
    @Component
    @ConfigurationProperties(prefix = "info")
    public class InfoConfig2 {
     
       private String address;
       private String company;
       private String degree;
     
       public String getAddress() {
           return address;
       }
     
       public void setAddress(String address) {
           this.address = address;
       }
     
       public String getCompany() {
           return company;
       }
     
       public void setCompany(String company) {
           this.company = company;
       }
     
       public String getDegree() {
           return degree;
       }
     
       public void setDegree(String degree) {
           this.degree = degree;
       }
     
    }
  
  读取指定文件
  
  资源目录下建立config/db-config.properties:
    
    db.username=root
    db.password=123456
    @PropertySource+@Value注解读取方式
    
    @Component
    @PropertySource(value = { "config/db-config.properties" })
    public class DBConfig1 {
     
       @Value("${db.username}")
       private String username;
     
       @Value("${db.password}")
       private String password;
     
       public String getUsername() {
           return username;
       }
     
       public void setUsername(String username) {
           this.username = username;
       }
     
       public String getPassword() {
           return password;
       }
     
       public void setPassword(String password) {
           this.password = password;
       }
     
    }
    注意：@PropertySource不支持yml文件读取。

  @PropertySource+@ConfigurationProperties注解读取方式
  
    @Component
    @ConfigurationProperties(prefix = "db")
    @PropertySource(value = { "config/db-config.properties" })
    public class DBConfig2 {
     
       private String username;
       private String password;
     
       public String getUsername() {
           return username;
       }
     
       public void setUsername(String username) {
           this.username = username;
       }
     
       public String getPassword() {
           return password;
       }
     
       public void setPassword(String password) {
           this.password = password;
       }
     
    }
  
  Environment读取方式 以上所有加载出来的配置都可以通过Environment注入获取到。
    
    @Autowired
    private Environment env;
     
    // 获取参数
    String getProperty(String key);

## 14、Spring Boot 支持哪些日志框架？推荐和默认的日志框架是哪个？
  Spring Boot 支持 Java Util Logging, Log4j2, Lockback 作为日志框架，如果你使用 Starters 启动器，Spring Boot 将使用 Logback 作为默认日志框架，Spring Boot支持Java Util Logging,Log4j2,Lockback作为日志框架，如果你使用starters启动器，Spring Boot将使用Logback作为默认日志框架。无论使用哪种日志框架，Spring Boot都支持配置将日志输出到控制台或者文件中。
  
  spring-boot-starter启动器包含spring-boot-starter-logging启动器并集成了slf4j日志抽象及Logback日志框架。
 
## 15、SpringBoot 实现热部署有哪几种方式？
  主要有两种方式：
  
  Spring Loaded
  
  Spring-boot-devtools
  
## 16、你如何理解 Spring Boot 配置加载顺序？
  在 Spring Boot 里面，可以使用以下几种方式来加载配置。
  
  1）properties文件；
  
  2）YAML文件；
  
  3）系统环境变量；
  
  4）命令行参数；
  
    配置属性加载的顺序如下：
    1、开发者工具 Devtools 全局配置参数；
    2、单元测试上的 @TestPropertySource 注解指定的参数；
    3、单元测试上的 @SpringBootTest 注解指定的参数；
    4、命令行指定的参数，如 java -jar springboot.jar --name="Java技术栈"；
    5、命令行中的 SPRING_APPLICATION_JSON 指定参数, 如 java -Dspring.application.json='{"name":"Java技术栈"}' -jar springboot.jar
    6、ServletConfig 初始化参数；
    7、ServletContext 初始化参数；
    8、JNDI参数（如 java:comp/env/spring.application.json）；
    9、Java系统参数（来源：System.getProperties()）；
    10、操作系统环境变量参数；
    11、RandomValuePropertySource 随机数，仅匹配：ramdom.*；
    12、JAR包外面的配置文件参数（application-{profile}.properties（YAML））
    13、JAR包里面的配置文件参数（application-{profile}.properties（YAML））
    14、JAR包外面的配置文件参数（application.properties（YAML））
    15、JAR包里面的配置文件参数（application.properties（YAML））
    16、@Configuration配置文件上 @PropertySource 注解加载的参数；
    17、默认参数（通过 SpringApplication.setDefaultProperties 指定）；
  
  数字小的优先级越高，即数字小的会覆盖数字大的参数值，我们来实践下，验证以上配置参数的加载顺序
  
  1、在主应用程序中添加 Java 系统参数。
  
    @Bean
    public CommandLineRunner commandLineRunner() {
        return (args) -> {
            System.setProperty("name", "javastack-system-properties");
        };
    }
  2、在 application.properties 文件中添加属性。
    
    name = javastack-application
 
  3、在 application-dev.properties 文件中添加属性。
  
    name = javastack-application-dev
  
  4、添加测试类
  
    @RunWith(SpringRunner.class)
    @SpringBootTest(value = { "name=javastack-test", "sex=1" })
    @ActiveProfiles("dev")
    public class SpringBootBestPracticeApplicationTests {
     
        @Value("${name}")
        private String name;
     
        @Test
        public void test() {
            System.out.println("name is " + name);
        }
     
    }
  运行 test 单元测试，程序输出：
  name is javastack-test
  
    根据以上参数动态调整，发现参数会被正确覆盖。了解了 Spring Boot 各种配置的加载顺序，如果配置被覆盖了我们就知道是什么问题了。
    
## 17、Spring Boot 如何定义多套不同环境配置？
  提供多套配置文件，如：
  
      applcation.properties
      
      application-dev.properties
      application-test.properties
      application-prod.properties
      
## 18、Spring Boot 可以兼容老 Spring 项目吗，如何做？
  可以兼容，使用 @ImportResource 注解导入老 Spring 项目配置文件。
  
## 19、保护 Spring Boot 应用有哪些方法？
  在生产中使用HTTPS
  
  使用Snyk检查你的依赖关系
  
  升级到最新版本
  
  启用CSRF保护
  
  使用内容安全策略防止XSS攻击

## 20、Spring Boot 2.X 有什么新特性？与 1.X 有什么区别？
  
       1. 配置变更
       2. JDK 版本升级
       3. 第三方类库升级
       4. 响应式 Spring 编程支持
       5. HTTP/2 支持
       6. 配置属性绑定
       
https://blog.csdn.net/cx521600/article/details/90205359

## 21、条件注解

    @ConditionalOnBean（仅仅在当前上下文中存在某个对象时，才会实例化一个Bean）
    @ConditionalOnClass（某个class位于类路径上，才会实例化一个Bean）
    @ConditionalOnExpression（当表达式为true的时候，才会实例化一个Bean）
    @ConditionalOnMissingBean（仅仅在当前上下文中不存在某个对象时，才会实例化一个Bean）
    @ConditionalOnMissingClass（某个class类路径上不存在的时候，才会实例化一个Bean）
    @ConditionalOnNotWebApplication（不是web应用）
   
