package cn.yuanyu.mp.mapper;

import cn.yuanyu.mp.enums.SexEnum;
import cn.yuanyu.mp.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
     * 1.测试插入一条记录
     * INSERT INTO tb_user ( user_name, password, name, age, email ) VALUES ( ?, ?, ?, ?, ? )
     */
    @Test
    public void testInsert() {
        User user = new User();
        user.setUserName("cao");
        //TODO mail字段和数据库名字不对应
        user.setMail("cao@yuanyu.cn");
        user.setAge(30);
        user.setName("曹操");
        user.setPassword("123456");
        //TODO address表中没有
        user.setAddress("北京");
        int result = userMapper.insert(user); //result数据库受影响的行数
        System.out.println("result => " + result);
        //获取自增长后的id值, 自增长后的id值会回填到user对象中
        System.out.println("id => " + user.getId());
    }


    /**
     * 测试 @TableField(fill = FieldFill.INSERT)
     */
    @Test
    public void testFieldFill() {
        User user = new User();
        user.setUserName("guanyu");
        user.setMail("guanyu@yuanyu.cn");
        user.setAge(30);
        user.setName("关羽");
        user.setPassword("123456");
        //TODO 不设置version值采用默认的，设置了采用设置的值
        //user.setVersion(666);
        int result = userMapper.insert(user);
        System.out.println("result => " + result);
    }

    /**
     * 测试逻辑删除
     */
    //: ==>  Preparing: UPDATE tb_user SET deleted=1 WHERE id=? AND deleted=0
    //: ==> Parameters: 1(Long)
    //: <==    Updates: 1
    @Test
    public void testDeleteById() {
        int result = userMapper.deleteById(1L);
        System.out.println("result => " + result);
    }

    /**
     * 测试逻辑删除
     */
    //: ==>  Preparing: SELECT id,user_name,name,age,email AS mail,version,deleted FROM tb_user WHERE id=? AND deleted=0
    //: ==> Parameters: 1(Long)
    //: <==      Total: 0
    @Test
    public void testSelectById() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }


    /**
     *
     */
    //: ==>  Preparing: SELECT id,user_name,name,age,email AS mail,version,deleted FROM tb_user WHERE deleted=0
    //: ==> Parameters:
    @Test
    public void testSelectList() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }


    /**
     * 测试通用枚举
     */
    //: ==>  Preparing: INSERT INTO tb_user ( user_name, password, name, age, email, version, sex ) VALUES ( ?, ?, ?, ?, ?, ?, ? )
    //: ==> Parameters: diaochan(String), 123456(String), 貂蝉(String), 20(Integer), diaochan@itast.cn(String), 1(Integer), 2(Integer)
    //: <==    Updates: 1
    @Test
    public void testInsertEnum() {
        User user = new User();
        user.setName("貂蝉");
        user.setUserName("diaochan");
        user.setAge(20);
        user.setMail("diaochan@bug.cn");
        user.setPassword("123456");
        //TODO 优雅的保存枚举
        user.setSex(SexEnum.WOMAN);
        int result = userMapper.insert(user);
        System.out.println("result = " + result);

        //查询插入的值
        testSelectEnum(user.getId());
    }

    //: ==>  Preparing: SELECT id,user_name,name,age,email AS mail,version,deleted,sex FROM tb_user WHERE id=? AND deleted=0
    //: ==> Parameters: 13(Long)
    //: <==      Total: 1
    private void testSelectEnum(long id) {
        User user = userMapper.selectById(id);
        System.out.println(user);
    }

    /**
     * 测试通用枚举
     */
    //: ==>  Preparing: SELECT id,user_name,name,age,email AS mail,version,deleted,sex FROM tb_user WHERE deleted=0 AND (sex = ?)
    //: ==> Parameters: 1(Integer)
    //: <==      Total: 5
    @Test
    public void testSelectBySex() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("sex", SexEnum.MAN);
        List<User> users = this.userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }


}