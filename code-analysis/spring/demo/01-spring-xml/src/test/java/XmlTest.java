import cn.yuanyu.bean.Person;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class XmlTest {
    @Test
    public void test() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:beans.xml");
        // 通过类型获取，同一个类形在容器中只有一个才能这样获取
        Person person = applicationContext.getBean(Person.class);
        System.out.println(person);
        // 通过名字获取
        Person p = (Person) applicationContext.getBean("person");
        System.out.println(person == p);
    }
}
