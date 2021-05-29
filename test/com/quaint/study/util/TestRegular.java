package com.quaint.study.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author quaint
 * @date 2021/2/26
 */
public class TestRegular {

    private final static String regexH5 = "^http(s)?://.*";
    private final static String regexWx = "^/.*";
    private final static String regexAli = "^/.*";

    public static void main(String[] args) {
        // 匹配正则
        String pattern = "[\\u4e00-\\u9fa5]+";
        String test = "测试";
        System.out.println("测试:" + test.matches(pattern));
        boolean isMatch = Pattern.matches(pattern, "测试");
        System.out.println("isMatch: " + isMatch);
        System.out.println("dfd".matches("\\w{3}"));
        System.out.println("df454d".matches("\\w+"));
        System.out.println("dfd的是非得失".matches("\\w+"));
        System.out.println("11.0".matches("\\d+.\\d+"));

        System.out.println(isAliAppletUrl("/ali/page"));
    }

    public static boolean isAliAppletUrl(String aliUrl) {
        Pattern pattern = Pattern.compile(regexAli, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(aliUrl);
        return matcher.find();
    }

}
