import cn.yuanyu.ssm.config.AppConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class AnnotationTest {
    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        printBeans(applicationContext);
        // 工厂Bean获取的是调用getObject创建的对象
        Object color = applicationContext.getBean("colorFactoryBean");
        System.out.println("bean的类型 : " + color.getClass());
        Object color2 = applicationContext.getBean("colorFactoryBean");
        System.out.println(color == color2); // false
        // org.springframework.beans.factory.BeanFactory.FACTORY_BEAN_PREFIX
        Object colorFactoryBean = applicationContext.getBean("&colorFactoryBean");
        System.out.println(colorFactoryBean.getClass());

    }
    void printBeans(AnnotationConfigApplicationContext context) {
        String[] definitionNames = context.getBeanDefinitionNames();
        for (String definitionName : definitionNames) {
            System.out.println(definitionName);
        }
    }
}
