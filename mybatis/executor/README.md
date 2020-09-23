```sql
CREATE DATABASE mybatis_bash_test_db;
USE mybatis_bash_test_db;

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

