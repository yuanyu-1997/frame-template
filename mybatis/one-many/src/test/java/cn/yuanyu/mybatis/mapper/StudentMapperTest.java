package cn.yuanyu.mybatis.mapper;

import cn.yuanyu.mybatis.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@SpringBootTest
@RunWith(SpringRunner.class)
public class StudentMapperTest {

    @Resource
    private StudentMapper studentMapper;


    @Test
    public void selectStudentById() {
        Student student = studentMapper.selectStudentById(1);

    }
}