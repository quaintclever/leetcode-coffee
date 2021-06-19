package com.quaint.leetcode.year2021;

import com.quaint.leetcode.abst.AbstractTest;

import java.util.Random;

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
        ps.add(JuneTest::rainStream);
    }

    /**
     * ===============================================================
     */
    private static void rainStream() {
        Random random = new Random(1);
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < 20; i++) {
            int r = (random.nextInt(100) + 1);
            sb.append(i % 2 == 0 ? r * -1 : r).append(",");
        }
        String arrStr = sb.substring(0, sb.length() - 2) + "]";
        printCompare("[-48:14, 5:-35, 49:-70, -18:64, -63:35, -93:63]", self.rainStream(get1dArr(arrStr)));
    }

    private static void findTargetSumWays() {
        printCompare(5, self.findTargetSumWays(get1dArr("[1,1,1,1,1]"), 3));
    }

    private static void findMaxLength() {
        printCompare(2, self.findMaxLength(get1dArr("[0,1]")));
    }

    private static void checkSubarraySum() {
        printCompare(true, self.checkSubarraySum(get1dArr("[23,2,4,6,7]"), 13));
        printCompare(true, self.checkSubarraySum(get1dArr("[23,2,4,6,7]"), 6));
    }


}
