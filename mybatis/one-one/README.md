

```sql
USE mybatis_db;
CREATE TABLE tb_person
(
    `id`       VARCHAR(64) NOT NULL COMMENT '主键id',
    `username` VARCHAR(20) NOT NULL COMMENT '姓名',
    `password` VARCHAR(64) NOT NULL COMMENT '密码',
    `sex`      CHAR(2) COMMENT '性别',
    `age`      TINYINT COMMENT '年龄',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_person_username` (`username`)
) COMMENT = '用户表';
CREATE TABLE tb_card
(
    `person_id` VARCHAR(64) NOT NULL COMMENT '用户id',
    `code`      VARCHAR(18) NOT NULL COMMENT '身份证编号',
    `address`   VARCHAR(100) COMMENT '住址',
    PRIMARY KEY (`person_id`),
    FOREIGN KEY (person_id) REFERENCES tb_person (id)
);
--
INSERT INTO tb_person(`id`, `username`, `password`, `sex`, `age`)
VALUES ('8848', 'jack', '123456', '男', 23);
INSERT INTO tb_card(`person_id`, `code`, `address`)
VALUES ('8848', '432801198009191038', 'Xxx市Xxx区Xxx街道Xxx村Xxx组Xxx号');
```

![relation](./relation.png)





```xml
<!--
    CardMapper.xml
-->
<mapper namespace="cn.yuanyu.mybatis.mapper.CardMapper">
    <!-- 根据id查询Card，返回Card对象 -->
    <select id="selectCardById" parameterType="int" resultType="cn.yuanyu.mybatis.entity.Card">
        SELECT *
        from tb_card
        where person_id = #{id}
    </select>
</mapper>
```

方式一

```xml
<!--
    PersonMapper.xml
-->
<mapper namespace="cn.yuanyu.mybatis.mapper.PersonMapper">
    <resultMap type="cn.yuanyu.mybatis.entity.Person" id="personMapper">
        <id property="id" column="id"/>
        <result property="username" column="username"/>
        <result property="password" column="password"/>
        <result property="sex" column="sex"/>
        <result property="age" column="age"/>
        <association property="card" column="id"
                     select="cn.yuanyu.mybatis.mapper.CardMapper.selectCardById"
                     javaType="cn.yuanyu.mybatis.entity.Card"/>
    </resultMap>
    <select id="selectPersonByUsername" resultMap="personMapper">
        SELECT *
        from tb_person
        where username = #{username}
    </select>
</mapper>
```

方式二

```xml
<!--
    PersonMapper.xml
-->
<mapper namespace="cn.yuanyu.mybatis.mapper.PersonMapper">
    <!-- 方式二的第一种映射方式 -->
    <resultMap type="cn.yuanyu.mybatis.entity.Person" id="personMapper">
        <id property="id" column="id"/>
        <result property="username" column="username"/>
        <result property="password" column="password"/>
        <result property="sex" column="sex"/>
        <result property="age" column="age"/>
        <collection property="card" ofType="cn.yuanyu.mybatis.entity.Card">
            <id property="personId" column="person_id"/>
            <result property="code" column="code"/>
            <result property="address" column="address"/>
        </collection>
    </resultMap>
    <!-- -->
    <select id="selectPersonByUsername" resultMap="personMapper">
        select *
        from tb_person p
                 join tb_card c on p.id = c.person_id and p.username = #{username}
    </select>
</mapper>
```

```xml
<!--
    PersonMapper.xml
-->
<mapper namespace="cn.yuanyu.mybatis.mapper.PersonMapper">
    <!-- 方式二的第二种映射方式 -->
    <resultMap type="cn.yuanyu.mybatis.entity.Person" id="personMapper">
        <id property="id" column="id"/>
        <result property="username" column="username"/>
        <result property="password" column="password"/>
        <result property="sex" column="sex"/>
        <result property="age" column="age"/>
        <result property="card.personId" column="person_id"/>
        <result property="card.code" column="code"/>
        <result property="card.address" column="address"/>
    </resultMap>
    <!-- -->
    <select id="selectPersonByUsername" resultMap="personMapper">
        select *
        from tb_person p
                 join tb_card c on p.id = c.person_id and p.username = #{username}
    </select>
</mapper>
```



