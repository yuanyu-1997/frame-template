docker创建一个mysql容器
```sql
docker run \
--name multi_ds_db \
--env MYSQL_ROOT_HOST=%.%.%.% \
--env MYSQL_ROOT_PASSWORD=123456 \
-p 33006:3306  \
-di mysql:8.0.18
```
连接mysql
```
jdbc:mysql://120.25.216.234:33006/?serverTimezone=UTC
```
创建两个数据库
```
create database order_db;
create database user_db;
```

每个数据库插入一张表

```sql
CREATE TABLE `user_db`.`t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `age` int(11) DEFAULT NULL,
  `hubby` varchar(255) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_g8gqk4e142wekcb1t6d3v2mwx` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `order_db`.`t_order`
(
    `id`          varchar(64) NOT NULL,
    `create_time` datetime     DEFAULT NULL,
    `description` varchar(255) DEFAULT NULL,
    `modify_time` datetime     DEFAULT NULL,
    `user_id`     bigint(20)   DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
```

```sql
select * from `order_db`.`t_order`;
select * from `user_db`.`t_user`;
```

```sql
show create table `user_db`.`t_user`;
show create table `order_db`.`t_order`;
```



**案例还存在问题**，如果采用spirngdata自动创建表，这个时候创建的两张表在一张数据库去了，repository切入点表达式也无法切入进去

