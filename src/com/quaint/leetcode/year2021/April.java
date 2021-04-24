package com.quaint.leetcode.year2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
     * 377. 组合总和 Ⅳ
     * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
     *
     * 题目数据保证答案符合 32 位整数范围。
     *
     *  @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        int[] fn = new int[target + 1];
        fn[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) {
                    fn[i] += fn[i - num];
                }
            }
        }
        return fn[target];
    }

    /**
     * 368. 最大整除子集
     * 给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[j]) 都应当满足：
     * answer[i] % answer[j] == 0 ，或
     * answer[j] % answer[i] == 0
     * 如果存在多个有效解子集，返回其中任何一个均可。
     * @param nums
     * @return
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        // 排序
        Arrays.sort(nums);

        // 初始化 动态规划数组, 和状态转移数组
        int n = nums.length;
        int[] fn = new int[n];
        int[] gn = new int[n];

        for (int i = 0; i < n; i++) {
            int len = 1, pre = i;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0) {
                    // 如果大于 len, 更新
                    if (fn[j] + 1 > len) {
                        len = fn[j] + 1;
                        pre = j;
                    }
                }
            }
            fn[i] = len;
            gn[i] = pre;
        }

        // 寻找 最长的 fn.
        int max = -1;
        int idx = -1;
        for (int i = 0; i < n; i++) {
            if (fn[i] > max) {
                max = fn[i];
                idx = i;
            }
        }

        // 构建转移过程
        List<Integer> ans = new ArrayList<>();
        while (ans.size() != max) {
            ans.add(nums[idx]);
            idx = gn[idx];
        }
        return ans;
    }


    /**
     * 363. 矩形区域不超过 K 的最大数值和
     * matrix = [[1,0,1],[0,-2,3]], k = 2
     * => 2
     * matrix = [[2,2,-1]], k = 3
     * 输出：3
     *
     * @param matrix
     * @param k
     * @return
     */
    public int maxSumSubmatrix(int[][] matrix, int k) {

        // 初始化二维前缀数组
        int m = matrix.length, n = matrix[0].length;
        int[][] sum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }

        // 遍历前缀数组 [i,j] => [l,o] 范围的和
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int l = i; l <= m; l++) {
                    for (int o = j; o <= n; o++) {
                        int cur = sum[l][o] + sum[i - 1][j - 1] - sum[i - 1][o] - sum[l][j - 1];
                        if (cur < k) {
                            ans = Math.max(cur, ans);
                        }
                        if (cur == k) {
                            return k;
                        }
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 91. 解码方法
     * 11106
     * 当前解码 只和 当前位置, 或者前一位置有关.
     *
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        s = " " + s;
        char[] chars = s.toCharArray();
        // 创建滚动数组
        int[] fn = new int[]{1, 0, 0};
        for (int i = 1; i < chars.length; i++) {
            fn[i % 3] = 0;
            int a = chars[i] - '0', b = (chars[i - 1] - '0') * 10 + a;
            // a 符合要求, 可以从 i-1 转移过来
            if (a > 0 && a < 10) {
                fn[i % 3] = fn[(i - 1) % 3];
            }
            // b 符合要求, 可以从 i-2 转移过来
            if (b > 9 && b < 27) {
                fn[i % 3] += fn[(i - 2) % 3];
            }
        }
        return fn[(chars.length - 1) % 3];
    }

    /**
     * 寻找最长的公共子串
     * s   2   s   2
     * "" ""  ""  ""  ""
     * s ""  s  ""   s  ""
     * s ""  s  ""   s  ""
     * 2 ""  "" s2  ""   2
     * s ""  s  ""  s2s ""
     *
     * @param s1
     * @param s2
     * @return
     */
    public String longCommonStr(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length();
        String[][] fn = new String[n1 + 1][n2 + 1];
        // 初始化动态规划边界
        for (int i = 0; i < n1; i++) {
            fn[i][0] = "";
        }
        for (int i = 0; i < n2; i++) {
            fn[0][i] = "";
        }
        String ans = "";
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    fn[i + 1][j + 1] = fn[i][j] + s1.charAt(i);
                    if (fn[i + 1][j + 1].length() > ans.length()) {
                        ans = fn[i + 1][j + 1];
                    }
                } else {
                    fn[i + 1][j + 1] = "";
                }
            }
        }
        return ans;
    }

    public String longStr(String s1, String s2) {
        if (s1.length() < s2.length()) {
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }
        String ans = "";
        for (int i = 0; i < s2.length(); i++) {
            for (int j = i + 1; j <= s2.length(); j++) {
                String sub2 = s2.substring(i, j);
                if (s1.contains(sub2) && sub2.length() > ans.length()) {
                    ans = sub2;
                }
            }
        }
        return ans;
    }

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
