package com.quaint.leetcode.abst;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * desc: 抽象test
 * </p>
 *
 * @author quaint
 * @since 18 April 2021
 */
public abstract class AbstractTest {

    protected static List<Processor> ps = new ArrayList<>();

    public void execute() {
        ps.forEach(p -> {
            System.out.println("=====================");
            p.process();
            System.out.println("=====================");
        });
    }

    /**
     * 常用测试变量定义
     */
    protected static int[] absArrNull = {};
    protected static int[] absArr0 = {0};
    protected static int[] absArr123 = {1, 2, 3};
    protected static int[] absArr321 = {3, 2, 1};

    protected static int[][] absArr2dNull = {{}};
    protected static int[][] absArr2d_1_1 = {{1}};
    protected static int[][] absArr2d_1_3 = {{1, 2, 3}};
    protected static int[][] absArr2d_2_3 = {{1, 2, 3},{1, 2, 3}};
    protected static int[][] absArr2d_3_3 = {{1, 2, 3},{1, 2, 3},{1, 2, 3}};

}
