import cn.yuanyu.ssm.bean.Person;
import cn.yuanyu.ssm.config.AppConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class AnnotationTest {
    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        Person person = applicationContext.getBean(Person.class);
        System.out.println(person);

        String city = applicationContext.getEnvironment().getProperty("person.city");
        System.out.println(city);
    }
}
