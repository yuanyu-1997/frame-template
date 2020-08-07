package com.nobug.jwt.security.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * 权限表
 */
@Getter
@Setter
@Entity(name = "t_permission")
@EntityListeners(AuditingEntityListener.class)
public class PermissionEntity {
    /**
     * 权限id
     */
    @Id
    @Column(length = 32)
    private String id;
    /**
     * 权限标识符
     */
    @Column(length = 32, nullable = false)
    private String code;
    /**
     * 描述
     */
    @Column(length = 64)
    private String description;
    /**
     * 请求地址
     */
    @Column(length = 128)
    private String url;

    /**
     *
     */
    @CreatedDate
    private Date createTime;
    /**
     *
     */
    @LastModifiedDate
    private Date updateTime;


    /**
     *
     */
    @ManyToMany(mappedBy = "permissions", fetch = FetchType.EAGER)
    private Set<RoleEntity> roles = new HashSet<>();

    public PermissionEntity() {
    }

    public PermissionEntity(String id, String code, String description, String url) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.url = url;
    }

}
