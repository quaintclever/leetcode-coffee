package com.quaint.study.time;

import java.time.LocalDateTime;

/**
 * @author quaint
 * @date 2021/4/20
 */
public class TimeTest extends TimeUtil{

    public static void main(String[] args) {
        LocalDateTime localDateTime = timeStr2toDayTime("07:11:11");
        System.out.println(localDateTime.toString());
    }

}
