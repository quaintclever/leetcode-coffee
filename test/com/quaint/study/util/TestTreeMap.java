package com.quaint.study.util;

import java.util.TreeMap;

/**
 * @author quaint
 * @date 2021/4/26
 */
public class TestTreeMap {

    public static void main(String[] args) {

        // 测试 TreeMap 的排序
        TreeMap<String, Object> sortMap = new TreeMap<>();
        sortMap.put("abc", "quaint");
        sortMap.put("sign", "quaint2");
        sortMap.put("auth", "quaint3");
        sortMap.put("name", "quaint4");
        sortMap.put("age", "quaint5");
        sortMap.forEach((k, v) -> System.out.println(k + " <- k | v -> " + v));

    }

}
