

```bash
docker run \
--name mysql_sso \
--env MYSQL_ROOT_HOST=%.%.%.% \
--env MYSQL_ROOT_PASSWORD=123456 \
-p 36000:3306  \
-di mysql:8.0.18
```

```
jdbc:mysql://121.36.33.154:36000/?serverTimezone=UTC
```





```sql
create database uaa default char set utf8mb4;
use uaa;
```

```sql
DROP TABLE IF EXISTS `sso_access_token`;
CREATE TABLE `sso_access_token`
(
    `id`           int(11)      NOT NULL AUTO_INCREMENT COMMENT 'id',
    `access_token` varchar(255) NOT NULL COMMENT 'Access Token',
    `user_id`      int(11)      NOT NULL COMMENT '关联的用户ID',
    `user_name`    varchar(100) DEFAULT NULL COMMENT '用户名',
    `ip`           varchar(50)  DEFAULT NULL COMMENT '用户来源IP',
    `client_id`    int(11)      DEFAULT NULL,
    `channel`      varchar(50)  DEFAULT '' COMMENT '表示这个Token用于什么渠道，比如：官网、APP1、APP2等等',
    `expires_in`   bigint(11)   NOT NULL COMMENT '过期时间戳',
    `create_user`  int(11)      DEFAULT NULL COMMENT '创建用户',
    `create_time`  datetime     DEFAULT NULL COMMENT '创建时间',
    `update_user`  int(11)      DEFAULT NULL COMMENT '最后更新用户',
    `update_time`  datetime     DEFAULT NULL COMMENT '最后更新时间',
    PRIMARY KEY (`id`)
) COMMENT ='单点登录的Access Token信息表';

DROP TABLE IF EXISTS `sso_client_details`;
CREATE TABLE `sso_client_details`
(
    `id`           int(11)      NOT NULL AUTO_INCREMENT,
    `client_name`  varchar(100)  DEFAULT NULL COMMENT '接入单点登录的系统名称',
    `description`  varchar(1000) DEFAULT NULL,
    `redirect_url` varchar(255) NOT NULL COMMENT '请求Token的回调URL',
    `logout_url`   varchar(255) NOT NULL COMMENT '注销URL',
    `status`       int(2)        DEFAULT NULL COMMENT '0表示未开通；1表示正常使用；2表示已被禁用',
    PRIMARY KEY (`id`),
    UNIQUE KEY (`redirect_url`)
) COMMENT ='单点登录的回调域名的白名单';

DROP TABLE IF EXISTS `sso_refresh_token`;
CREATE TABLE `sso_refresh_token`
(
    `id`            int(11)      NOT NULL AUTO_INCREMENT COMMENT 'id',
    `token_id`      int(11)      NOT NULL COMMENT '表sso_access_token对应的Access Token记录',
    `refresh_token` varchar(255) NOT NULL COMMENT 'Refresh Token',
    `expires_in`    bigint(11)   NOT NULL COMMENT '过期时间戳',
    `create_user`   int(11)  DEFAULT NULL COMMENT '创建用户',
    `create_time`   datetime DEFAULT NULL COMMENT '创建时间',
    `update_user`   int(11)  DEFAULT NULL COMMENT '最后更新用户',
    `update_time`   datetime DEFAULT NULL COMMENT '最后更新时间',
    PRIMARY KEY (`id`)
) COMMENT ='单点登录的Refresh Token信息表';
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


