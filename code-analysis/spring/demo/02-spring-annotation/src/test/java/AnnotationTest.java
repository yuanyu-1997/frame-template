import cn.yuanyu.bean.Person;
import cn.yuanyu.config.AppConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class AnnotationTest {
    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        Person person = applicationContext.getBean(Person.class);
        System.out.println(person);
        // 通过类型获取容器中bean的名字
        String name = applicationContext.getBeanNamesForType(Person.class)[0];
        System.out.println(name);
    }
}
