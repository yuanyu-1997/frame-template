import cn.yuanyu.bean.Person;
import cn.yuanyu.config.AppConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Arrays;
import java.util.Map;


// -Dos.name=linux
public class AnnotationTest {
    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //
        String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);
        System.out.println(Arrays.toString(beanNamesForType));
        //
        Map<String, Person> personMap = applicationContext.getBeansOfType(Person.class);
        System.out.println(personMap);
        // 获取环境变量的值
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("os.name");
        System.out.println(property);
    }
}
