package cn.yuanyu.mybatis.mapper;

import cn.yuanyu.mybatis.entity.Clazz;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ClazzMapperTest {

    @Resource
    private ClazzMapper clazzMapper;

    @Test
    public void selectClazzById() {
        Clazz clazz = clazzMapper.selectClazzById(1);
        System.out.println("\n\n---------------------分割线---------------------\n\n");
        //List<Student> students = clazz.getStudents(); //这里才开始执行查询
    }
}