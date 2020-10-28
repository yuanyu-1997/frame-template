```bash
docker run \
--name mysql_quartz \
--env MYSQL_ROOT_HOST=%.%.%.% \
--env MYSQL_ROOT_PASSWORD=123456 \
-p 34000:3306  \
--rm \
-di mysql:8.0.18
```

```
jdbc:mysql://120.79.202.181:34000?serverTimezone=UTC
```



```sql
CREATE DATABASE quartz DEFAULT CHAR SET utf8mb4;
USE quartz;
```

