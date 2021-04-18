package com.quaint.leetcode.year2021;

/**
 * <p>
 * desc: 四月是你的谎言, leetcode 练习.
 * </p>
 *
 * @author quaint
 * @since 18 April 2021
 */
public class April {

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
