package cn.yuanyu.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author yuanyu
 */
@Data
public class Person {
    /**
     * 主键id
     */
    @TableId(type = IdType.INPUT)
    private Integer id;
    /**
     * 姓名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
    /**
     * 性别
     */
    private String sex;
    /**
     * 年龄
     */
    private Integer age;

    /**
     * 人和身份证是一对一的关系，即一个人只有一个身份证
     */
    private Card card;
}
