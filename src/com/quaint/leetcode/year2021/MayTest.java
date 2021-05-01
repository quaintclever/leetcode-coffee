package com.quaint.leetcode.year2021;

import com.quaint.leetcode.abst.AbstractTest;

import static com.quaint.leetcode.util.LcArrayUtil.get1dArr;
import static com.quaint.leetcode.util.LcPrintUtil.printCompare;

/**
 * <p>
 * desc: 如何让你遇见我，在我最美的时刻。
 * </p>
 *
 * @author quaint
 * @since 01 May 2021
 */
public class MayTest extends AbstractTest {

    private static final May self = new May();

    static {
        // 测试代码添加位置..
        ps.add(MayTest::nextGreaterElements);
    }

    /**
     * ===============================================================
     */
    private static void nextGreaterElements() {
        System.out.println("!!!测试开始执行!!!");
        printCompare(get1dArr("2,-1,2"), self.nextGreaterElements(get1dArr("[1,2,1]")));
    }

    private static void getImportance() {
        System.out.println("!!!测试开始执行!!!");
        printCompare(0, self.getImportance(null, 1));
    }

}
