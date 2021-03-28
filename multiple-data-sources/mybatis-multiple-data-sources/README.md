

```bash
docker run \
--name mysql_db1 \
--env MYSQL_ROOT_HOST=%.%.%.% \
--env MYSQL_ROOT_PASSWORD=123456 \
-p 33000:3306  \
--rm \
-di mysql:8.0.18
```

```
jdbc:mysql://121.36.33.154:33000?serverTimezone=UTC
```

```sql
-- 建库
CREATE DATABASE db_user;
USE db_user;
-- 建表
CREATE TABLE `tb_user`
(
    `id`        INT(10) UNSIGNED AUTO_INCREMENT COMMENT '用户ID',
    `username`  VARCHAR(20) NOT NULL COMMENT '用户名',
    `password`  VARCHAR(64) NOT NULL COMMENT '密码',
    `nick_name` VARCHAR(20) COMMENT '昵称',
    PRIMARY KEY `id` (`id`),
    UNIQUE KEY `uk_user_username` (`username`)
) DEFAULT CHARSET = utf8mb4 COMMENT '用户表';
-- 插入测试数据
INSERT INTO `tb_user` (`username`, `password`, `nick_name`)
VALUES ('zhangsan', '123456', '张三'),
       ('lisi', '123456', '李四');
```





```bash
docker run \
--name mysql_db2 \
--env MYSQL_ROOT_HOST=%.%.%.% \
--env MYSQL_ROOT_PASSWORD=123456 \
-p 34000:3306  \
--rm \
-di mysql:8.0.18
```

```
jdbc:mysql://120.79.202.181:34000?serverTimezone=UTC
```

```sql
-- 建库
CREATE DATABASE db_live;
USE db_live;
-- 建表
CREATE TABLE `tb_video`
(
    `id`   int(10)      NOT NULL AUTO_INCREMENT COMMENT '视频ID',
    `uid`  int(10)      NOT NULL COMMENT '用户ID',
    `path` VARCHAR(255) NOT NULL DEFAULT '0' COMMENT '视频相对路径',
    PRIMARY KEY (`id`)
) DEFAULT CHARSET = utf8mb4 COMMENT '视频表';
```

