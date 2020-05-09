package cn.yuanyu.mp.mapper;

import cn.yuanyu.mp.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
     * 2.查询所有
     * SELECT id,user_name,password,name,age,email FROM tb_user
     */
    @Test
    public void testSelectAll() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }



    /**
     * 测试插入一条记录
     *
     */
    @Test
    public void testInsert() {
        User user = new User();
        user.setUserName("cao");
        user.setEmail("cao@yuanyu.cn");
        user.setAge(30);
        user.setName("曹操");
        user.setPassword("123456");
        int result = userMapper.insert(user); //result数据库受影响的行数
        System.out.println("result => " + result);
        //获取自增长后的id值, 自增长后的id值会回填到user对象中
        System.out.println("id => " + user.getId());
    }


    /**
     * 测试分页查询
     */
    //: ==>  Preparing: SELECT COUNT(1) FROM tb_user WHERE (email LIKE ?)
    //: ==> Parameters: %yuanyu%(String)
    //: ==>  Preparing: SELECT id,user_name,password,name,age,email FROM tb_user WHERE (email LIKE ?) LIMIT ?,?
    //: ==> Parameters: %yuanyu%(String), 0(Long), 3(Long)
    //: <==      Total: 3
    @Test
    public void testSelectPage() {
        Page<User> page = new Page<>(1, 3); //查询第一页，查询3条数据
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //设置查询条件
        wrapper.like("email", "yuanyu");

        IPage<User> iPage = userMapper.selectPage(page, wrapper);
        System.out.println("数据总条数： " + iPage.getTotal());
        System.out.println("数据总页数： " + iPage.getPages());
        System.out.println("当前页数： " + iPage.getCurrent());
        //
        List<User> records = iPage.getRecords();
        records.forEach(System.out::println);
    }






}