

```bash
docker run \
--name mysql_mybatis_executor \
--env MYSQL_ROOT_HOST=%.%.%.% \
--env MYSQL_ROOT_PASSWORD=123456 \
-p 45000:3306  \
-di mysql:8.0.18
```

```
jdbc:mysql://121.36.33.154:45000?serverTimezone=UTC
```

```sql
CREATE DATABASE mybatis_bash_db;
USE mybatis_bash_db;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`       int(11) NOT NULL,
    `username` varchar(20) DEFAULT NULL,
    `sex`      varchar(6)  DEFAULT NULL,
    `birthday` date        DEFAULT NULL,
    `address`  varchar(20) DEFAULT NULL,
    `password` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
);
```

