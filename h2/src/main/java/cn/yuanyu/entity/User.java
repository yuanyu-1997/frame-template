package cn.yuanyu.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author yuanyu
 */
@Data
@Entity
public class User {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 用户名
     */
    @Column(unique = true, length = 20)
    private String username;
    /**
     * 密码
     */
    @Column(length = 64)
    private String password;
    /**
     * 姓名
     */
    @Column(length = 20)
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 邮箱
     */
    @Column(length = 50)
    private String email;

    public User() {
    }

    public User(Long id, String username, String password, String name, Integer age, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.email = email;
    }
}