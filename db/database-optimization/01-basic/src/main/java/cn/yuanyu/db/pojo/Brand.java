package cn.yuanyu.db.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 品牌表
 */
@Data
public class Brand {
    /**
     * 品牌id
     */
    private Long id;
    /**
     * 品牌名称
     */
    private String name;

    /**
     * 品牌首字母
     */
    private Character firstChar;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
