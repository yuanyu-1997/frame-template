

```bash
docker run \
--name mybatis_db \
-e MYSQL_ROOT_HOST=%.%.%.% \
-e MYSQL_ROOT_PASSWORD=123456 \
-p 6699:3306  \
-di mysql:8.0.18
```

```
jdbc:mysql://ip:6699/?serverTimezone=UTC
```



```sql
CREATE DATABASE mybatis_db;
USE mybatis_db;
```

