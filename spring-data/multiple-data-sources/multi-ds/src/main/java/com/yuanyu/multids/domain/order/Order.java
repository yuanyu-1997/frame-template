package com.yuanyu.multids.domain.order;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.util.Date;

/**
 * 订单
 *
 * @author yuanyu
 */
@Data
@Entity(name = "t_order")
@EntityListeners(AuditingEntityListener.class)
public class Order {
    /**
     * 订单id
     */
    @Id
    @Column(length = 64, unique = true)
    private String id;

    /**
     * 订单详情
     */
    @Column(length = 255)
    private String description;

    /**
     * 用户id
     */
    private Long userId;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date modifyTime;

    public Order() {
    }

    public Order(String id, String description, Long userId) {
        this.id = id;
        this.description = description;
        this.userId = userId;
    }

}
