package com.quaint.leetcode.year2021;

import com.quaint.leetcode.util.LcDataStructure;

/**
 * @author quaint
 * @date 2021/11/12
 */
public class November extends LcDataStructure {

    /**
     * 375. 猜数字大小 II
     * @param n
     * @return
     */
    public int getMoneyAmount(int n) {
        return dfs(1, n);
    }

    static int[][] cache = new int[201][201];
    private int dfs(int left, int right) {
        if (left >= right) {
            return 0;
        }
        if (cache[left][right] != 0) {
            return cache[left][right];
        }
        int ans = Integer.MAX_VALUE;
        for (int i = left; i <= right; i++) {
            int temp = Math.max(dfs(left, i - 1), dfs(i + 1, right)) + i;
            ans = Math.min(temp, ans);
        }
        cache[left][right] = ans;
        return ans;
    }


    public int getMoneyAmountCheck(int n) {
        int[][] f = new int[n + 10][n + 10];
        for (int len = 2; len <= n; len++) {
            for (int l = 1; l + len - 1 <= n; l++) {
                int r = l + len - 1;
                f[l][r] = 0x3f3f3f3f;
                for (int x = l; x <= r; x++) {
                    int cur = Math.max(f[l][x - 1], f[x + 1][r]) + x;
                    f[l][r] = Math.min(f[l][r], cur);
                }
            }
        }
        return f[1][n];
    }

}
