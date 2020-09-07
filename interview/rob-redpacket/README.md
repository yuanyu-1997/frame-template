

```bash
docker run \
--name mysql_rob_redpacket \
--env MYSQL_ROOT_HOST=%.%.%.% \
--env MYSQL_ROOT_PASSWORD=123456 \
-p 5600:3306  \
-di mysql:8.0.18
```

```bash
jdbc:mysql://121.36.33.154:5600?serverTimezone=UTC
```



```sql
create database rob_redpacket default char set utf8mb4;
use rob_redpacket;
```

```bash
docker run \
--name redis_rob_redpacket \
-p 6300:6379 \
-di redis
```



```sql
DROP TABLE IF EXISTS `red_packet_info`;
CREATE TABLE `red_packet_info`
(
    `id`               int(11)    NOT NULL AUTO_INCREMENT,
    `uid`              int(20)    NOT NULL COMMENT '新建红包用户的用户标识',
    `red_packet_id`    bigint(11) NOT NULL COMMENT '红包id，采用timestamp+5位随机数',
    `total_amount`     int(11)    NOT NULL COMMENT '红包总金额，单位分',
    `total_packet`     int(11)    NOT NULL COMMENT '红包总个数',
    `remaining_amount` int(11)    NOT NULL COMMENT '剩余红包金额，单位分',
    `remaining_packet` int(11)    NOT NULL COMMENT '剩余红包个数',
    `create_time`      timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`      timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='红包信息表，新建一个红包插入一条记录';

DROP TABLE IF EXISTS `red_packet_record`;
CREATE TABLE `red_packet_record`
(
    `id`            int(11)     NOT NULL AUTO_INCREMENT,
    `uid`           int(20)     NOT NULL COMMENT '抢到红包用户的用户标识',
    `red_packet_id` bigint(11)  NOT NULL COMMENT '红包id，采用timestamp+5位随机数',
    `amount`        int(11)     NOT NULL COMMENT '抢到红包的金额',
    `nick_name`     varchar(32) NOT NULL COMMENT '抢到红包的用户的用户名',
    `create_time`   timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='抢红包记录表，抢一个红包插入一条记录';
```



```sql
truncate table red_packet_info;
truncate table red_packet_record;
```



压力测试

 https://www.apachelounge.com/download/ 

 http://httpd.apache.org/docs/2.0/programs/ab.html 

```
ab -n 1000 -c 1000 -p req.txt -T application/json http://localhost:4000/red_packet_info/getPacket/
```

req.txt

```json
{"uid":788,"redPacketId":1599377918367,"nickName":"724c0f9c98bbbb72219fa0"}
```

