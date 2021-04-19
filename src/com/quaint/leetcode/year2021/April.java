package com.quaint.leetcode.year2021;

import javafx.util.Pair;

/**
 * <p>
 * desc: 四月是你的谎言, leetcode 练习.
 * </p>
 *
 * @author quaint
 * @since 18 April 2021
 */
public class April {

    /**
     * 165. 比较版本号
     *
     * @param v1 eg: 9.10.1
     * @param v2 eg: 10.0.1
     * @return v1 > v2  1 | v1 == v2  0 |  v1 < v2  -1
     */
    public int compareVersion(String v1, String v2) {
        String[] s1 = v1.split("\\.");
        String[] s2 = v2.split("\\.");
        int n1 = s1.length;
        int n2 = s2.length;
        for (int i = 0; i < n1 && i < n2; i++) {
            if (Long.parseLong(s1[i]) == Long.parseLong(s2[i])) {
                continue;
            }
            return Long.parseLong(s1[i]) > Long.parseLong(s2[i]) ? 1 : -1;
        }
        if (n1 == n2) {
            return 0;
        }
        if (n1 > n2) {
            while (n2 < n1) {
                if (Long.parseLong(s1[n2]) != 0) {
                    return 1;
                }
                n2++;
            }
        } else {
            while (n1 < n2) {
                if (Long.parseLong(s2[n1]) != 0) {
                    return -1;
                }
                n1++;
            }
        }
        return 0;
    }


    // 5737. 所有数对按位与结果的异或和
    public int getXORSum(int[] arr1, int[] arr2) {
        int xor = arr2[0];
        for (int i = 1; i < arr2.length; i++) {
            xor ^= arr2[i];
        }
        int ans = arr1[0] & xor;
        for (int i = 1; i < arr1.length; i++) {
            ans ^= (xor & arr1[i]);
        }
        return ans;
    }

}
