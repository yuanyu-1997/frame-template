package cn.yuanyu.uaa.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 可被访问的用户权限表
 */
@Data
public class AuthScope {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 可被访问的用户的权限范围（比如：basic、super）
     */
    private String scopeName;
    /**
     *
     */
    private String description;

    public AuthScope() {
    }

    public AuthScope(String scopeName, String description) {
        this.scopeName = scopeName;
        this.description = description;
    }
}