import cn.yuanyu.ssm.bean.Boss;
import cn.yuanyu.ssm.config.AppConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class AnnotationTest {
@Test
public void test() {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    Boss boss = applicationContext.getBean(Boss.class);
    boss.show();
    printBeans(applicationContext);
}
void printBeans(AnnotationConfigApplicationContext context) {
    String[] definitionNames = context.getBeanDefinitionNames();
    for (String definitionName : definitionNames) {
        System.out.println(definitionName);
    }
}
}
