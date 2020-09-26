import cn.yuanyu.crud.config.AppConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;


public class AnnotationTest {
    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //UserService userService = applicationContext.getBean(UserService.class);
        //User kun = userService.findUserByUsername("蔡徐坤");
        //System.out.println(kun);

        System.out.println("\n");
        // 获取容器中所有的bean的名字
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        Arrays.stream(definitionNames).forEach(System.out::println);
    }
}
