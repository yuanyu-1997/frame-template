import cn.yuanyu.ssm.bean.Red;
import cn.yuanyu.ssm.service.MathCalculator;
import cn.yuanyu.ssm.config.AppConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class AnnotationTest {

    @Test
    public void test_aop() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        //1、不要自己创建对象
		//MathCalculator mathCalculator = new MathCalculator();
		//mathCalculator.div(1, 1);
        MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);

        mathCalculator.div(1, 1);

        Red red = applicationContext.getBean(Red.class);


        applicationContext.close();
    }


}
