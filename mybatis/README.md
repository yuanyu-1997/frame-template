

```bash
docker run \
--name mysql_mybatis_test \
-e MYSQL_ROOT_HOST=%.%.%.% \
-e MYSQL_ROOT_PASSWORD=123456 \
-p 42561:3306  \
-di mysql:8.0.18
```

```
jdbc:mysql://ip:42561/?serverTimezone=UTC
```











```sql
drop database if exists mybatis_test;
create database mybatis_test default char set utf8mb4;
use mybatis_test;
```

