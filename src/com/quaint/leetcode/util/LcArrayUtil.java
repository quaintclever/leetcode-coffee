package com.quaint.leetcode.util;

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
     * @param arr 字符串数组  eg:  [1,2,3,4,5]
     * @return int[]
     */
    public static int[] get1dArr(String arr) {
        if ("[]".equals(arr) || "".equals(arr)) {
            return new int[]{};
        }
        String str = arr.substring(1, arr.length() - 1);
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
     * @param arr 字符串数组  eg:  [[1,2,3,4,5]]
     * @return int[][]
     */
    public static int[][] get2dArr(String arr) {
        if ("[[]]".equals(arr) || "".equals(arr)) {
            return new int[][]{};
        }
        String str = arr.substring(1, arr.length() - 1);
        str = str.replace("[", "");
        String[] strArr = str.split("]");
        int m = strArr.length;
        int n = strArr[0].split(",").length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            String[] inArr = strArr[i].split(",");
            for (int j = 0; j < n; j++) {
                res[i][j] = Integer.parseInt(inArr[j]);
            }
        }
        return res;
    }

}
