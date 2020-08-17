package cn.yuanyu.redisboot.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private String name;
    private String age;
    public User() {}
    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }
}
