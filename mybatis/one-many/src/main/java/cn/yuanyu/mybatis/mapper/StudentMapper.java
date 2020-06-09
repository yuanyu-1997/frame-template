package cn.yuanyu.mybatis.mapper;


import cn.yuanyu.mybatis.entity.Student;

/**
 * @author yuanyu
 */
public interface StudentMapper {
    /**
     * 根据id查询学生信息，班级信息也要查询出来
     *
     * @param id 学生id
     * @return 学生
     */
    Student selectStudentById(Integer id);
}
