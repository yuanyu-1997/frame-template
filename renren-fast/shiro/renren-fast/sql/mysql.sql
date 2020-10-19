CREATE DATABASE IF NOT EXISTS ren_ren_fast_shiro;
USE ren_ren_fast_shiro;

DROP DATABASE if exists `sys_user`;
CREATE TABLE `sys_user`
(
    `id`          VARCHAR(64)  NOT NULL COMMENT '用户ID',
    `username`    varchar(20)  NOT NULL COMMENT '用户名',
    `password`    varchar(100) NOT NULL COMMENT '密码',
    `salt`        varchar(20) DEFAULT NULL COMMENT '盐加密（为null说明是明文存储，需要升级为密文）',
    `status`      tinyint     DEFAULT 1 COMMENT '状态（0禁用、1正常）',
    `create_time` TIMESTAMP   DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
    `update_time` TIMESTAMP   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_username` (`username`)
) DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';

DROP DATABASE if exists `sys_role`;
CREATE TABLE `sys_role`
(
    `id`          VARCHAR(64) NOT NULL COMMENT '角色ID',
    `role_name`   varchar(20)  DEFAULT NULL COMMENT '角色名',
    `role_desc`   varchar(250) DEFAULT NULL COMMENT '角色说明',
    `create_time` TIMESTAMP    DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
    `update_time` TIMESTAMP    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
) DEFAULT CHARSET = utf8mb4 COMMENT ='角色表';

DROP DATABASE if exists `sys_permission`;
CREATE TABLE `sys_permission`
(
    `id`          VARCHAR(64) NOT NULL COMMENT '权限ID',
    `perm_tag`    varchar(50)  DEFAULT NULL COMMENT '权限标识符',
    `perm_desc`   varchar(250) DEFAULT NULL COMMENT '权限说明',
    `create_time` TIMESTAMP    DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
    `update_time` TIMESTAMP    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
) DEFAULT CHARSET = utf8mb4 COMMENT ='权限表';

DROP DATABASE if exists `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `user_id`     VARCHAR(64) NOT NULL COMMENT '用户ID',
    `role_id`     VARCHAR(64) NOT NULL COMMENT '角色ID',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
    `update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`user_id`, `role_id`),
    CONSTRAINT `fk_userRole_userId` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `fk_userRole_roleId` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) DEFAULT CHARSET = utf8mb4 COMMENT ='用户角色表';

DROP DATABASE if exists `sys_role_permission`;
CREATE TABLE `sys_role_permission`
(
    `role_id`     VARCHAR(64) NOT NULL COMMENT '角色ID',
    `perm_id`     VARCHAR(64) NOT NULL COMMENT '权限ID',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
    `update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`role_id`, `perm_id`),
    CONSTRAINT `fk_rolePermission_roleId` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `fk_rolePermission_permId` FOREIGN KEY (`perm_id`) REFERENCES `sys_permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) DEFAULT CHARSET = utf8mb4 COMMENT ='角色权限表';


-- ----------------------------
-- 用户表 123456
-- ----------------------------
INSERT INTO `sys_user`(id, username, salt, password)
VALUES ('1', '张三', 'e3f9fdf61f5d42bc84db', 'u0h5xlqUh44ccogtzsP0t7L1yAe2mi3OxmtDyLyvZ0c='),
       ('2', '歆瑶', 'e3f9fdf61f5d42bc84db', 'u0h5xlqUh44ccogtzsP0t7L1yAe2mi3OxmtDyLyvZ0c=');
-- ----------------------------
-- 角色表
-- ----------------------------
INSERT INTO `sys_role`(id, role_name, role_desc)
VALUES ('100', '辅导员', '超级管理员，拥有所有权限'),
       ('200', '班长', '查询权限');
-- ----------------------------
-- 权限表
-- ----------------------------
INSERT INTO `sys_permission`(id, perm_tag, perm_desc)
VALUES ('1000', 'student:add', '增加学生权限'),
       ('2000', 'student:delete', '删除学生权限'),
       ('3000', 'student:update', '修改学生权限'),
       ('4000', 'student:list', '查看学生列表权限'),
       ('5000', 'student:select', '查看学生权限');

-- ----------------------------
-- 角色权限表
-- ----------------------------
-- 辅导员拥有所有权限
insert into sys_role_permission(role_id, perm_id)
select r.id rID, p.id pID
from sys_role r,
     sys_permission p
where r.role_name = '辅导员';
-- 班长拥有查询权限
insert into sys_role_permission(role_id, perm_id)
select r.id rID, p.id pID
from sys_role r,
     sys_permission p
where r.role_name = '班长'
  and (p.perm_tag = 'student:select' or p.perm_tag = 'student:list');
-- ----------------------------
-- 用户角色表
-- ----------------------------
-- 歆瑶是辅导员
insert into sys_user_role(user_id, role_id)
select u.id uID, r.id rID
from sys_user u
         join sys_role r on r.role_name = '辅导员' and u.username = '歆瑶';
-- 张三是班长
insert into sys_user_role(user_id, role_id)
select u.id uID, r.id rID
from sys_user u
         join sys_role r on r.role_name = '班长' and u.username = '张三';




DROP DATABASE if exists `sys_user_token`;
-- 系统用户Token
CREATE TABLE `sys_user_token`
(
    `user_id`     bigint(20)   NOT NULL,
    `token`       varchar(100) NOT NULL COMMENT 'token',
    `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `token` (`token`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8mb4 COMMENT ='系统用户Token';