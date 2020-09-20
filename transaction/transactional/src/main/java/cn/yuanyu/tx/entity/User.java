package cn.yuanyu.tx.entity;

import lombok.Data;

/**
 * @author yuanyu
 */
@Data
public class User {
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

    public User() {
    }



    public User(String username, String password, String name, Integer age, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.email = email;
    }
}