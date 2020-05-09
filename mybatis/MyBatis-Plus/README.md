

```bash
docker run \
--name mp_db \
--env MYSQL_ROOT_HOST=%.%.%.% \
--env MYSQL_ROOT_PASSWORD=123456 \
-p 33600:3306  \
-di mysql:8.0.18
```

```bash
jdbc:mysql://121.36.33.154:33600/?serverTimezone=UTC
```

```sql
-- 创建测试数据库
create database mp;
use mp;
-- 创建测试表
CREATE TABLE `tb_user` (
  `id` BIGINT (20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_name` VARCHAR (20) NOT NULL COMMENT '用户名',
  `password` VARCHAR (20) NOT NULL COMMENT '密码',
  `name` VARCHAR (30) DEFAULT NULL COMMENT '姓名',
  `age` INT (11) DEFAULT NULL COMMENT '年龄',
  `email` VARCHAR (50) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 ;
```

```sql
-- 清空表
truncate table mp.tb_user;
-- 插入测试数据
INSERT INTO `tb_user` (`id`, `user_name`, `password`, `name`, `age`, `email`)
VALUES ('1', 'zhangsan', '123456', '张三', '18', 'zhangsan@bug.cn'),
       ('2', 'lisi', '123456', '李四', '20', 'lisi@bug.cn'),
       ('3', 'wangwu', '123456', '王五', '28', 'wangwu@bug.cn'),
       ('4', 'zhaoliu', '123456', '赵六', '21', 'zhaoliu@bug.cn'),
       ('5', 'sunqi', '123456', '孙七', '24', 'sunqi@bug.cn');
```

```sql
-- 查看表信息
show table status;
-- 修改主键自增的值（大于才生效）
alter table tb_user auto_increment = 20;
```

测试**乐观锁**增加的字段

```sql
-- 添加字段
ALTER TABLE `tb_user` ADD COLUMN `version` int(10) NULL AFTER `email`;
-- 修改以前的记录
UPDATE `tb_user` SET `version`='1';
-- 显示表结构
SHOW CREATE TABLE `tb_user`;
```

测试**逻辑删除**增加的字段

```sql
ALTER TABLE `tb_user` ADD COLUMN `deleted` int(1) NULL DEFAULT 0 COMMENT '1代表删除，0代表未删除' AFTER `version`;
```

测试**通用枚举** 

```sql
ALTER TABLE `tb_user` ADD COLUMN `sex` int(1) NULL DEFAULT 1 COMMENT '1-男，2-女' AFTER `deleted`;
```
