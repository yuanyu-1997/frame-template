```bash
docker run \
--name tx_db \
-e MYSQL_ROOT_HOST=%.%.%.% \
-e MYSQL_ROOT_PASSWORD=123456 \
-p 6000:3306  \
-di mysql:8.0.18
```

```bash
jdbc:mysql://121.36.33.154:6000?serverTimezone=Asia/Shanghai
```

```sql
-- 创建数据库
CREATE DATABASE tx CHARSET=utf8mb4;
USE tx;
-- 创建表
CREATE TABLE account(
  `id`    INT(11) NOT NULL AUTO_INCREMENT,
  `name`  VARCHAR(40),
  `money` FLOAT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_account_name` (`name`) 
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 ;
-- 插入数据
INSERT INTO account(`name`,money) VALUES('张三',1000);
INSERT INTO account(`name`,money) VALUES('李四',1000);
```

```sql
-- 重置数据
TRUNCATE TABLE account;
INSERT INTO account(`name`,money) VALUES('张三',1000);
INSERT INTO account(`name`,money) VALUES('李四',1000);
-- 查询
SELECT * FROM account;
```



在Spring Boot中是不需要加 **@EnableTransactionManagement** 注解的 https://yuanyu.blog.csdn.net/article/details/106597952 