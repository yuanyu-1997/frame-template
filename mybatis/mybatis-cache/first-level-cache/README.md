

```bash
jdbc:mysql://121.36.33.154:42561?serverTimezone=UTC
```



```sql
-- 库
drop database if exists mybatis;
create database mybatis;
use mybatis;
-- 表
drop table if exists tb_employee;
CREATE TABLE tb_employee
(
    `id`         INT(11) PRIMARY KEY AUTO_INCREMENT,
    `login_name` VARCHAR(18),
    `password`   VARCHAR(18),
    `name`       VARCHAR(18) DEFAULT NULL,
    `sex`        CHAR(2)     DEFAULT NULL,
    `age`        INT(11)     DEFAULT NULL,
    `phone`      VARCHAR(21),
    `sal`        DOUBLE,
    `state`      VARCHAR(18)
) default char set utf8mb4;
-- 数据
INSERT INTO tb_employee(login_name, password, NAME, sex, age, phone, sal, state)
VALUES ('jack', '123456', '杰克', '男', 26, '13902019999', 9800, 'ACTIVE');
INSERT INTO tb_employee(login_name, password, NAME, sex, age, phone, sal, state)
VALUES ('rose', '123456', '露丝', '女', 21, '13902018888', 6800, 'ACTIVE');
-- 查询
select * from tb_employee;
```

