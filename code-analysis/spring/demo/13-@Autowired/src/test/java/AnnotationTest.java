import cn.yuanyu.ssm.config.AppConfig;
import cn.yuanyu.ssm.service.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class AnnotationTest {
    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = applicationContext.getBean(UserService.class);
        userService.print();

        printBeans(applicationContext);
    }
    void printBeans(AnnotationConfigApplicationContext context) {
        String[] definitionNames = context.getBeanDefinitionNames();
        for (String definitionName : definitionNames) {
            System.out.println(definitionName);
        }
    }


}
