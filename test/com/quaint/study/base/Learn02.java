package com.quaint.study.base;

import java.util.Arrays;

/**
 * 基本数据类型
 * @author quaint
 * @date 2021/3/16
 */
public class Learn02 {

    public static void main(String[] args) {

        // 代表二进制的 101101, 相当于十进制的 45
        int v1 = 0b00101101;
        System.out.println(v1); // 45
        // 代表八进制的 377，相当于十进制的 255
        int v2 = 0377;
        System.out.println(v2); // 255
        // 代表十六进制的 1 除以 2²，也就是 0.25
        double v3 = 0x1p-2;
        System.out.println(v3); // 0.25
        // 还允许我们用 _ 来分隔数字
        int v4 = 123_456;
        System.out.println(v4); // 123456


        // === 定义 float 最大值, double 最大值 ===
        float fm = Float.MAX_VALUE;
        double dm = Double.MAX_VALUE;
        System.out.printf("%f\n",fm);
        System.out.printf("%f\n",dm);

        // Inconvertible types; cannot cast 'int' to 'boolean'
        // boolean bo = (boolean) 1;

        String s1 = "hello";
        String s2 = "你好";
        // 转移字符举例
        System.out.println("\"User\\quaint\\test\"");
        System.out.println(s1.length());
        // 格式化字符串
        System.out.println(String.format("%s__%s", s1, s2));
        // 字符串分隔
        String[] ls = s1.split("l");
        System.out.println(Arrays.toString(ls));
        // 字符串中是否包含
        System.out.println(s1.contains("l"));
        // 前后缀判断
        System.out.println(s1.startsWith("h"));
        System.out.println(s1.endsWith("lo"));
        // 字符出现的位置
        System.out.println(s1.indexOf("l"));
        System.out.println(s1.indexOf("lo"));
        // join
        String message = String.join("-", "Java", "is", "cool");
        System.out.println(message);


        String forTestStr = "我的名字是困特, 英文名字是 quaint";
        for(int i = 0; i < forTestStr.length(); i++) {
            System.out.printf("%c",forTestStr.charAt(i));
        }

        // 比较字符串局部相等
        boolean res = "bbb111".regionMatches(3, "aa111a", 2, 3);
        System.out.println("\n比较字符串局部相等: " + res);

        // 修改字符串中的指定字符
        String updateStr = "\n我是要成为海贼王的困特!quaint";
        StringBuilder sb = new StringBuilder(updateStr);
        sb.setCharAt(12,'?');
        System.out.println(sb.toString());

        // 隐式转换
        System.out.println(Math.sqrt(9));
        System.out.println(Math.sqrt(9.0));

    }

}
