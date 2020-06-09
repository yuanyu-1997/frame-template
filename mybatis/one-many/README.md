

```sql
DROP TABLE IF EXISTS tb_clazz;
CREATE TABLE tb_clazz
(
    `id`   INT AUTO_INCREMENT,
    `code` VARCHAR(64),
    `name` VARCHAR(12),
    PRIMARY KEY (`id`)
);
DROP TABLE IF EXISTS tb_student;
CREATE TABLE tb_student
(
    `id`       INT AUTO_INCREMENT,
    `name`     VARCHAR(20),
    `sex`      CHAR(2),
    `age`      TINYINT,
    `clazz_id` INT,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`clazz_id`) REFERENCES tb_clazz (`id`)
);
-- 插入
INSERT INTO tb_clazz(`code`, `name`)
VALUES ('02131704', '软工四班');
INSERT INTO tb_student(`name`, `sex`, `age`, `clazz_id`)
VALUES ('jack', '男', 23, 1),
       ('rose', '女', 18, 1),
       ('tom', '男', 21, 1),
       ('alice', '女', 20, 1);
-- 查询
SELECT * FROM tb_clazz;
SELECT * FROM tb_student;
```

