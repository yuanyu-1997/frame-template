package cn.yuanyu.mp.mapper;

import cn.yuanyu.mp.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    /**
     * 1.根据id查询（自定义的方法）
     * select * from tb_user where id = ?
     */
    @Test
    public void testFindById() {
        System.out.println(userMapper.findById(1L));
    }


    /**
     * 3.测试根据 主键id去更新
     * UPDATE tb_user SET password=?, age=? WHERE id=?
     */
    @Test
    public void testUpdateById() {
        User user = new User();
        user.setId(1L); //条件，根据id更新
        user.setAge(19); //更新的字段
        user.setPassword("666666");
        int result = userMapper.updateById(user);
        System.out.println("result => " + result);
    }


    /**
     * 测试全表更新，SQL分析器的阻断效果
     * UPDATE tb_user SET age=?
     */
    @Test
    public void testUpdateAll() {
        User user = new User();
        user.setAge(20);
        //Error updating database.  Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Prohibition of table update operation
        //Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Prohibition of table update operation
        int result = this.userMapper.update(user, null);
        System.out.println("result = " + result);
    }


    /**
     * 测试乐观锁
     */
    @Test
    public void testOptimisticLock() {
        User user = new User();
        user.setId(1L); //条件（根据id更新）
        user.setAge(60); //更新的字段
        //TODO 设置version信息
        user.setVersion(1); //当前的版本信息
        int result = userMapper.updateById(user);
        System.out.println("result => " + result);
    }

    //: ==>  Preparing: SELECT id,user_name,password,name,age,email,version FROM tb_user WHERE id=?
    //: ==> Parameters: 1(Long)
    //: <==      Total: 1
    //User(id=1, userName=zhangsan, password=666666, name=张三, age=60, email=zhangsan@bug.cn, version=8)
    //: ==>  Preparing: UPDATE tb_user SET user_name=?, password=?, name=?, age=?, email=?, version=? WHERE id=? AND version=?
    //: ==> Parameters: zhangsan(String), 666666(String), 张三(String), 60(Integer), zhangsan@bug.cn(String), 9(Integer), 1(Long), 8(Integer)
    //: <==    Updates: 1
    /**
     * UPDATE tb_user
     * SET user_name=?,
     *     password=?,
     *     name=?,
     *     age=?,
     *     email=?,
     *     version=9
     * WHERE id = ?
     *   AND version = 8
     */
    @Test
    public void testOptimisticLockRightWay() {
        //先查询
        User user = userMapper.selectById(1L);
        System.out.println(user);
        //在字段
        user.setAge(60);
        //TODO 查询出来的结果已经带了版本号了
        int result = userMapper.updateById(user); //打断点 修改这条记录的版本号
        if (result == 0) {
            //抛出异常 或者 执行重试
            throw new RuntimeException("更新数据失败，版本号错误");
        } else {
            System.out.println("result => " + result);
        }
    }


}