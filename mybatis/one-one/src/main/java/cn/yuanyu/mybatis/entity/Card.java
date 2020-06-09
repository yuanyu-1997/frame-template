package cn.yuanyu.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author yuanyu
 */
@Data
public class Card {
    /**
     *
     */
    @TableId(type = IdType.INPUT)
    private Integer personId;

    /**
     * 身份证编号
     */
    private String code;

    /**
     * 住址
     */
    private String address;
}
