package com.quaint.leetcode.util;

import java.util.Arrays;

/**
 * @author quaint
 * @date 2021/4/22
 */
public abstract class LcPrintUtil {

    private static int idx = 0;

    /**
     * 输出比较
     * @param ans
     * @param out
     * @param <T>
     */
    public static <T> void printCompare(T ans, T out) {
        // 测试用例自增
        idx ++;

        // 答案 str
        String ansStr = arr1D2str(ans);
        // 输出 str
        String outStr = arr1D2str(out);

        // 判断答案是否错误
        if (outStr.equals(ansStr)) {
            System.out.println("case: [" + idx + "] ==> your out: [" + outStr + "]  <==>  the ans: [" + ansStr + "].");
        } else {
            System.err.println("case: [" + idx + "] ==> your out: [" + outStr + "]  <==>  the ans: [" + ansStr + "].");
        }
    }

    public static <T> String arr1D2str(T t) {
        String res;
        if (t instanceof int[]) {
            res = Arrays.toString((int[]) t);
        } else if (t instanceof String[]) {
            res = Arrays.toString((String[]) t);
        } else {
            res = t.toString();
        }
        return res;
    }

}
