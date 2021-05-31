package com.quaint.study.base;

/**
 * 类型转换练习
 *
 * @author quaint
 * @date 2021/5/31
 */
public class Learn03 {

    public static void main(String[] args) {
        // String 对象转换为 long
        System.out.println("======= (String) Object 对象转换为 long =======");
        Object o = null;
        String count = (String) o;
        long cnt;
        if (count == null) {
            cnt = 0;
        } else {
            cnt = Long.parseLong(count);
        }
        System.out.println(cnt);
    }
}
