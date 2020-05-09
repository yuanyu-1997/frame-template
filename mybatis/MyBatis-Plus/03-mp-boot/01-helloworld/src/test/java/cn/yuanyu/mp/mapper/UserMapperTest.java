package cn.yuanyu.mp.mapper;

import cn.yuanyu.mp.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;


    //-------------------------------------------------------------- C -------------------------------------------------------//

    /**
     * 1.测试插入一条记录
     * INSERT INTO tb_user ( user_name, password, name, age, email ) VALUES ( ?, ?, ?, ?, ? )
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


    //-------------------------------------------------------------- D -------------------------------------------------------//

    /**
     * 1.根据id删除数据
     * DELETE FROM tb_user WHERE id=?
     */
    @Test
    public void testDeleteById() {
        int result = userMapper.deleteById(1L);
        System.out.println("result => " + result);
    }

    /**
     * 2.多条件删除数据
     * DELETE FROM tb_user WHERE password = ? AND user_name = ?
     */
    @Test
    public void testDeleteByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_name", "lisi");
        map.put("password", "123456");
        // 根据map删除数据，多条件之间是and关系
        int result = userMapper.deleteByMap(map);
        System.out.println("result => " + result);
    }

    /**
     * 3.根据包装条件做删除
     * DELETE FROM tb_user WHERE (user_name = ? AND password = ?)
     */
    @Test
    public void testDeleteByQueryWrapper() {
        //用法一：
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", "wangwu")
                .eq("password", "123456");
        // 根据包装条件做删除
        int result = userMapper.delete(wrapper);
        System.out.println("result => " + result);
    }

    /**
     * 4.根据包装条件做删除（推荐）
     * DELETE FROM tb_user WHERE user_name=? AND password=?
     */
    @Test
    public void testDelete() {
        //用法二：
        User user = new User();
        user.setUserName("zhaoliu");
        user.setPassword("123456");

        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        // 根据包装条件做删除
        int result = userMapper.delete(wrapper);
        System.out.println("result => " + result);
    }

    /**
     * 5.根据id批量删除数据
     * DELETE FROM tb_user WHERE id IN ( ? , ? )
     */
    @Test
    public void testDeleteBatchIds() {
        int result = userMapper.deleteBatchIds(Arrays.asList(5L, 22L));
        System.out.println("result => " + result);
    }
    //-------------------------------------------------------------- U -------------------------------------------------------//

    /**
     * 1.测试根据 主键id去更新
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
     * 2.根据条件做更新（用户名）
     * UPDATE tb_user SET password=?, age=? WHERE (user_name = ?)
     */
    @Test
    public void testUpdateByQueryWrapper() {
        User user = new User();
        //更新的字段
        user.setAge(20);
        user.setPassword("8888888");
        //QueryWrapper 仅仅能构造查询（匹配）条件
        //com.baomidou.mybatisplus.core.conditions.Wrapper
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //TODO 注意是数据库字段的名字，而不是对象属性名字
        wrapper.eq("user_name", "zhangsan"); //匹配 user_name = zhangsan 的用户数据
        //根据条件做更新
        int result = this.userMapper.update(user, wrapper);
        System.out.println("result => " + result);
    }

    /**
     * 3.根据条件做更新（用户名）
     * UPDATE tb_user SET age=?,password=? WHERE (user_name = ?)
     */
    @Test
    public void testUpdateByUpdateWrapper() {
        //UpdateWrapper 不经能够构造查询（匹配）条件，还能设置更新的字段
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("age", 21).set("password", "999999") //更新的字段
                .eq("user_name", "zhangsan"); //更新的条件
        //根据条件做更新
        int result = userMapper.update(null, wrapper);
        System.out.println("result => " + result);
    }

    //-------------------------------------------------------------- R -------------------------------------------------------//
    //清空表（truncate table mp.tb_user;）后从新插入数据

    /**
     * 1.根据id查询（自定义的方法）
     * select * from tb_user where id = ?
     */
    @Test
    public void testFindById() {
        System.out.println(userMapper.findById(1L));
    }

    /**
     * 2.根据id查询一条记录
     * SELECT id,user_name,password,name,age,email FROM tb_user WHERE id=?
     */
    @Test
    public void testSelectById() {
        User user = userMapper.selectById(2L);
        System.out.println(user);
    }

    /**
     * 3.根据id批量查询数据
     * SELECT id,user_name,password,name,age,email FROM tb_user WHERE id IN ( ? , ? , ? , ? )
     */
    @Test
    public void testSelectBatchIds() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L, 100L));
        users.forEach(System.out::println);
    }

    /**
     * 4.根据条件查询一条记录
     * SELECT id,user_name,password,name,age,email FROM tb_user WHERE (user_name = ?)
     */
    @Test
    public void testSelectOne() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //查询条件
        //wrapper.eq("password", "123456");  org.apache.ibatis.exceptions.TooManyResultsException: Expected one result (or null) to be returned by selectOne(), but found: 5
        wrapper.eq("user_name", "zhangsan");
        // 查询的数据超过一条时，会抛出异常
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    /**
     * 5.根据条件查询数据条数
     * SELECT COUNT( 1 ) FROM tb_user WHERE (age > ?)
     */
    @Test
    public void testSelectCount() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age", 20); // 条件：年龄大于20岁的用户
        // 根据条件查询数据条数
        Integer count = userMapper.selectCount(wrapper);
        System.out.println("count => " + count);
    }

    /**
     * 6.查询所有
     * SELECT id,user_name,password,name,age,email FROM tb_user
     */
    @Test
    public void testSelectAll() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    /**
     * 7.查询满足条件的集合
     * SELECT id,user_name,password,name,age,email FROM tb_user WHERE (password LIKE ?)
     */
    @Test
    public void testSelectList() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //设置查询条件
        wrapper.like("password", "123456");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }


    //TODO 需要配置plus的分页插件后在使用 (PaginationInterceptor)

    /**
     * 8.测试分页查询
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


    /**
     * 9.QueryWrapper中的allEq用法
     */
    @Test
    public void testAllEq() {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "李四");
        params.put("age", "20");
        params.put("password", null);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //---------------------------------------------------- 用法一 ---------------------------------------------------//
        /**
         * SELECT id,user_name,password,name,age,email FROM tb_user WHERE (password IS NULL AND name = ? AND age = ?)
         */
        wrapper.allEq(params);
        //---------------------------------------------------- 用法二 ---------------------------------------------------//
        /**
         * SELECT id,user_name,password,name,age,email FROM tb_user WHERE (name = ? AND age = ?)
         */
        //wrapper.allEq(params, false);
        //---------------------------------------------------- 用法三（设置过滤） ---------------------------------------------------//
        /**
         * SELECT id,user_name,password,name,age,email FROM tb_user WHERE (age = ?)
         */
        //wrapper.allEq((k, v) -> (k.equals("age") || k.equals("id")) , params);
        //---------------------------------------------------- 用法四 ---------------------------------------------------//
        /**
         * SELECT id,user_name,password,name,age,email FROM tb_user WHERE (name = ? AND age = ?)
         */
        //wrapper.allEq((k, v) -> (k.equals("age") || k.equals("id") || k.equals("name")), params);

        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }


    /**
     * 10.多条件组合查询
     * SELECT id,user_name,password,name,age,email FROM tb_user WHERE (password = ? AND age >= ? AND name IN (?,?,?))
     */
    @Test
    public void testEq() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("password", "123456")
                .ge("age", 20)
                .in("name", "李四", "王五", "赵六");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    /**
     * 11.模糊查询
     */
    @Test
    public void testLike() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //: ==>  Preparing: SELECT id,user_name,password,name,age,email FROM tb_user WHERE (name LIKE ?)
        //: ==> Parameters: %五(String)
        wrapper.likeLeft("name", "五");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    /**
     * 12.排序
     * SELECT id,user_name,password,name,age,email FROM tb_user ORDER BY age DESC
     */
    @Test
    public void testOrderByAgeDesc() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //按照年龄倒序排序
        wrapper.orderByDesc("age");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    /**
     * 13.多条件查询
     * SELECT id,user_name,password,name,age,email FROM tb_user WHERE (name = ? OR age = ?)
     */
    @Test
    public void testOr() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //中间没有 “ .or() ” SQL就时and拼接了
        wrapper.eq("name", "王五").or().eq("age", 21);
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    /**
     * 14.多条件查询
     * SELECT id,name,age FROM tb_user WHERE (name = ? OR age = ?)
     */
    @Test
    public void testSelect() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "王五")
                .or()
                .eq("age", 21)
                .select("id", "name", "age"); //指定查询的字段
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }
}