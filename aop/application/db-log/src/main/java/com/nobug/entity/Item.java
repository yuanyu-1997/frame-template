package com.nobug.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 商品表
 *
 * @author yuanyu
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Item {
    /**
     * 商品id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 商品标题
     */
    @Column(nullable = false, length = 100)
    private String title;
    /**
     * 商品价格（元）
     */
    @Column(nullable = false)
    private Double price;
    /**
     * 库存数量
     */
    @Column(nullable = false)
    private Integer num;
    /**
     * 所属类目，叶子类目
     */
    @Column(nullable = false)
    private Long categoryId;
    /**
     * 商品状态，1-正常，2-下架，3-删除
     */
    private Character status;
    /**
     * 商家ID
     */
    @Column(length = 50)
    private String sellerId;
    /**
     * 创建时间
     */
    @CreatedDate
    private Date createTime;
    /**
     * 更新时间
     */
    @LastModifiedDate
    private Date updateTime;

}
