package com.quaint.study.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author quaint
 * @date 2021/5/6
 */
public class CollectionsTest {

    public static void main(String[] args) {

        // 测试 个list 是否可以增加
        List<Integer> integers = Collections.singletonList(1);
        // 下面会报错
        // integers.add(2);

        // 改为可以增加
        List<Integer> list = new ArrayList<>(integers);
        list.add(2);
        System.out.println(list.size());

        // 测试 asList 是否可以增加
        List<Integer> integers1 = Arrays.asList(1, 2);
        // 下面也会报错
        // integers1.add(3);

    }

}
