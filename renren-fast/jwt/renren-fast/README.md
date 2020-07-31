



```bash
docker run \
--name renren_fast \
--env MYSQL_ROOT_HOST=%.%.%.% \
--env MYSQL_ROOT_PASSWORD=123456 \
-p 3000:3306  \
-di mysql:8.0.18
```



```bash
jdbc:mysql://39.106.196.224:3000/?serverTimezone=Asia/Shanghai
```

```sql
create database renren_fast default char set utf8mb4;
use renren_fast;
```

```sql
-- 用户表
CREATE TABLE `tb_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `password` varchar(64) COMMENT '密码',
  `create_time` datetime COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX (`username`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8mb4 COMMENT='用户';
```

