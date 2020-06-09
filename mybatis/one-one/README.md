

```sql
CREATE TABLE tb_person
(
    `id`       VARCHAR(64) NOT NULL COMMENT '主键id',
    `username` VARCHAR(20) NOT NULL COMMENT '姓名',
    `password` VARCHAR(64) NOT NULL COMMENT '密码',
    `sex`      CHAR(2) COMMENT '性别',
    `age`      TINYINT COMMENT '年龄',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_person_username` (`username`)
) COMMENT = '用户表';
CREATE TABLE tb_card
(
    `person_id` VARCHAR(64) NOT NULL COMMENT '用户id',
    `code`      VARCHAR(18) NOT NULL COMMENT '身份证编号',
    `address`   VARCHAR(100) COMMENT '住址',
    PRIMARY KEY (`person_id`),
    FOREIGN KEY (person_id) REFERENCES tb_person (id)
);
--
INSERT INTO tb_person(`id`, `username`, `password`, `sex`, `age`)
VALUES ('8848', 'jack', '123456', '男', 23);
INSERT INTO tb_card(`person_id`, `code`, `address`)
VALUES ('8848', '432801198009191038', 'Xxx市Xxx区Xxx街道Xxx村Xxx组Xxx号');
```

![relation](./relation.png)