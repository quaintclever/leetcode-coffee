package com.quaint.study.time;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author quaint
 * @date 2021/4/20
 */
public class TestTimeSwitch extends TimeSwitchUtil {

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        LocalDate date = LocalDate.now();

        System.out.println("=======");
        System.out.println(now);
        System.out.println(switch2milli(now));

        System.out.println("=======");
        System.out.println(date);
        System.out.println(switch2second(date));

        System.out.println("=======");
        System.out.println(now);
        System.out.println(switch2second(now));

        System.out.println("=======");
        System.out.println(switch2second(now));
        System.out.println(second2DateStr(switch2second(now)));

        System.out.println("=======");
        System.out.println(TimeSwitchUtil.longM2string(System.currentTimeMillis(), "yyyyMM"));

    }

}
