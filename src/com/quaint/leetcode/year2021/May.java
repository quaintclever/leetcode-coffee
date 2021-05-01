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
     * 5733. 最近的房间
     * 未通过...
     */
    public int[] closestRoom(int[][] rooms, int[][] queries) {
        // 排序
        Arrays.sort(rooms, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] > o2[0] ? 1 : -1;
            }
            return o1[1] > o2[1] ? 1 : -1;
        });

        // 求最大面积
        int maxSize = 0;
        for (int i = 0; i < rooms.length; i++) {
            maxSize = Math.max(rooms[i][1], maxSize);
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int needSize = queries[i][1];
            if (needSize > maxSize) {
                ans[i] = -1;
                continue;
            }
            // 2分 查找 合适的面积
            int left = 0;
            int right = rooms.length - 1;
            int mid = right;
            while (left < right) {
                mid = left + right + 1 >> 1;
                if (rooms[mid][1] > needSize) {
                    right = mid - 1;
                } else {
                    left = mid;
                }
            }
            // 2分 查找 合适的房间id;
            int needId = queries[i][0];
            left = Math.min(left, mid);
            right = rooms.length - 1;
            while (left < right) {
                mid = left + right + 1 >> 1;
                if (rooms[mid][0] > needId) {
                    right = mid - 1;
                } else {
                    left = mid;
                }
            }
            ans[i] = rooms[left][0] < rooms[right][0] ? rooms[left][0] : rooms[right][0];
        }
        return ans;
    }


    /**
     * 减小和重新排列数组后的最大元素
     */
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        arr[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            if (Math.abs(arr[i] - arr[i - 1]) > 1) {
                arr[i] = arr[i - 1] + 1;
            }
        }
        return arr[arr.length - 1];
    }


    /**
     * 座位预约管理系统
     */
    class SeatManager {

        Queue<Integer> que = new PriorityQueue<>();
        int num = 0;

        public SeatManager(int n) {
        }

        public int reserve() {
            if (que.isEmpty()) {
                return ++num;
            } else {
                return que.poll();
            }
        }

        public void unreserve(int seatNumber) {
            if (seatNumber <= num) {
                que.offer(seatNumber);
            }
        }
    }


    /**
     * 将所有数字用字符替换
     */
    public String replaceDigits(String s) {
        int n = s.length();
        char[] sc = s.toCharArray();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < sc.length; i++) {
            if (i % 2 == 1) {
                ans.append((char) (sc[i - 1] + sc[i] - '0'));
            } else {
                ans.append(sc[i]);
            }
        }
        return ans.toString();
    }


    /**
     * 503. 下一个更大元素 II
     *
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
            while (!stk.isEmpty() && nums[stk.peek()] < nums[i % n]) {
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
            for (int i = 0; i < n; i++) {
                sums[i + 1] = nums[i] + sums[i];
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
