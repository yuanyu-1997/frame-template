package cn.yuanyu.boot;


import cn.yuanyu.boot.bean.User;
import com.alibaba.fastjson.JSON;
import org.springframework.cglib.beans.BeanMap;

public class BootApplicationTest {

    public static void main(String[] args) {
        User user = new User();
        user.setAge(25);
        // org.springframework.cglib.beans.BeanMap
        BeanMap beanMap = BeanMap.create(user);
        System.out.println(beanMap);
    }
}