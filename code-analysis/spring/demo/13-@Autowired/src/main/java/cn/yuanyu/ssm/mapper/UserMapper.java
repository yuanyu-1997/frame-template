package cn.yuanyu.ssm.mapper;

import org.springframework.stereotype.Repository;


@Repository
public class UserMapper {
    private String lable = "defalut";
    public void setLable(String lable) {
        this.lable = lable;
    }
    @Override
    public String toString() {
        return "UserMapper{" + "lable='" + lable + '\'' + '}';
    }
}
