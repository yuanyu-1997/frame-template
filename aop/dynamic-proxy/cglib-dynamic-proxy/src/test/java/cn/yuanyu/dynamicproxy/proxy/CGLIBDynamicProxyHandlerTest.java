package cn.yuanyu.dynamicproxy.proxy;


import cn.yuanyu.dynamicproxy.interceptor.UserServiceInterceptor;
import cn.yuanyu.dynamicproxy.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;
// https://www.cnblogs.com/wyq1995/p/10945034.html
@Slf4j
public class CGLIBDynamicProxyHandlerTest {

    @Test
    public void invoke() {
        //在指定目录下生成动态代理类，我们可以反编译看一下里面到底是一些什么东西
        //System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, ".");

        //创建Enhancer对象，类似于JDK动态代理的Proxy类，下一步就是设置几个参数
        Enhancer enhancer = new Enhancer();
        //设置目标类的字节码文件
        enhancer.setSuperclass(UserService.class);
        //设置回调函数
        enhancer.setCallback(new UserServiceInterceptor());
        //这里的creat方法就是正式创建代理类
        UserService proxyUserService = (UserService) enhancer.create();
        //调用代理类的eat方法

        proxyUserService.e();

    }

}