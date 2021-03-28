

```sql
docker run \
--name easycode_db \
-e MYSQL_ROOT_HOST=%.%.%.% \
-e MYSQL_ROOT_PASSWORD=123456 \
-p 6033:3306  \
-di mysql:8.0.18
```







```sql
-- 创建数据库
DROP DATABASE IF EXISTS `mybatis_db`;
CREATE DATABASE IF NOT EXISTS `mybatis_db` CHARSET utf8mb4;
USE `mybatis_db`;
-- 创建测试表
CREATE TABLE `user`
(
    `id`       INT(10) UNSIGNED AUTO_INCREMENT COMMENT '主键ID',
    `username` VARCHAR(20) NOT NULL COMMENT '用户名',
    `password` VARCHAR(64) NOT NULL COMMENT '密码',
    `name`     VARCHAR(20) COMMENT '姓名',
    `age`      TINYINT UNSIGNED COMMENT '年龄',
    `email`    VARCHAR(50) COMMENT '邮箱',
    PRIMARY KEY `id` (`id`),
    UNIQUE KEY `uk_user_username` (`username`)
);
-- 插入测试数据
INSERT INTO `user` (`id`, `username`, `password`, `name`, `age`, `email`)
VALUES ('1', 'zhangsan', '123456', '张三', '18', 'zhangsan@bug.cn'),
       ('2', 'lisi', '123456', '李四', '20', 'lisi@bug.cn'),
       ('3', 'wangwu', '123456', '王五', '28', 'wangwu@bug.cn'),
       ('4', 'zhaoliu', '123456', '赵六', '21', 'zhaoliu@bug.cn'),
       ('5', 'sunqi', '123456', '孙七', '24', 'sunqi@bug.cn');
```

