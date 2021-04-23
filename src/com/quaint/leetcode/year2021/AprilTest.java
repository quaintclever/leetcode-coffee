package com.quaint.leetcode.year2021;

import com.quaint.leetcode.abst.AbstractTest;
import com.quaint.leetcode.util.LcPrintUtil;

import java.util.Arrays;

import static com.quaint.leetcode.util.LcArrayUtil.get1dArr;
import static com.quaint.leetcode.util.LcArrayUtil.get2dArr;

/**
 * <p>
 * desc: 四月是你的谎言, leetcode 测试.
 * </p>
 *
 * @author quaint
 * @since 18 April 2021
 */
public class AprilTest extends AbstractTest {

    private static final April self = new April();

    static {
        // 测试代码添加位置..
        ps.add(AprilTest::largestDivisibleSubset);
    }

    /**
     * ===============================================================
     */
    private static void largestDivisibleSubset() {
        System.out.println("!!!测试开始执行!!!");
        LcPrintUtil.printCompare(Arrays.asList(4, 8, 240), self.largestDivisibleSubset(get1dArr("[4,8,10,240]")));
        LcPrintUtil.printCompare(Arrays.asList(1, 2), self.largestDivisibleSubset(get1dArr("[1,3,2]")));
        LcPrintUtil.printCompare(Arrays.asList(1, 2, 4, 8), self.largestDivisibleSubset(get1dArr("[1,2,4,8]")));
    }

    private static void maxSumSubmatrix() {
        System.out.println("!!!测试开始执行!!!");
        LcPrintUtil.printCompare(2, self.maxSumSubmatrix(get2dArr("[[1,0,1],[0,-2,3]]"), 2));
        LcPrintUtil.printCompare(3, self.maxSumSubmatrix(get2dArr("[[1,0,1],[0,-2,3]]"), 2));
    }

    private static void numDecodings() {
        System.out.println("!!!测试开始执行!!!");
        System.out.println(self.numDecodings("2101"));
    }

    private static void longCommonStr() {
        System.out.println("!!!测试开始执行!!!");
        System.out.println(self.longCommonStr("abdfeghi123x", "bcdweghi12xjg"));
    }

    private static void compareVersion() {
        System.out.println("!!!测试开始执行!!!");
        System.out.println(self.compareVersion("7.5.2.4", "7.5.3"));
        System.out.println(self.compareVersion("1.0", "1"));
    }


    private static void getXORSum() {
        System.out.println("!!!测试开始执行!!!");
        int[] arr1 = get1dArr("[1,2,3]");
        int[] arr2 = get1dArr("[6,5]");
        System.out.println(self.getXORSum(arr1, arr2));
    }
}
