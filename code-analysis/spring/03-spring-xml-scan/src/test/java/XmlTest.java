import cn.yuanyu.userapi.service.UserService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class XmlTest {
    @Test
    public void test(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:beans.xml");
        UserService userService = applicationContext.getBean(UserService.class);
        System.out.println(userService);
    }
}
