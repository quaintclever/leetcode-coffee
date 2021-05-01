package com.quaint.leetcode;

import com.quaint.leetcode.abst.AbstractTest;
import com.quaint.leetcode.year2021.MayTest;

/**
 * <p>
 * desc: 启动类
 * </p>
 *
 * @author quaint
 * @since 18 April 2021
 */
public class QuaintMain {

    private static final AbstractTest test = new MayTest();

    public static void main(String[] args) {
        test.execute();
    }

}
