package com.quaint.leetcode.year2021;

import com.quaint.leetcode.util.LcDataStructure;
import com.quaint.leetcode.util.LcPrintUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 六月的风, 吹过了你的脸庞
 *
 * @author quaint
 * @date 2021/6/1
 */
public class June extends LcDataStructure {

    /**
     * 雨流算法. (一遍不行.)
     * @param rainStream 雨流数组
     * @return 删除的雨流
     */
    public List<String> rainStream(int[] rainStream) {
        System.out.println("======= 随机生成的数组 =======");
        System.out.println(LcPrintUtil.arr1D2str(rainStream));

        int idx = 0;
        List<String> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        for (int i = 1; i < rainStream.length - 2;) {
            if (this.isRainStream(rainStream[idx], rainStream[i++], rainStream[i++], rainStream[i])) {
                res.add(rainStream[i - 2] + ":" + rainStream[i - 1]);
            } else {
                temp.add(rainStream[idx]);
                if (i - idx != 3) {
                    idx = i - 2;
                } else {
                    idx++;
                }
                i = idx + 1;
            }
            // 剩余的
            if (i >= rainStream.length - 2){
                temp.add(rainStream[idx]);
                for (int j = i; j < rainStream.length; j++) {
                    temp.add(rainStream[j]);
                }
            }
        }
        System.out.println("======= 剩余的 =======");
        System.out.println(LcPrintUtil.arr1D2str(temp));
        return res;
    }

    private boolean isRainStream(int a, int b, int c, int d) {
        if (b >= d && c <= a && c > b) {
            return true;
        }
        return b <= d && c >= a && c < b;
    }

    /**
     * 494. 目标和
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        return dfs(nums, 0, target);
    }

    private int dfs(int[] nums, int idx, int target) {
        if (idx >= nums.length) {
            return target == 0 ? 1 : 0;
        }
        int left = dfs(nums, idx + 1, target + nums[idx]);
        int right = dfs(nums, idx + 1, target - nums[idx]);
        return left + right;
    }

    /**
     * 525. 连续数组
     *
     * @param nums
     * @return
     */
    public int findMaxLength(int[] nums) {
        // 构建前缀数组
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] - 1 + (nums[i - 1] << 1);
        }
        // 记录坐标和前缀和
        Map<Integer, Integer> sumIdxMap = new HashMap<>();
        int ans = 0;
        sumIdxMap.put(0, 0);
        for (int i = 2; i <= n; i++) {
            if (!sumIdxMap.containsKey(sum[i - 2])) {
                sumIdxMap.put(sum[i - 2], i - 2);
            }
            if (sumIdxMap.containsKey(sum[i])) {
                ans = Math.max(ans, i - sumIdxMap.get(sum[i]));
            }
        }
        return ans;
    }

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
