package com.quaint.study.util;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author quaint
 * @date 2021/11/9
 */
public class TestReentrantLock {

    private final static ReentrantLock lock = new ReentrantLock(true);
    private final static Condition c1 = lock.newCondition();
    private final static Condition c2 = lock.newCondition();


    public static void main(String[] args) throws InterruptedException {

        System.out.println("start.............");

        new Thread(() -> {
            try {
                lock.lock();
                c1.await();
                System.out.println("c1 signaled.............");
                c2.signal();
                lock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                lock.lock();
                // 唤醒c1
                c1.signal();
                c2.await();
                System.out.println("c2 signaled.............");
                lock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        TimeUnit.SECONDS.sleep(1);
        System.out.println("end .............");
    }

}
