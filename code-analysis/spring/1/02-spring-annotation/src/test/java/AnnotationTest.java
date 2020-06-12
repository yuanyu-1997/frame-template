import cn.yuanyu.bean.Person;
import cn.yuanyu.config.AppConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//@RunWith(SpringRunner.class) //@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:beans.xml")
public class AnnotationTest {
    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        Person person = applicationContext.getBean(Person.class);
        System.out.println(person);
        //
        String name = applicationContext.getBeanNamesForType(Person.class)[0];
        System.out.println(name);
    }
}
