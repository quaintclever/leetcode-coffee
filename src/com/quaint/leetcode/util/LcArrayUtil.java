package com.quaint.leetcode.util;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * desc: leetcode 数组工具类
 * </p>
 *
 * @author quaint
 * @since 18 April 2021
 */
public abstract class LcArrayUtil {

    /**
     * 获取 一维 字符串
     *
     * @param arr 字符串数组  eg:  [1,2,3,4,5]  or 1,2,3,4,5
     * @return int[]
     */
    public static int[] get1dArr(String arr) {
        if ("[]".equals(arr) || "".equals(arr)) {
            return new int[]{};
        }
        String str;
        if (arr.startsWith("[") && arr.endsWith("]")) {
            str = arr.substring(1, arr.length() - 1);
        } else {
            str = arr;
        }
        String[] strArr = str.split(",");
        int[] res = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            res[i] = Integer.parseInt(strArr[i]);
        }
        return res;
    }

    /**
     * 获取 二维 字符串
     *
     * @param arr 字符串数组  eg:  "[[1,2,3,4,5]]"
     * @return int[][]
     */
    public static int[][] get2dArr(String arr) {
        if ("[[]]".equals(arr) || "".equals(arr)) {
            return new int[][]{};
        }

        // 处理字符串
        String str = arr.substring(1, arr.length() - 2);
        str = str.replace("[", "");
        String[] strArr = str.split("],");

        // 定义数组大小
        int m = strArr.length;
        int n = 0;
        for (String s : strArr) {
            n = Math.max(s.split(",").length, n);
        }

        // 给数组赋值.
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            String[] inArr = strArr[i].split(",");
            for (int j = 0; j < n && j < inArr.length; j++) {
                res[i][j] = Integer.parseInt(inArr[j]);
            }
        }
        return res;
    }

    /**
     * 获取 二维 List
     *
     * @param arr 字符串数组  eg:  "[[1,2,3,4,5]]"
     * @return List<List<Integer>>
     */
    public static List<List<Integer>> get2dList(String arr) {
        if ("[[]]".equals(arr) || "".equals(arr)) {
            return new ArrayList<>();
        }

        // 处理字符串
        String str = arr.substring(1, arr.length() - 2);
        str = str.replace("[", "");
        String[] strArr = str.split("],");

        // 准备返回数据
        List<List<Integer>> res = new ArrayList<>();
        for (String s1 : strArr) {
            List<Integer> cur = new ArrayList<>();
            String[] inArr = s1.split(",");
            for (String s2 : inArr) {
                cur.add(Integer.parseInt(s2));
            }
            res.add(cur);
        }
        return res;
    }

}
