package com.nobug.multids.entity;

import lombok.Data;

import java.util.Date;


@Data
public class User {
    /**
     *
     */
    private Integer id;
    /**
     *
     */
    private String username;
    /**
     *
     */
    private String password;
    /**
     *
     */
    private String sex;
    /**
     *
     */
    private Date birthday;
    /**
     *
     */
    private String address;

    public User() {
    }

    public User(Integer id, String username, String sex, Date birthday, String address, String password) {
        this.id = id;
        this.username = username;
        this.sex = sex;
        this.birthday = birthday;
        this.address = address;
        this.password = password;
    }
}