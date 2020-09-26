import cn.yuanyu.config.AppConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class AnnotationTest {
    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //Person person = applicationContext.getBean(Person.class);
        //System.out.println(person);
    }
}
