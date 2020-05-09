package cn.yuanyu.mp.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;


/**
 * @author yuanyu
 */

public enum SexEnum implements IEnum<Integer> {
    /**
     *
     */
    MAN(1, "男"),
    /**
     *
     */
    WOMAN(2, "女");
    /**
     *
     */
    private final int value;
    /**
     *
     */
    private final String desc;

    SexEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * 枚举数据库存储值
     */
    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.desc;
    }
}
