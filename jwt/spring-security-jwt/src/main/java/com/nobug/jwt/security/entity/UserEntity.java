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
 * 用户表
 */
@Getter
@Setter
@Entity(name = "t_user")
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {
    /**
     * 用户id
     */
    @Id
    @Column(length = 64)
    private String id;

    /**
     * 用户名（唯一）
     */
    @Column(unique = true, nullable = false, length = 64)
    private String username;
    /**
     * 密码
     */
    @Column(nullable = false, length = 64)
    private String password;
    /**
     * 用户姓名
     */
    @Column(nullable = false, length = 255)
    private String fullname;
    /**
     * 手机号
     */
    @Column(length = 11)
    private String mobile;

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
     * 配置用户到角色的多对多关系
     */
    @ManyToMany(targetEntity = RoleEntity.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "t_user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Set<RoleEntity> roles = new HashSet<>();



    public UserEntity() {
    }

    public UserEntity(String id, String username, String password, String fullname, String mobile) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.mobile = mobile;
    }

}
