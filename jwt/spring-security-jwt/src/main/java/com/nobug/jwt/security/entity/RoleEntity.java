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
 * 角色表
 */
@Getter
@Setter
@Entity(name = "t_role")
@EntityListeners(AuditingEntityListener.class)
public class RoleEntity {
    /**
     * 角色id
     */
    @Id
    @Column(length = 32)
    private String id;

    /**
     *
     */
    @Column(unique = true, length = 255)
    private String roleName;
    /**
     *
     */
    @Column(length = 255)
    private String description;
    /**
     *
     */
    private Character status;
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
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<UserEntity> users = new HashSet<>();


    /**
     * 配置角色到权限的多对多关系
     */
    @ManyToMany(targetEntity = PermissionEntity.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "t_role_permission",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id", referencedColumnName = "id")}
    )
    private Set<PermissionEntity> permissions = new HashSet<>();


    public RoleEntity() {
    }

    public RoleEntity(String id, String roleName, String description, Character status) {
        this.id = id;
        this.roleName = roleName;
        this.description = description;
        this.status = status;
    }
}
