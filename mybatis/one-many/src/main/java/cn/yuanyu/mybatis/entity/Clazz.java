package cn.yuanyu.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

/**
 * @author yuanyu
 */
@Data
public class Clazz {
    /**
     * 班级id，主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 班级编号
     */
    private String code;
    /**
     * 班级名称
     */
    private String name;

    /**
     * 班级和学生是一对多的关系，即一个班级可以有多个学生
     */
    private List<Student> students;

}
