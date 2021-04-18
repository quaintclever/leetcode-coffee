package com.quaint.leetcode.year2021;

import com.quaint.leetcode.abst.AbstractTest;

import static com.quaint.leetcode.util.LcArrayUtil.get1dArr;

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
        ps.add(AprilTest::getXORSum);
    }

    private static void template() {
        int[] arr1 = get1dArr("[1,2,3]");
        int[] arr2 = get1dArr("[6,5]");

    }


    private static void getXORSum() {
        int[] arr1 = get1dArr("[1,2,3]");
        int[] arr2 = get1dArr("[6,5]");
        System.out.println(self.getXORSum(arr1, arr2));
        int[] arr3 = get1dArr("[12]");
        int[] arr4 = get1dArr("[4]");
        System.out.println(self.getXORSum(arr3, arr4));
    }
}
