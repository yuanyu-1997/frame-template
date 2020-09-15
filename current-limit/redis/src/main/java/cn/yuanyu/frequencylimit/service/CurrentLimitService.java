package cn.yuanyu.frequencylimit.service;


public interface CurrentLimitService {
    /**
     * 判断是否超出限制
     *
     * @param ip      客户端IP地址
     * @param timeout 时间段，毫秒
     * @param count   这个时间段内允许访问的次数
     */
    boolean limitSendCount(String ip, long timeout, int count);
}
