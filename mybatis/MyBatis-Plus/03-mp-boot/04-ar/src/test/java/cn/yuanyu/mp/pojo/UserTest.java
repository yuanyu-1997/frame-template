package cn.yuanyu.mp.pojo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {

    //TODO 虽然没有使用UserMapper但是我们不能删除（底层会去调用）；com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: cn.yuanyu.mp.pojo.User Not Found TableInfoCache.

    /**
     * 1.测试AR根据主键查询
     * SELECT id,user_name,password,name,age,email FROM tb_user WHERE id=?
     */
    @Test
    public void testARSelectById() {
        User user = new User();
        user.setId(1L);
        //
        User res = user.selectById();
        System.out.println(res);
    }

    /**
     * 2.测试AR新增数据
     * INSERT INTO tb_user ( id, user_name, password, name, age, email ) VALUES ( ?, ?, ?, ?, ?, ? )
     */
    @Test
    public void testARInsert() {
        User user = new User();
        user.setName("刘备");
        user.setAge(30);
        user.setPassword("123456");
        user.setUserName("liubei");
        user.setEmail("liubei@bug.cn");
        // 调用AR的insert方法进行插入数据
        boolean insert = user.insert();
        System.out.println("result => " + insert);
    }

    /**
     * 3.测试AR更新操作
     * UPDATE tb_user SET age=? WHERE id=?
     */
    @Test
    public void testARUpdateById() {
        User user = new User();
        user.setId(6L); //查询条件
        user.setAge(35); //更新的数据
        boolean result = user.updateById();
        System.out.println("result => " + result);
    }



    /**
     * 4.测试AR删除操作
     * DELETE FROM tb_user WHERE id=?
     */
    @Test
    public void testARDeleteById() {
        User user = new User();
        user.setId(6L);
        boolean delete = user.deleteById();
        System.out.println("result => " + delete);
    }

    /**
     * 5.测试AR根据条件查询
     * SELECT id,user_name,password,name,age,email FROM tb_user WHERE (age <= ?)
     */
    @Test
    public void testARSelectList() {
        User user = new User();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.le("age", "20");
        List<User> users = user.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }


}