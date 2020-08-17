

```bash
docker run \
--name mysql_oauth2 \
--env MYSQL_ROOT_HOST=%.%.%.% \
--env MYSQL_ROOT_PASSWORD=123456 \
-p 37000:3306  \
-di mysql:8.0.18
```

```
jdbc:mysql://121.36.33.154:37000/?serverTimezone=UTC
```





```sql
create database uaa default char set utf8mb4;
use uaa;
```

```sql
DROP TABLE IF EXISTS `auth_access_token`;
CREATE TABLE `auth_access_token`
(
    `id`           int(11)      NOT NULL AUTO_INCREMENT COMMENT 'id',
    `access_token` varchar(255) NOT NULL COMMENT 'Access Token',
    `user_id`      int(11)      NOT NULL COMMENT '关联的用户ID',
    `user_name`    varchar(100) DEFAULT NULL COMMENT '用户名',
    `client_id`    int(11)      NOT NULL COMMENT '接入的客户端ID',
    `expires_in`   bigint(11)   NOT NULL COMMENT '过期时间戳',
    `grant_type`   varchar(50)  DEFAULT NULL COMMENT '授权类型，比如：authorization_code',
    `scope`        varchar(100) DEFAULT NULL COMMENT '可被访问的用户的权限范围，比如：basic、super',
    `create_user`  int(11)      DEFAULT NULL COMMENT '创建用户',
    `create_time`  datetime     DEFAULT NULL COMMENT '创建时间',
    `update_user`  int(11)      DEFAULT NULL COMMENT '最后更新用户',
    `update_time`  datetime     DEFAULT NULL COMMENT '最后更新时间',
    PRIMARY KEY (`id`)
) COMMENT ='Access Token信息表';

DROP TABLE IF EXISTS `auth_client_details`;
CREATE TABLE `auth_client_details`
(
    `id`            int(11)       NOT NULL AUTO_INCREMENT COMMENT 'id',
    `client_id`     varchar(100)  NOT NULL COMMENT '接入的客户端ID',
    `client_name`   varchar(100)  DEFAULT NULL,
    `client_secret` varchar(255)  NOT NULL COMMENT '接入的客户端的密钥',
    `redirect_uri`  varchar(1000) NOT NULL COMMENT '回调地址',
    `description`   varchar(1000) DEFAULT NULL COMMENT '描述信息',
    `create_user`   int(11)       DEFAULT NULL COMMENT '创建用户',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_user`   int(11)       DEFAULT NULL COMMENT '最后更新用户',
    `update_time`   datetime      DEFAULT NULL COMMENT '最后更新时间',
    `status`        int(2)        DEFAULT '0' COMMENT '0表示未开通；1表示正常使用；2表示已被禁用',
    PRIMARY KEY (`id`)
) COMMENT ='接入的客户端信息表';

DROP TABLE IF EXISTS `auth_client_user`;
CREATE TABLE `auth_client_user`
(
    `id`             int(11) NOT NULL AUTO_INCREMENT,
    `auth_client_id` int(11) NOT NULL,
    `user_id`        int(11) NOT NULL,
    `auth_scope_id`  int(11) NOT NULL,
    PRIMARY KEY (`id`)
) COMMENT ='用户对某个接入客户端的授权信息';
DROP TABLE IF EXISTS `auth_refresh_token`;
CREATE TABLE `auth_refresh_token`
(
    `id`            int(11)      NOT NULL AUTO_INCREMENT COMMENT 'id',
    `token_id`      int(11)      NOT NULL COMMENT '表auth_access_token对应的Access Token记录',
    `refresh_token` varchar(255) NOT NULL COMMENT 'Refresh Token',
    `expires_in`    bigint(11)   NOT NULL COMMENT '过期时间戳',
    `create_user`   int(11)  DEFAULT NULL COMMENT '创建用户',
    `create_time`   datetime DEFAULT NULL COMMENT '创建时间',
    `update_user`   int(11)  DEFAULT NULL COMMENT '最后更新用户',
    `update_time`   datetime DEFAULT NULL COMMENT '最后更新时间',
    PRIMARY KEY (`id`)
) COMMENT ='Refresh Token信息表';

DROP TABLE IF EXISTS `auth_scope`;
CREATE TABLE `auth_scope`
(
    `id`          int(11)      NOT NULL AUTO_INCREMENT,
    `scope_name`  varchar(100) NOT NULL COMMENT '可被访问的用户的权限范围，比如：basic、super',
    `description` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`)
) COMMENT ='可被访问的用户权限表';

```



```sql
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `username`    varchar(100)     DEFAULT NULL,
    `password`    varchar(256)     DEFAULT NULL,
    `mobile`      varchar(30)      DEFAULT NULL,
    `email`       varchar(100)     DEFAULT NULL,
    `create_time` datetime         DEFAULT NULL,
    `update_time` datetime         DEFAULT NULL,
    `status`      int(2)  NOT NULL DEFAULT '1' COMMENT '0表示未开通；1表示正常使用；2表示已被禁用',
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `role_name`   varchar(100) DEFAULT NULL,
    `description` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `func`;
CREATE TABLE `func`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `name`        varchar(100) DEFAULT NULL,
    `description` varchar(100) DEFAULT NULL,
    `code`        varchar(100) DEFAULT NULL,
    `url`         varchar(200) DEFAULT NULL,
    `status`      int(2)       DEFAULT '1' COMMENT '1表示正常使用；2表示已被禁用',
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`
(
    `id`      int(11) NOT NULL AUTO_INCREMENT,
    `user_id` int(11) DEFAULT NULL,
    `role_id` int(11) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `userId` (`user_id`),
    CONSTRAINT `userId` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS `role_func`;
CREATE TABLE `role_func`
(
    `id`      int(11) NOT NULL AUTO_INCREMENT,
    `role_id` int(11) NOT NULL,
    `func_id` int(11) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `roleId` (`role_id`),
    CONSTRAINT `roleId` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);
```





```bash
docker run \
--name redis_uaa \
-p 6000:6379 \
-di redis
```

