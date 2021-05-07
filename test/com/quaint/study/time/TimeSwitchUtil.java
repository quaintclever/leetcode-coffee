package com.quaint.study.time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * 时间转换工具
 * @author quaint
 * @date 2021/4/20
 */
public abstract class TimeSwitchUtil {

    public final static String DEFAULT_LOCAL_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public final static String DEFAULT_LOCAL_TIME_FORMAT = "HH:mm:ss";

    /**
     * LocalDateTime  ==>  毫秒时间戳
     */
    public static long switch2milli(LocalDateTime time) {
        if (time == null) {
            return 0;
        }
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * LocalDate  ==>  时间戳
     */
    public static long switch2second(LocalDate date) {
        if (date == null) {
            return 0;
        }
        LocalDateTime time = date.atTime(0, 0, 0);
        return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    /**
     * LocalDateTime  ==>  时间戳
     */
    public static long switch2second(LocalDateTime time) {
        if (time == null) {
            return 0;
        }
        return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    /**
     * LocalDateTime  <==  时间戳
     */
    public static LocalDateTime switch4long(long millis) {
        return Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }


    /**
     * LocalDateTime  <==  字符串
     */
    public static LocalDateTime switch4string(String timeStr) {
        return switch4string(timeStr, DEFAULT_LOCAL_DATE_TIME_FORMAT);
    }

    public static LocalDateTime switch4string(String timeStr, String format) {
        if (timeStr == null || timeStr.trim().length() == 0) {
            return null;
        }
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
            return LocalDateTime.parse(timeStr, df);
        } catch (Exception e) {
            throw new IllegalArgumentException("日期字符串不能转换成LocalDateTime!");
        }
    }

    /**
     * LocalDateTime  ==>  string
     */
    public static String switch2string(LocalDateTime time) {
        return switch2string(time, DEFAULT_LOCAL_DATE_TIME_FORMAT);
    }

    public static String switch2string(LocalDateTime time, String format) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return df.format(time);
    }


    /**
     * 上面为  基于 LocalDateTime 的转换
     * =======================================================================
     * 下面为  str 和 时间戳转换
     * <p>
     * 时间戳  ==>  字符串
     */
    public static String long2string(long time) {
        return long2string(time, DEFAULT_LOCAL_DATE_TIME_FORMAT);
    }

    public static String long2string(long time, String format) {
        Instant instant = Instant.ofEpochMilli(time);
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, zone);
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return df.format(dateTime);
    }

    /**
     * 字符串  ==>  时间戳
     */
    public static long string2long(String time) {
        return string2long(time, DEFAULT_LOCAL_DATE_TIME_FORMAT);
    }

    public static long string2long(String time, String format) {
        LocalDateTime dateTime = switch4string(time, format);
        return switch2second(dateTime);
    }

    /**
     * 不包含日期的时间字符串, 转为当天的对应时间
     * @param timeStr
     * @return
     */
    public static LocalDateTime timeStr2toDayTime(String timeStr) {
        return timeStr2toDayTime(timeStr, DEFAULT_LOCAL_TIME_FORMAT);
    }

    public static LocalDateTime timeStr2toDayTime(String timeStr, String format) {
        if (timeStr == null || timeStr.trim().length() == 0) {
            return null;
        }
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
            LocalTime lt = LocalTime.parse(timeStr, df);
            return LocalDate.now().atTime(lt);
        } catch (Exception e) {
            throw new IllegalArgumentException("日期字符串不能转换成LocalTime!");
        }
    }

    /**
     * 获取今天 0 时的 时间戳
     * @return 时间戳
     */
    public static long getTodayZeroTime() {
        return LocalDate.now().atStartOfDay().toEpochSecond(ZoneOffset.of("+8"));
    }

    /**
     * 获取当前秒数
     * @return 时间戳 单位: 秒
     */
    public static Long getCurrentTimeSeconds() {
        return System.currentTimeMillis() / 1000L;
    }

    /**
     * 获取相对时间 的0点 秒数
     * @param day
     * @return
     */
    public static long getRelativeDayZeroTime(int day) {
        return getRelativeDayZeroTime(day, ChronoUnit.DAYS);
    }

    public static long getRelativeDayZeroTime(int num, TemporalUnit unit) {
        if (num == 0) {
            return getTodayZeroTime();
        }
        return LocalDate.now().plus(num, unit).atStartOfDay().toEpochSecond(ZoneOffset.of("+8"));
    }


    /**
     * 获取相对时间 的当前时间秒数
     * @param day
     * @return
     */
    public static long getRelativeDayTime(int day) {
        return getRelativeDayTime(day, ChronoUnit.DAYS);
    }

    public static long getRelativeDayTime(int num, TemporalUnit unit) {
        if (num == 0) {
            return LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        }
        return LocalDateTime.now().plus(num, unit).toEpochSecond(ZoneOffset.of("+8"));
    }

}
