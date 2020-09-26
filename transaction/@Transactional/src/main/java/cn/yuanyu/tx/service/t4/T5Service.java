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


    // m14 -> m15
    void m14(User a, User b);
    void m15(User user);



    // T5Service2.m16 -> m17
    void m17(User user);



    // m20 -> m21
    void m20(User a, User b);
    void m21(User user);


    // T5Service2.m22 -> T5Service.m23
    void m23(User user);

}
