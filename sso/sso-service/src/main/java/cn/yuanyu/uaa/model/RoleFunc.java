package cn.yuanyu.uaa.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class RoleFunc {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     *
     */
    private Integer roleId;
    /**
     *
     */
    private Integer funcId;
}