package cn.yuanyu.mp.pojo;

import lombok.Data;

/**
 * @author yuanyu
 */
@Data
//@TableName("tb_user")
public class User {
    //@TableId(type = IdType.AUTO)
    private Long id;
    private String userName;
    private String password;
    private String name;
    private Integer age;
    private String email;
}