package com.nobug.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 品牌表
 */
@Data
@Entity
public class Brand {
    /**
     * 品牌id
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /**
     * 品牌名称
     */
    @Column(length = 255)
    private String name;
    /**
     * 品牌首字母
     */
    private Character firstChar;

}
