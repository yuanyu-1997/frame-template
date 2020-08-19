package cn.yuanyu.jasonhand.bean;


import java.util.Date;

public class Student {
    private String name;
    private Integer age;
    private Date dateOfGraduation;
    // 默认值
    private String hubby = "学习";
    public Student(String name) {
        this.name = name;
    }
    public Student(){}
    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public Integer getAge(){return age; }
    public void setAge(Integer age) {this.age = age;}
    public String getHubby(){return hubby;}
    public void setHubby(String hubby){this.hubby = hubby;}
    public Date getDateOfGraduation(){return dateOfGraduation; }
    public void setDateOfGraduation(Date dateOfGraduation){this.dateOfGraduation = dateOfGraduation;}
}
