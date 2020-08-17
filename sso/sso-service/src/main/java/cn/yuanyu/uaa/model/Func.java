package cn.yuanyu.uaa.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Func {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     *
     */
    private String name;
    /**
     *
     */
    private String description;
    /**
     *
     */
    private String code;
    /**
     *
     */
    private String url;
    /**
     *
     */
    private Integer status;
}