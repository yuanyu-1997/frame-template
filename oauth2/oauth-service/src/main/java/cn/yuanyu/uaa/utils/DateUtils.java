package cn.yuanyu.uaa.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Date相关公共方法
 */
public class DateUtils {

    private static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 返回当前的LocalDateTime
     */
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }


    /**
     * 返回当前精确到毫秒的时间戳
     */
    public static Long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 将时间戳转换为LocalDateTime
     *
     * @param second     Long类型的时间戳
     * @param zoneOffset 时区，不填默认为+8
     */
    public static LocalDateTime ofEpochSecond(Long second, ZoneOffset zoneOffset) {
        if (zoneOffset == null) {
            return LocalDateTime.ofEpochSecond(second, 0, ZoneOffset.ofHours(8));
        } else {
            return LocalDateTime.ofEpochSecond(second, 0, zoneOffset);
        }
    }


    /**
     * 格式化LocalDateTime
     *
     * @param pattern 指定时间格式化表达式
     */
    public static String toDateTimeStr(LocalDateTime dateTime, String pattern) {
        return dateTime.format(DateTimeFormatter.ofPattern(pattern, Locale.SIMPLIFIED_CHINESE));
    }

    /**
     * 返回当前日期的LocalDate
     */
    public static LocalDate currentDate() {
        return LocalDate.now();
    }

    /**
     * 格式化LocalDate
     */
    public static String toDateStr(LocalDate date) {
        return toDateStr(date, DEFAULT_DATE_FORMAT);
    }

    /**
     * 格式化LocalDate
     *
     * @param date    LocalDate
     * @param pattern 指定日期格式化表达式
     */
    public static String toDateStr(LocalDate date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern, Locale.SIMPLIFIED_CHINESE));
    }

    /**
     * 返回几天之后的时间
     *
     * @param days 天数
     */
    public static LocalDateTime nextDays(Long days) {
        return now().plusDays(days);
    }

    /**
     * 返回几天之后的时间（精确到秒的时间戳）
     *
     * @param days       天数
     * @param zoneOffset 时区，不填默认为+8
     */
    public static Long nextDaysSecond(Long days, ZoneOffset zoneOffset) {
        LocalDateTime dateTime = nextDays(days);

        if (zoneOffset == null) {
            return dateTime.toEpochSecond(ZoneOffset.ofHours(8));
        } else {
            return dateTime.toEpochSecond(zoneOffset);
        }
    }

    /**
     * 将天数转化为秒数
     *
     * @param days 天数
     */
    public static Long dayToSecond(Long days) {
        return days * 86400;
    }

}
