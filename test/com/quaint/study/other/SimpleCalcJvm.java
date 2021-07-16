package com.quaint.study.other;

/**
 * @author quaint
 * @date 2021/7/16
 */
public class SimpleCalcJvm {

    public static void main(String[] args) {
        calcJvm(8);
    }

    /**
     * JVM内存换算公式:
     * 初始堆内存：            Xms = ms_mem = jvm_mem * 3/4
     * 最大堆内存:             Xmx = mx_mem = jvm_mem * 3/4
     * 年轻代内存：            Xmn = mn_mem = jvm_mem * 9/32
     * 元空间大小(老年代)       max_meta_mem =  jvm_mem / 8
     * 线程的Stack大小         Xss = 1MB
     * @param mem jvm 内存
     */
    public static void calcJvm(int mem) {
        // 转换为 MB
        mem = mem * (2 << 9);

        int xms = mem * 3 >> 2;
        int xmx = mem * 3 >> 2;
        int xmn = mem * 9 >> 5;
        int max_meta_mem = mem >> 3;

        // 打印内存
        System.out.println("\"ms_mem\":" + "\"" + xms + "\"");
        System.out.println("\"mx_mem\":" + "\"" + xmx + "\"");
        System.out.println("\"mn_mem\":" + "\"" + xmn + "\"");
        System.out.println("\"max_meta_mem\":" + "\"" + max_meta_mem + "\"");
    }

}
