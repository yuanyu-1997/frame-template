package cn.yuanyu.tx.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yuanyu
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -22949137694436362L;
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 邮箱
     */
    private String email;


}