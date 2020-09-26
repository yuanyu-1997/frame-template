import cn.yuanyu.ssm.bean.Red;
import cn.yuanyu.ssm.config.AppConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class AnnotationTest {
    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        Red red = applicationContext.getBean(Red.class);


        //printBeans(applicationContext);
    }
    void printBeans(AnnotationConfigApplicationContext context) {
        String[] definitionNames = context.getBeanDefinitionNames();
        for (String definitionName : definitionNames) {
            System.out.println(definitionName);
        }
    }


}
