# 【master】120.25.216.234

使用docker创建一个mysql容器

```bash
docker run \
--name mysql_master \
--env MYSQL_ROOT_HOST=%.%.%.% \
--env MYSQL_ROOT_PASSWORD=123456 \
-p 33060:3306  \
-di mysql:8.0.18
```

进入容器并修改配置文件

```bash
### 拷贝MySQL容器中的配置文件到宿主机
docker cp mysql_master:/etc/mysql/my.cnf master_my.cnf
### 修改MySQL配置文件
vi master_my.cnf
```

```properties
#mysql 服务ID，保证整个集群环境中唯一
server-id=1
#mysql binlog 日志的存储路径和文件名
log-bin=/var/lib/mysql/mysqlbin
#是否只读，1 代表只读，0 代表读写
read-only=0
#忽略的数据，指不需要同步的数据库
binlog-ignore-db=mysql
```

![my.cnf](./img/master-my.cnf.png)

```bash
### 将文件拷贝到容器中，替换原有的my.cnf
docker cp master_my.cnf mysql_master:/etc/mysql/my.cnf
### 确认是否修改成功
docker exec -it mysql_master bash
cat /etc/mysql/my.cnf
exit
```

后重启容器

```bash
docker restart mysql_master
```

使用远程工具连接配置好的master

```bash
jdbc:mysql://120.25.216.234:33060/?serverTimezone=UTC
```

创建同步数据的账户，并且进行授权操作（注意**ip地址为slave数据库的ip地址**）

```sql
CREATE USER 'sync'@'121.36.33.154' IDENTIFIED WITH mysql_native_password BY '123456';
GRANT REPLICATION SLAVE ON *.* TO 'sync'@'121.36.33.154';
FLUSH PRIVILEGES;
```

查看master状态

```bash
mysql> SHOW MASTER STATUS;
+-----------------+----------+--------------+------------------+-------------------+
| File            | Position | Binlog_Do_DB | Binlog_Ignore_DB | Executed_Gtid_Set |
+-----------------+----------+--------------+------------------+-------------------+
| mysqlbin.000001 |     1094 |              | mysql            |                   |
+-----------------+----------+--------------+------------------+-------------------+
1 row in set (0.00 sec)
```

# 【slave】121.36.33.154

创建docker容器
```bash
docker run \
--name mysql_slave \
--env MYSQL_ROOT_HOST=%.%.%.% \
--env MYSQL_ROOT_PASSWORD=123456 \
-p 33000:3306  \
-di mysql:8.0.18
```
和前面一样修改容器里面的配置文件
```bash
### 拷贝MySQL容器中的配置文件到宿主机
docker cp mysql_slave:/etc/mysql/my.cnf slave_my.cnf
### 修改MySQL配置文件
vi slave_my.cnf
```
```properties
#mysql服务端ID,唯一
server-id=2
#指定binlog日志
log-bin=/var/lib/mysql/mysqlbin
```

![my.cnf](./img/slave-my.cnf.png)

```bash
### 将文件拷贝到容器中，替换原有的my.cnf
docker cp slave_my.cnf mysql_slave:/etc/mysql/my.cnf
### 确认是否修改成功
docker exec -it mysql_slave bash
cat /etc/mysql/my.cnf
exit
```

重启容器

```bash
docker restart mysql_slave
```

使用远程工具连接配置好的slave

```
jdbc:mysql://121.36.33.154:33000/?serverTimezone=UTC
```

配置同步（注意**ip地址是master的ip地址**）

```sql
CHANGE MASTER TO 
MASTER_HOST = '120.25.216.234',
MASTER_PORT = 33060,
MASTER_USER = 'sync',
MASTER_PASSWORD = '123456',
MASTER_LOG_FILE = 'mysqlbin.000001',
MASTER_LOG_POS = 1094 ;
```

开启同步操作

```sql
START SLAVE;
```

```sql
SHOW SLAVE STATUS \G
```

![my.cnf](./img/slave-status.png)

停止同步操作

```sql
STOP SLAVE;
```

在slave创建一个只有查询权限的用户，可以避免很多问题发生

```bash
CREATE USER  'tms_select'@'%.%.%.%' IDENTIFIED WITH mysql_native_password BY '123456';
GRANT select ON *.* TO 'tms_select'@'%.%.%.%';
FLUSH PRIVILEGES;
```

# 测试主从同步

在master创建表并插入数据

 https://blog.csdn.net/qq_40794973/article/details/104831325 

````sql
CREATE DATABASE master_slave_test;
USE master_slave_test;
CREATE TABLE `tb_user`
(
    `id`       INT(10) UNSIGNED NOT NULL,
    `username` varchar(20)      NOT NULL,
    `password` varchar(50)      NOT NULL,
    `sex`      varchar(2)       NOT NULL,
    `birthday` date             NOT NULL,
    `address`  varchar(100)     NOT NULL DEFAULT '',
    PRIMARY KEY (`id`)
);
INSERT INTO `tb_user`(`id`, `username`, `password`, `sex`, `birthday`, `address`)
VALUES (1, '蔡徐坤', '123', '男', NOW(), 'Xxxx'),
       (2, '乔碧萝', '321', '女', NOW(), 'Xxxx'),
       (3, '卢本伟', '456', '男', NOW(), 'Xxxx');
SELECT * FROM `tb_user`;
````

在slave即可查看master创建的数据库和表以及插入的数据

```sql
SELECT * FROM master_slave_test.`tb_user`;
```



```
【master】jdbc:mysql://120.25.216.234:33060/master_slave_test?serverTimezone=UTC
【slave】jdbc:mysql://121.36.33.154:33000/master_slave_test?serverTimezone=UTC
```

# 主从复制失败

```bash
stop slave;
reset master;
```

https://blog.csdn.net/abcwanglinyong/article/details/96563085 

配置好主从复制后如果**删除从表记录**，会造成主从复制失败；

![my.cnf](./img/sync-err.png)



 https://www.oschina.net/question/2398274_2262571 

```properties
log-error=/usr/local/mysql/data/mysql.log  
```





```bash
docker stop mysql_master && docker rm mysql_master;
docker stop mysql_slave && docker rm mysql_slave;
```

