package cn.yuanyu.mybatisexecutor.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 414848905562793591L;
    private Integer id;
    private String username;
    private String sex;
    private Date birthday;
    private String address;
    private String password;
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
