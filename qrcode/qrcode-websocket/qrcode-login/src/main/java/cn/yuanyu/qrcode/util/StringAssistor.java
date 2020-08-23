package cn.yuanyu.qrcode.util;


import java.util.Random;

public class StringAssistor {
    private static String identifySet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String randomString(int Length) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < Length; i++) {
            builder.append(identifySet.charAt(random.nextInt(62)));
        }
        return builder.toString();
    }
}
