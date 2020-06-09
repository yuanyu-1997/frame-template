

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
    `clazz_id` INT,
    `name`     VARCHAR(20),
    `sex`      CHAR(2),
    `age`      TINYINT,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`clazz_id`) REFERENCES tb_clazz (`id`)
);
-- 插入
INSERT INTO tb_clazz(`id`, `code`, `name`)
VALUES (1, '02131704', '软工四班');
INSERT INTO tb_student(`name`, `clazz_id`, `sex`, `age`)
VALUES ('jack', 1, '男', 23),
       ('rose', 1, '女', 18),
       ('tom', 1, '男', 21),
       ('alice', 1, '女', 20);
-- 查询
SELECT * FROM tb_clazz;
SELECT * FROM tb_student;
```

