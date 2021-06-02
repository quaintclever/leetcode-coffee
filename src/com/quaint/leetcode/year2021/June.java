package com.quaint.leetcode.year2021;

import com.quaint.leetcode.util.LcDataStructure;

import java.util.HashMap;
import java.util.Map;

/**
 * 六月的风, 吹过了你的脸庞
 *
 * @author quaint
 * @date 2021/6/1
 */
public class June extends LcDataStructure {

    /**
     * 523. 连续的子数组和 (改map)
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        // 构建前缀数组
        int[] sum = new int[n + 1];
        Map<Integer, Integer> need = new HashMap<>();
        need.put(0, 0);
        for (int i = 1; i <= n; i++) {
            sum[i] = (sum[i - 1] + nums[i - 1]) % k;
            if (need.containsKey(sum[i])) {
                if (i - need.get(sum[i]) >= 2) {
                    return true;
                }
            } else {
                need.put(sum[i], i);
            }
        }
        return false;
    }

    /**
     * 523. 连续的子数组和 (暴力)
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum2(int[] nums, int k) {
        int n = nums.length;
        // 构建前缀数组
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        // 遍历寻找结果
        for (int i = 0; i < n; i++) {
            for (int j = i + 2; j <= n; j++) {
                int part = sum[j] - sum[i];
                if (part % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }

}
