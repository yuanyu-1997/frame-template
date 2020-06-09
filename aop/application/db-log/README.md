```sql
docker run \
--name shopping_mall \
--env MYSQL_ROOT_HOST=%.%.%.% \
--env MYSQL_ROOT_PASSWORD=123456 \
-p 33060:3306  \
-di mysql:8.0.18
```

```sql
jdbc:mysql://39.106.196.224:33060/?serverTimezone=UTC
```

```sql
CREATE DATABASE shopping_mall DEFAULT CHARSET = utf8mb4;
USE shopping_mall;
```

```sql
show create table brand;
show create table item;
show create table user;
show create table operation_log;
```

