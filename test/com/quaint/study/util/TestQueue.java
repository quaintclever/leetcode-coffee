package com.quaint.study.util;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author quaint
 * @date 2021/4/19
 */
public class TestQueue {

    public static void main(String[] args) {
        // 测试优先队列
        testPriorityQueue();
    }

    /**
     * 测试优先队列
     */
    public static void testPriorityQueue() {
        Queue<Integer> pq = new PriorityQueue<>();
        // 添加方法
        pq.offer(7);
        pq.add(8);
        pq.offer(7);
        pq.offer(10);
        // 获取迭代器, 不按顺序遍历  7,8,7,10
        System.out.println("======= 迭代器, 不按顺序遍历 =======");
        Iterator<Integer> iterator = pq.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // 顺序遍历
        System.out.println("======= 顺序遍历 =======");
        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }

}
