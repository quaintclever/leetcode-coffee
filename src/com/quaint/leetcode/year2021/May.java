package com.quaint.leetcode.year2021;

import com.quaint.leetcode.util.LcDataStructure;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * desc: 如何让你遇见我，在我最美的时刻。
 * </p>
 *
 * @author quaint
 * @since 01 May 2021
 */
public class May extends LcDataStructure {

    /**
     * 503. 下一个更大元素 II
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        int find, n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n; i++) {
            find = (i + 1) % n;
            while (find != i) {
                if (nums[find] > nums[i]) {
                    ans[i] = nums[find];
                    break;
                }
                find = (find + 1) % n;
            }
        }
        return ans;
    }

    public int[] nextGreaterElements2(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Deque<Integer> stk = new LinkedList<>();
        for (int i = 0; i < n * 2 - 1; i++) {
            while (!stk.isEmpty() && nums[stk.peek()] < nums [i % n]) {
                ans[stk.pop()] = nums[i % n];
            }
            stk.push(i % n);
        }
        return ans;
    }

    /**
     * 303. 区域和检索 - 数组不可变
     */
    class NumArray {
        int[] sums;
        public NumArray(int[] nums) {
            int n = nums.length;
            sums = new int[n + 1];
            for(int i = 0; i < n; i++) {
                sums[i+1] = nums[i] + sums[i];
            }
        }

        public int sumRange(int left, int right) {
            return sums[right + 1] - sums[left];
        }
    }

    /**
     * 690. 员工的重要性
     */
    class MyTask extends RecursiveTask<Integer> {

        Map<Integer, Employee> map;
        int sum;
        int id;

        public MyTask(int sum, int id, Map<Integer, Employee> map) {
            this.sum = sum;
            this.id = id;
            this.map = map;
        }

        @Override
        protected Integer compute() {
            Employee employee = map.get(id);
            sum += employee.importance;
            employee.subordinates.forEach(i -> sum += new MyTask(0, i, map).fork().join());
            return sum;
        }
    }

    public int getImportanceForkJoin(List<Employee> employees, int id) {
        Map<Integer, Employee> map = employees.stream().parallel()
                .collect(Collectors.toMap(e -> e.id, Function.identity()));
        ForkJoinPool fj = new ForkJoinPool();
        return fj.invoke(new MyTask(0, id, map));
    }

    public int getImportance(List<Employee> employees, int id) {
        if (employees == null) {
            return 0;
        }
        Map<Integer, Employee> map = employees.stream().collect(Collectors.toMap(e -> e.id, Function.identity()));
        int ans = 0;
        // Set<Integer> cache = new HashSet<>();
        Deque<Integer> stk = new LinkedList<>();
        stk.push(id);
        while (!stk.isEmpty()) {
            Employee pop = map.get(stk.pop());
            ans += pop.importance;
            for (Integer sub : pop.subordinates) {
                // 防止2个领导管同一个下级， 不加也没问题，leetcode 没有这种用例
                // if (!cache.contains(sub)) {
                stk.push(sub);
                // cache.add(sub);
                // }
            }
        }
        return ans;
    }
}
