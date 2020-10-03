-- 创建数据库
CREATE DATABASE mybatis_spring;
USE mybatis_spring;
-- 创建测试表
CREATE TABLE `users`
(
    `id`       INT(10) UNSIGNED AUTO_INCREMENT COMMENT '主键ID',
    `username` VARCHAR(20) NOT NULL COMMENT '用户名',
    `password` VARCHAR(64) NOT NULL COMMENT '密码',
    PRIMARY KEY `id` (`id`),
    UNIQUE KEY `uk_user_username` (`username`)
);
-- 插入测试数据
INSERT INTO `users` (`id`, `username`, `password`)
VALUES (1, 'zhangsan', '123456'),
       (2, 'lisi', '123456');