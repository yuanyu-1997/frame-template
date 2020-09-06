package cn.yuanyu.db.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 商品表
 *
 * @author yuanyu
 */
@Data
public class Item {
    /**
     * 商品id
     */
    private Integer id;
    /**
     * 商品标题
     */
    private String title;
    /**
     * 商品价格（元）
     */
    private Double price;
    /**
     * 库存数量
     */
    private Integer num;
    /**
     * 所属类目，叶子类目
     */
    private Long categoryId;
    /**
     * 商品状态，1-正常，2-下架，3-删除
     */
    private Character status;
    /**
     * 商家ID
     */
    private String sellerId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
