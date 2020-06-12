package cn.yuanyu.userapi.bean;

import lombok.Data;

/**
 * @author yuanyu
 */
@Data
public class Person {
    private String name;
    private Integer age;
    public Person() {}
    public Person(String name, Integer age) {this.name = name;this.age = age;}
}
