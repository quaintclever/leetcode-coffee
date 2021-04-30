package com.quaint.leetcode.year2021;

import com.quaint.leetcode.abst.AbstractTest;

import java.util.Arrays;

import static com.quaint.leetcode.util.LcArrayUtil.get1dArr;
import static com.quaint.leetcode.util.LcArrayUtil.get2dArr;
import static com.quaint.leetcode.util.LcPrintUtil.printCompare;

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
        ps.add(AprilTest::singleNumber);
    }

    /**
     * ===============================================================
     */
    private static void singleNumber() {
        System.out.println("!!!测试开始执行!!!");
        printCompare(3, self.singleNumber(get1dArr("[2,2,3,2]")));
        printCompare(99, self.singleNumber(get1dArr("[0,1,0,1,0,1,99]")));
    }

    private static void canCross() {
        System.out.println("!!!测试开始执行!!!");
        printCompare(true, self.canCross(get1dArr("[0,1,3,5,6,8,12,17]")));
        printCompare(false, self.canCross(get1dArr("[0,1,2,3,4,8,9,11]")));
    }

    private static void shipWithinDays() {
        System.out.println("!!!测试开始执行!!!");
        int[] p = get1dArr("[1,2,3,4,5,6,7,8,9,10]");
        printCompare(15, self.shipWithinDays(p, 5));
        printCompare(160, self.shipWithinDays(get1dArr("[10,50,100,100,50,100,100,100]"), 5));
    }

    private static void largestDivisibleSubset() {
        System.out.println("!!!测试开始执行!!!");
        printCompare(Arrays.asList(4, 8, 240), self.largestDivisibleSubset(get1dArr("[4,8,10,240]")));
        printCompare(Arrays.asList(1, 2), self.largestDivisibleSubset(get1dArr("[1,3,2]")));
        printCompare(Arrays.asList(1, 2, 4, 8), self.largestDivisibleSubset(get1dArr("[1,2,4,8]")));
    }

    private static void maxSumSubmatrix() {
        System.out.println("!!!测试开始执行!!!");
        printCompare(2, self.maxSumSubmatrix(get2dArr("[[1,0,1],[0,-2,3]]"), 2));
        printCompare(3, self.maxSumSubmatrix(get2dArr("[[1,0,1],[0,-2,3]]"), 2));
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
