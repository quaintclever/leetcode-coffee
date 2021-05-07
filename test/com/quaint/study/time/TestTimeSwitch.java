package com.quaint.study.time;

import java.time.LocalDateTime;

/**
 * @author quaint
 * @date 2021/4/20
 */
public class TestTimeSwitch extends TimeSwitchUtil {

    public static void main(String[] args) {
        LocalDateTime localDateTime = timeStr2toDayTime("07:11:11");
        System.out.println(localDateTime.toString());

        System.out.println("获取今天 0 时的秒 时间戳");
        System.out.println(getTodayZeroTime());

        System.out.println("获取15天前 0 时的秒 时间戳");
        System.out.println(getRelativeDayZeroTime(-15));
        System.out.println(long2string(getRelativeDayZeroTime(-15) * 1000));
        System.out.println(long2string(getRelativeDayTime(-15) * 1000));
    }

}
