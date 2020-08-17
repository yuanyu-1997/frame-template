package cn.yuanyu.ssoclient.model;

import lombok.Data;

@Data
public class Func {
    private Integer id;
    private String name;
    private String description;
    private String code;
    private String url;
    private Integer status;
}