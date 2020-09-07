

```bash
docker run \
--name mysql_probabilistic_data_structure \
--env MYSQL_ROOT_HOST=%.%.%.% \
--env MYSQL_ROOT_PASSWORD=123456 \
-p 5700:3306  \
-di mysql:8.0.18
```

```bash
jdbc:mysql://121.36.33.154:5700?serverTimezone=UTC
```

```sql
create database mysql_probabilistic_data_structure default char set utf8mb4;
use mysql_probabilistic_data_structure;
```



```sql
CREATE TABLE `sys_user`
(
    `id`        int(11) unsigned NOT NULL AUTO_INCREMENT,
    `user_name` varchar(11)      NOT NULL COMMENT '用户名',
    `image`     varchar(11)      NOT NULL DEFAULT '' COMMENT '用户头像',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

insert into sys_user(id, user_name, image)
values (1, '张三', ''),
       (2, '李四', '');
```

