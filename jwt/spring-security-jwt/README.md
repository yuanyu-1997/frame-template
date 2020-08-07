

```bash
docker run \
--name spring_security_jwt \
--env MYSQL_ROOT_HOST=%.%.%.% \
--env MYSQL_ROOT_PASSWORD=123456 \
-p 43399:3306  \
-di mysql:8.0.18
```



```bash
create database spring_security_jwt default char set utf8mb4;
```



登陆

 http://localhost:6969/api/auth/login 

```json
{"username": "zhangsan", "password": "123456","rememberMe":true}
{"username": "lisi", "password": "123456","rememberMe":true}
```

访问资源

```json
http://localhost:6969/api/r1
```

