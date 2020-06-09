package com.nobug;


import com.alibaba.fastjson.JSON;
import com.nobug.entity.Brand;
import com.nobug.entity.User;
import com.nobug.vo.OperationLogParameter;
import org.junit.Test;

public class fastjsonTest {

    @Test
    public void test_Brand(){
        Brand brand = new Brand();
        brand.setName("华为");
        brand.setFirstChar('H');


        System.out.println(JSON.toJSONString(brand, true));
    }

    @Test
    public void test_OperationLogParameter(){
        OperationLogParameter parameter = new OperationLogParameter();
        //parameter.setOperateUser("CZXIWADE");
        parameter.setOperateMethod("insert");
        System.out.println(JSON.toJSONString(parameter, true));
    }

    @Test
    public void test_(){
        User user = new User();
        user.setUsername("蔡徐坤");
        user.setPassword("123456789");
        user.setName("坤坤");
        user.setSex('1');
        System.out.println(JSON.toJSONString(user, true));

    }
}
