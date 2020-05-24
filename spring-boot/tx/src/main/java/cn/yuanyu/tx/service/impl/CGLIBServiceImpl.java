//package cn.yuanyu.tx.service.impl;
//
//
//import cn.yuanyu.tx.mapper.UserMapper;
//import cn.yuanyu.tx.entity.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
////TODO 只有public方法的事务注解有事务管理，并且必须外部调用
///**
// * @author yuanyu
// */
//@Service
//public class CGLIBServiceImpl {
//    @Autowired
//    private UserMapper userMapper;
//
//
//    /**
//     * 正常执行 有事务管理 √
//     */
//    @Transactional
//    public void ok(User user) {
//        userMapper.update(user);
//        int a = 1 / 0;
//    }
//
//
//    /**
//     * protected 无事务管理 ×
//     */
//    @Transactional
//    protected void a(User user) {
//        userMapper.update(user);
//        int a = 1 / 0;
//    }
//
//    /**
//     * private 编译器就会报错
//     */
//    @Transactional
//    private void b(User user) {
//        userMapper.update(user);
//        int a = 1 / 0;
//    }
//
//
//    @Transactional
//    public final void c(User user) {
//        userMapper.update(user);
//        int a = 1 / 0;
//    }
//
//    @Transactional
//    public static void e(User user) {
//        //userMapper.update(user);
//        int a = 1 / 0;
//    }
//
//
//    //无事务管理 ×
//    @Transactional
//    void f(User user) {
//        userMapper.update(user);
//        int a = 1 / 0;
//    }
//}
