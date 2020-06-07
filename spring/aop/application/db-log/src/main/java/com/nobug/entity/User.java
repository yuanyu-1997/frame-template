package com.nobug.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户表
 */
@Data
@Entity
public class User {
    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     *
     */
    @Column(nullable = false, length = 45)
    private String username;
    /**
     *
     */
    @Column(nullable = false, length = 96)
    private String password;
    /**
     *
     */
    @Column(nullable = false,length = 45)
    private String name;
    /**
     *
     */
    private Date birthday;
    /**
     *
     */
    private Character sex;
    /**
     *
     */
    @Column(length = 45)
    private String email;
    /**
     *
     */
    @Column(length = 45)
    private String phone;
    /**
     *
     */
    @Column(length = 32)
    private String qq;

}
