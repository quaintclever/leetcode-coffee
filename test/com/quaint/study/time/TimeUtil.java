package com.quaint.study.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 时间工具
 * @author quaint
 * @date 2021/4/20
 */
public abstract class TimeUtil {

    public final static String DEFAULT_LOCAL_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public final static String DEFAULT_LOCAL_TIME_FORMAT = "HH:mm:ss";

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
            throw new IllegalArgumentException("时间字符串不能转换成LocalTime!");
        }
    }

}
