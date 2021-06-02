package com.quaint.leetcode.year2021;

import com.quaint.leetcode.abst.AbstractTest;

import static com.quaint.leetcode.util.LcDataProcessUtil.get1dArr;
import static com.quaint.leetcode.util.LcDataProcessUtil.get2dArr;
import static com.quaint.leetcode.util.LcPrintUtil.printCompare;

/**
 * 在你最喜欢的那天吃到你最喜欢的糖果？
 *
 * @author quaint
 * @date 2021/6/1
 */
public class JuneTest extends AbstractTest {

    private static final June self = new June();

    static {
        // 测试代码添加位置..
        ps.add(JuneTest::checkSubarraySum);
    }

    /**
     * ===============================================================
     */
    private static void checkSubarraySum() {
        printCompare(true, self.checkSubarraySum(get1dArr("[23,2,4,6,7]"), 13));
        printCompare(true, self.checkSubarraySum(get1dArr("[23,2,4,6,7]"), 6));
    }


}
