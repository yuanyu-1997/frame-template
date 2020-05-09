package cn.yuanyu.mp.pojo;

import cn.yuanyu.mp.enums.SexEnum;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * @author yuanyu
 */
@Data
@TableName("tb_user")
public class User {
    /**
     * 数据库ID自增（不指定生成的id不是主键自增的值）
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     *
     */
    private String userName;
    /**
     * 查询时不返回该字段的值
     */
    @TableField(select = false)
    private String password;
    /**
     *
     */
    private String name;

    private Integer age;
    /**
     * 指定数据表中字段名（非驼峰不一致形）email vs mail
     * java.sql.SQLSyntaxErrorException: Unknown column 'mail' in 'field list'
     */
    @TableField(value = "email")
    private String mail;
    /**
     * 在数据库表中是不存在的（不设置 这个字段为空插入没问题，不为空插入时会报错--数据库不存在这个字段）
     * java.sql.SQLSyntaxErrorException: Unknown column 'address' in 'field list'
     */
    @TableField(exist = false)
    private String address;


    /**
     * 插入数据时进行填充
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

    /**
     * 逻辑删除字段（1-删除，0-未删除）；需要在application.yml配置
     */
    @TableLogic
    private Integer deleted;

    /**
     * 性别，枚举类型
     */
    private SexEnum sex;
}