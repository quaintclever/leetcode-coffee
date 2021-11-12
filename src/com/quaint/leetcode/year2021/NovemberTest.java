package com.quaint.leetcode.year2021;

import com.quaint.leetcode.abst.AbstractTest;

import static com.quaint.leetcode.util.LcDataProcessUtil.*;
import static com.quaint.leetcode.util.LcPrintUtil.printCompare;

/**
 * @author quaint
 * @date 2021/11/12
 */
public class NovemberTest extends AbstractTest {

    private static final November self = new November();

    static {
        // 测试代码添加位置..
        ps.add(NovemberTest::getMoneyAmount);
    }

    private static void getMoneyAmount() {
        printCompare(self.getMoneyAmountCheck(16), self.getMoneyAmount(16));
        printCompare(self.getMoneyAmountCheck(1), self.getMoneyAmount(1));
        printCompare(self.getMoneyAmountCheck(2), self.getMoneyAmount(2));
        printCompare(self.getMoneyAmountCheck(3), self.getMoneyAmount(3));
        printCompare(self.getMoneyAmountCheck(4), self.getMoneyAmount(4));
    }

}
