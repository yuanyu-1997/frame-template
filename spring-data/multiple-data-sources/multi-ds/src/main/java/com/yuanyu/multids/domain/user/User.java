package com.yuanyu.multids.domain.user;

import lombok.Data;

import javax.persistence.*;

/**
 * @author yuanyu
 */
@Data
@Entity(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     *
     */
    @Column(length = 20, unique = true)
    private String name;

    /**
     *
     */
    private Integer age;

    /**
     *
     */
    @Column(length = 255)
    private String hubby;

    public User() {
    }

    public User(String name, Integer age, String hubby) {
        this.name = name;
        this.hubby = hubby;
        this.age = age;
    }

}
