package cn.yuanyu.uaa.enums;

import lombok.Getter;

/**
 * 授权方式
 */
@Getter
public enum GrantTypeEnum {
    /**
     * 授权码模式
     */
    AUTHORIZATION_CODE("authorization_code");

    private final String type;

    GrantTypeEnum(String type) {
        this.type = type;
    }
}
