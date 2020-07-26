package cn.yuanyu.studentapi.entity.sys;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author yuanyu
 */
@Data
@TableName("sys_permission")
public class SysPermissionEntity {
    /**
     * 权限ID
     */
    @TableId(type = IdType.INPUT)
    private String id;
    /**
     * 权限标识符
     */
    private String permTag;
    /**
     * 权限名
     */
    private String permDesc;


    public SysPermissionEntity() {
    }

    public SysPermissionEntity(String id, String permTag, String permDesc) {
        this.id = id;
        this.permDesc = permDesc;
        this.permTag = permTag;
    }
}
