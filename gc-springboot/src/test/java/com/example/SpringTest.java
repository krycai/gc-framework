package com.example;

import com.allen.boot.po.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xuguocai on 2021/3/26 17:14
 */
public class SpringTest {

    @Test
    public void test() throws Exception {
        // 创建IoC容器，并进行初始化
        String resource = "spring-ioc-circular-dependency.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(resource);
        // 获取ClassA的实例（此时会发生循环依赖）
        Person classA = (Person) context.getBean(Person.class);
    }

}
