package cn.yuanyu.dynamicproxy;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class XiaoMeiProxyTest {


    // 打断点，查看 mother 实现类
    @Test
    public void test_jdk_dynamic_proxy() {

        // 想要和小美出去玩，但是我无法直接找到她
        Girl xiaoMei = new XiaoMei();
        // 他有一个庞大的家庭，想要跟她玩必须征得她家里人的同意
        XiaoMeiProxy family = new XiaoMeiProxy(xiaoMei);
        // 有一次我去找小美约会，碰到了她妈妈，我征得了她妈妈的同意
        Girl mother = (Girl) family.getProxyInstance();
        log.info("xiaoMei: name={},code={}", xiaoMei.getClass().getName(), xiaoMei.hashCode());
        log.info("mother:name={},code={}", mother.getClass().getName(), mother.hashCode());

        // 通过她妈妈这个代理才能约小美去吃饭
        mother.date();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");

        // 通过她妈妈这个代理才能约小美去看电影
        //mother.watchMovie();
    }
}
