



```sql
CREATE TABLE t_dept
(
    `id`        VARCHAR(64) NOT NULL COMMENT '部门id',
    `dept_name` VARCHAR(255) COMMENT '部门名字',
    UNIQUE KEY `uk_dept_name` (`dept_name`),
    PRIMARY KEY (`id`)
) DEFAULT CHARSET = utf8mb4 COMMENT = '部门表';
CREATE TABLE t_employee
(
    `id`       VARCHAR(64) NOT NULL COMMENT '主键id',
    `d_id`     VARCHAR(64) NOT NULL COMMENT '部门id',
    `username` VARCHAR(255) COMMENT '姓名',
    `gender`   CHAR(1) COMMENT '性别',
    `email`    VARCHAR(255) COMMENT '邮箱',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_employee_username` (`username`),
    KEY `fk_emp_dept` (`d_id`),
    CONSTRAINT `fk_emp_dept` FOREIGN KEY (`d_id`) REFERENCES `t_dept` (`id`)
) DEFAULT CHARSET = utf8mb4 COMMENT = '员工表';
```



```sql
-- 插入
INSERT INTO t_dept(`id`, `dept_name`)
VALUES ('8848', '开发部'),
       ('6699', '测试部');
INSERT INTO t_employee(`id`, `d_id`, `username`, `gender`, `email`)
VALUES ('1', '8848', 'tom', '0', 'tom@bug.com');
-- 查询
SELECT * FROM t_employee;
SELECT * FROM t_dept;
```



![relation](./relation.png)