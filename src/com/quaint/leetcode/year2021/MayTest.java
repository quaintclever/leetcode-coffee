package com.quaint.leetcode.year2021;

import com.quaint.leetcode.abst.AbstractTest;

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
        ps.add(MayTest::getImportance);
    }

    /**
     * ===============================================================
     */
    private static void getImportance() {
        System.out.println("!!!测试开始执行!!!");
        printCompare(0, self.getImportance(null, 1));
    }

}
