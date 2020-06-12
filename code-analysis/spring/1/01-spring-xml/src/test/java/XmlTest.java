import cn.yuanyu.bean.Person;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//@RunWith(SpringRunner.class) //@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:beans.xml")
public class XmlTest {
    @Test
    public void test(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:beans.xml");
        Person person = applicationContext.getBean(Person.class);
        System.out.println(person);
    }
}
