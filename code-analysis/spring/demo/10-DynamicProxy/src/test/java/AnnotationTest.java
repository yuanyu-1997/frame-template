import cn.yuanyu.ssm.config.AppConfig;
import cn.yuanyu.ssm.service.OrderService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// https://yuanyu.blog.csdn.net/article/details/108754891

public class AnnotationTest {

@Test
public void test() {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    OrderService orderService = applicationContext.getBean(OrderService.class);
    orderService.reduceStock();
}

}
