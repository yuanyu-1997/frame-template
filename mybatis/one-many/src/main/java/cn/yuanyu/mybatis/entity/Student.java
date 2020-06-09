package cn.yuanyu.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author yuanyu
 */
@Data
public class Student {

    /**
     * 学生id，主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 年龄
     */
    private Integer age;

    /**
     * 学生和班级是多对一的关系，即一个学生只属于一个班级
     */
    private Clazz clazz;
}
