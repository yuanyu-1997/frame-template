package cn.yuanyu.guava;

import com.google.common.base.Throwables;

/**
 * 获得字符串类型的堆栈信息
 *
 * @author yuanyu
 */
public class ExceptionStackTest {
    public static void main(String[] args) {
        try {
            controller();
        } catch (Throwable e) {
            String stackTrace = Throwables.getStackTraceAsString(e);
            System.out.println(stackTrace);
        }
    }
    private static void controller() {
        service();
    }
    private static void service() {
        dao();
    }
    private static void dao() {
        int a = 1 / 0;
    }
}
