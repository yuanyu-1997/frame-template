package cn.yuanyu.uaa.enums;

import lombok.Getter;

import java.util.concurrent.TimeUnit;

/**
 * 过期时间相关枚举
 */
@Getter
public enum ExpireEnum {
    /**
     * Authorization Code 的有效期为10分钟
     */
    AUTHORIZATION_CODE(10L, TimeUnit.MINUTES),
    /**
     * Access Token 的有效期为30天
     */
    ACCESS_TOKEN(30L, TimeUnit.DAYS),
    /**
     * Refresh Token的有效期为365天
     */
    REFRESH_TOKEN(365L,TimeUnit.DAYS)
    ;
    /**
     * 过期时间
     */
    private final Long time;
    /**
     * 时间单位
     */
    private final TimeUnit timeUnit;

    ExpireEnum(Long time, TimeUnit timeUnit) {
        this.time = time;
        this.timeUnit = timeUnit;
    }

}
