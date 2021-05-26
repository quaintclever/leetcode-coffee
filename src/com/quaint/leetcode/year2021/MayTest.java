package com.quaint.leetcode.year2021;

import com.quaint.leetcode.abst.AbstractTest;

import java.util.List;

import static com.quaint.leetcode.util.LcDataProcessUtil.*;
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
        ps.add(MayTest::reverseParentheses);
    }

    /**
     * ===============================================================
     */
    private static void reverseParentheses() {
        printCompare("iloveu", self.reverseParentheses("(u(love)i)"));
        printCompare("aqcvbhcwer", self.reverseParentheses("a(ch(qc(bv)))wer"));
    }

    private static void maxUncrossedLines() {
        printCompare(2, self.maxUncrossedLines(get1dArr("[1,4,2]"), get1dArr("[1,2,4]")));
        printCompare(3, self.maxUncrossedLines(get1dArr("[2,5,1,2,5]"), get1dArr("[10,5,2,1,5,2]")));
    }

    private static void topKFrequent() {
        printCompare(get1dArrStr("[\"i\", \"love\"]"), self.topKFrequent(get1dArrStr("[\"i\", \"love\", \"leetcode\", \"i\", \"love\", \"coding\"]"), 2));
    }

    private static void kthLargestValue() {
        printCompare(7, self.kthLargestValue(get2dArr("[[5,2],[1,6]]"), 1));
    }

    private static void countTriplets() {
        printCompare(4, self.countTriplets(get1dArr("[2,3,1,6,7]")));
    }

    private static void isCousins() {
        printCompare(false, self.isCousins(getTree("[1,2,3,4]"),4,3));
    }

    private static void intToRoman() {
        printCompare("MCMXCIV", self.intToRoman(1994));
        printCompare("LVIII", self.intToRoman(58));
    }

    private static void numWays() {
        printCompare(4, self.numWays(3,2));
    }

    private static void minDays() {
        printCompare(3, self.minDays(get1dArr("[1,10,3,10,2]"), 3,1));
        printCompare(9, self.minDays(get1dArr("[1,10,2,9,3,8,4,7,5,6]"), 4,2));
        printCompare(12, self.minDays(get1dArr("[7,7,7,7,12,7,7]"), 2,3));
    }

    private static void minimumTimeRequired() {
        printCompare(78, self.minimumTimeRequired(get1dArr("[46,13,54,51,38,49,54,67,26,78,33]"), 10));
        printCompare(501, self.minimumTimeRequired(get1dArr("[250,250,256,251,254,254,251,255,250,252,254,255]"), 10));
        printCompare(12, self.minimumTimeRequired(get1dArr("[5,5,4,4,4]"), 2));
        printCompare(9899456, self.minimumTimeRequired(get1dArr("[9899456,8291115,9477657,9288480,5146275,7697968,8573153,3582365,3758448,9881935,2420271,4542202]"), 9));
    }

    private static void plusOne() {
        printCompare(get1dArr("[1,0,0,0]"), self.plusOne(get1dArr("[9,9,9]")));
    }

    private static void countAndSay() {
        printCompare("1211", self.countAndSay(4));
    }

    private static void deleteAndEarn() {
        int[] arr = get1dArr("[1,1,1,1,2,3,4]");
        printCompare(9, self.deleteAndEarn(arr));
    }

    private static void minimumTotal() {
        List<List<Integer>> list = get2dList("[[2],[3,4],[6,5,7],[4,1,8,3]]");
        printCompare(11, self.minimumTotal(list));
    }

    private static void splitString() {
        printCompare(false, self.splitString("00000010000000000000"));
        printCompare(false, self.splitString("1"));
        printCompare(false, self.splitString("0"));
    }

    private static void closestRoom() {
        printCompare(get1dArr("[3,-1,3]"), self.closestRoom(new int[][]{{2,2},{1,2},{3,2}}, new int[][]{{3,1},{3,3},{5,2}}));
    }

    private static void nextGreaterElements() {
        printCompare(get1dArr("2,-1,2"), self.nextGreaterElements(get1dArr("[1,2,1]")));
    }

    private static void getImportance() {
        printCompare(0, self.getImportance(null, 1));
    }

}
