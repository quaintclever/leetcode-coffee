package com.quaint.leetcode.year2021;

import com.quaint.leetcode.abst.AbstractTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.quaint.leetcode.util.LcArrayUtil.get1dArr;
import static com.quaint.leetcode.util.LcArrayUtil.get2dArr;
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
        ps.add(MayTest::minimumTotal);
    }

    /**
     * ===============================================================
     */
    private static void minimumTotal() {
        System.out.println("!!!测试开始执行!!!");
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(2));
        list.add(Arrays.asList(3,4));
        list.add(Arrays.asList(6,5,7));
        list.add(Arrays.asList(4,1,8,3));
        printCompare(11, self.minimumTotal(list));
    }

    private static void splitString() {
        System.out.println("!!!测试开始执行!!!");
        printCompare(false, self.splitString("00000010000000000000"));
        printCompare(false, self.splitString("1"));
        printCompare(false, self.splitString("0"));
    }

    private static void closestRoom() {
        System.out.println("!!!测试开始执行!!!");
        printCompare(get1dArr("[3,-1,3]"), self.closestRoom(new int[][]{{2,2},{1,2},{3,2}}, new int[][]{{3,1},{3,3},{5,2}}));
    }

    private static void nextGreaterElements() {
        System.out.println("!!!测试开始执行!!!");
        printCompare(get1dArr("2,-1,2"), self.nextGreaterElements(get1dArr("[1,2,1]")));
    }

    private static void getImportance() {
        System.out.println("!!!测试开始执行!!!");
        printCompare(0, self.getImportance(null, 1));
    }

}
