import cn.yuanyu.ssm.bean.Color;
import cn.yuanyu.ssm.config.MainConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class AnnotationTest {
    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        System.out.println("容器初始化完成>>>>>>>>>>>>>>>>>>>");
        Color color = applicationContext.getBean(Color.class);

        //printBeans(applicationContext);
        System.out.println("关闭容器>>>>>>>>>>>>>>>>>>>");
        applicationContext.close();
    }

    void printBeans(AnnotationConfigApplicationContext context) {
        String[] definitionNames = context.getBeanDefinitionNames();
        for (String definitionName : definitionNames) {
            System.out.println(definitionName);
        }
    }
}
