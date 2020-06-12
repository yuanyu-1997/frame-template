import cn.yuanyu.reflex.AutoWired;
import cn.yuanyu.reflex.controller.UserController;
import cn.yuanyu.reflex.service.UserService;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflexTest {
    @Test
    public void test() throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        UserController userController = new UserController();
        Class<? extends UserController> clazz = userController.getClass();
        //创建对象
        UserService userService = new UserService();
        //获取所有属性
        Field serviceField = clazz.getDeclaredField("userService");
        serviceField.setAccessible(true);
        //只有通过方法才能够设置具体的属性值
        String name = serviceField.getName();
        //拼接方法的名称
        String setMethodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);

        //通过方法注入属性对象
        Method method = clazz.getMethod(setMethodName, UserService.class);
        //反射调用
        method.invoke(userController, userService);
        System.out.println(userController.getUserService());
    }


    @Test
    public void test_2() {
        UserController userController = new UserController();
        Class<? extends UserController> clazz = userController.getClass();
        //获取所有的属性值
        Arrays.stream(clazz.getDeclaredFields()).forEach(field -> {
            AutoWired annotation = field.getAnnotation(AutoWired.class);
            if (annotation != null) {
                field.setAccessible(true);
                //获取属性的类型
                Class<?> type = field.getType();
                Object userService;
                try {
                    userService = type.newInstance();
                    field.set(userController, userService);
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println(userController.getUserService());
    }


}
