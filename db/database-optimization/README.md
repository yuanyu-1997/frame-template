# 创建数据库

```sql
docker run \
--name shopping_mall \
--env MYSQL_ROOT_HOST=%.%.%.% \
--env MYSQL_ROOT_PASSWORD=123456 \
-p 33060:3306  \
-di mysql:8.0.18
```

```sql
jdbc:mysql://121.36.33.154:33060/?serverTimezone=UTC
```

```sql
CREATE DATABASE shopping_mall DEFAULT CHARSET = utf8mb4;
USE shopping_mall;
```

# 创建基础表

```sql
CREATE TABLE `brand`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `name`        varchar(255) DEFAULT NULL COMMENT '品牌名称',
    `first_char`  varchar(1)   DEFAULT NULL COMMENT '品牌首字母',
    `create_time` TIMESTAMP    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `item`
(
    `id`          int(11)       NOT NULL AUTO_INCREMENT COMMENT '商品id',
    `title`       varchar(100)  NOT NULL COMMENT '商品标题',
    `price`       double(10, 2) NOT NULL COMMENT '商品价格，单位为：元',
    `num`         int(10)       NOT NULL COMMENT '库存数量',
    `category_id` bigint(10)    NOT NULL COMMENT '所属类目，叶子类目',
    `status`      varchar(1)  DEFAULT NULL COMMENT '商品状态，1-正常，2-下架，3-删除',
    `seller_id`   varchar(50) DEFAULT NULL COMMENT '商家ID',
    `create_time` TIMESTAMP   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='商品表';

CREATE TABLE `user`
(
    `id`          int(11)     NOT NULL AUTO_INCREMENT,
    `username`    varchar(45) NOT NULL,
    `password`    varchar(96) NOT NULL,
    `name`        varchar(45) NOT NULL,
    `birthday`    datetime    DEFAULT NULL,
    `sex`         char(1)     DEFAULT NULL,
    `email`       varchar(45) DEFAULT NULL,
    `phone`       varchar(45) DEFAULT NULL,
    `qq`          varchar(32) DEFAULT NULL,
    `create_time` TIMESTAMP   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `operation_log`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `operate_class`   varchar(200) DEFAULT NULL COMMENT '操作类',
    `operate_method`  varchar(200) DEFAULT NULL COMMENT '操作方法',
    `return_class`    varchar(200) DEFAULT NULL COMMENT '返回值类型',
    `operate_user`    varchar(20)  DEFAULT NULL COMMENT '操作用户',
    `param_and_value` varchar(500) DEFAULT NULL COMMENT '请求参数名及参数值',
    `cost_time`       bigint(20)   DEFAULT NULL COMMENT '执行方法耗时, 单位 ms',
    `return_value`    varchar(200) DEFAULT NULL COMMENT '返回值',
    `create_time`     TIMESTAMP    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
```

# 优化

```xml
<!--
    OperationLogMapper.xml
-->
<sql id="query_where">
    <where>
        <if test="operateUser != null and operateUser != ''">
            and operate_user = #{operateUser}
        </if>
        <if test="operateMethod != null and operateMethod != ''">
            and operate_method = #{operateMethod}
        </if>
        <if test="returnClass != null and returnClass != ''">
            and return_class = #{returnClass}
        </if>
        <if test="costTime != null">
            and cost_time = #{costTime}
        </if>
    </where>
</sql>
```

```xaml
<!--
    OperationLogMapper.xml
	第一版分页查询
-->
<select id="findByCondition" parameterType="map" resultType="cn.yuanyu.db.pojo.OperationLog">
    SELECT
    id,
    operate_class AS operateClass ,
    operate_method AS operateMethod,
    return_class AS returnClass,
    operate_user AS operateUser,
    param_and_value AS paramAndValue,
    cost_time AS costTime,
    return_value AS returnValue,
    create_time as createTime
    FROM
    operation_log
    <include refid="query_where"/>
    LIMIT #{start} , #{rows}
</select>
```

```xml
<!--
    OperationLogMapper.xml
    第二版分页查询
-->
<select id="findByCondition" parameterType="map" resultType="cn.yuanyu.db.pojo.OperationLog">
    SELECT
    o.id,
    o.operate_class AS operateClass ,
    o.operate_method AS operateMethod,
    o.return_class AS returnClass,
    o.operate_user AS operateUser,
    o.param_and_value AS paramAndValue,
    o.cost_time AS costTime,
    o.return_value AS returnValue,
    o.create_time as createTime
    FROM
    operation_log o,
    (SELECT id FROM operation_log
    <include refid="query_where"/>
    ORDER BY id LIMIT #{start} , #{rows}) a
    where
    o.id = a.id
</select>
```



```sql
-- 查询索引
show index from operation_log;
-- 创建索引
create index idx_log_user on operation_log(operate_user);
-- 删除索引
drop index idx_log_user on operation_log;
-- 创建复合索引
CREATE INDEX idx_user_method_return_cost ON operation_log (operate_user, operate_method, return_class, cost_time);
CREATE INDEX idx_optlog_method_return_cost ON operation_log (operate_method, return_class, cost_time);
CREATE INDEX idx_optlog_return_cost ON operation_log (return_class, cost_time);
CREATE INDEX idx_optlog_cost ON operation_log (cost_time);
```







