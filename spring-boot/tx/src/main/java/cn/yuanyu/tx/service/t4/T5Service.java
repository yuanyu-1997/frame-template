package cn.yuanyu.tx.service.t4;


import cn.yuanyu.tx.entity.User;

import java.io.IOException;

public interface T5Service {
    /**
     * Error异常会回滚
     */
    void m11(User user);

    /**
     * IOException默认不会回滚
     */
    void m12(User user) throws IOException;

    /**
     * 设置IOException回滚
     */
    void m13(User user) throws IOException;

}
