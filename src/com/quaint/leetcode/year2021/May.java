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
     * 993. 二叉树的堂兄弟节点
     *
     * @param root
     * @param x
     * @param y
     * @return
     */
    public boolean isCousins(TreeNode root, int x, int y) {
        Map<Integer, Integer> levelMap = new HashMap<>();
        Map<Integer, Integer> parentMap = new HashMap<>();
        dfs(levelMap, parentMap, root, 0, -1);
        return levelMap.get(x).equals(levelMap.get(y)) && !parentMap.get(x).equals(parentMap.get(y));
    }

    public void dfs(Map<Integer, Integer> levelMap, Map<Integer, Integer> parentMap, TreeNode node, int level, int parent) {
        if (node == null) return;
        dfs(levelMap, parentMap, node.left, level + 1, node.val);
        levelMap.put(node.val, level);
        parentMap.put(node.val, parent);
        dfs(levelMap, parentMap, node.right, level + 1, node.val);
    }

    /**
     * 12. 整数转罗马数字
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     *
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        String[] bit1 = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] bit2 = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] bit3 = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] bit4 = {"", "M", "MM", "MMM", "", "", "", "", "", ""};
        String[][] bitAll = {bit1, bit2, bit3, bit4};
        int count = 0;
        StringBuilder ans = new StringBuilder();
        while (num != 0 && count < 4) {
            ans.insert(0, bitAll[count][num % 10]);
            num /= 10;
            count++;
        }
        return ans.toString();
    }

    /**
     * 1269. 停在原地的方案数
     *
     * @param steps
     * @param arrLen
     * @return
     */
    Map<String, Long> numWaysCache = new HashMap<>();

    public int numWays(int steps, int arrLen) {
        return (int) dfs(steps, arrLen, 0);
    }

    public long dfs(int steps, int arrLen, int idx) {
        if (idx < 0 || idx >= arrLen) return 0;
        if (steps == 0) return idx == 0 ? 1 : 0;

        // 设置缓存
        String key = steps + "-" + idx;
        if (numWaysCache.containsKey(key)) {
            return numWaysCache.get(key);
        }

        // 递归遍历
        long res = dfs(steps - 1, arrLen, idx + 1)
                + dfs(steps - 1, arrLen, idx)
                + dfs(steps - 1, arrLen, idx - 1);
        res %= MOD;
        numWaysCache.put(key, res);
        return res;
    }


    /**
     * 1734. 解码异或后的排列
     *
     * @param encoded
     * @return
     */
    public int[] decode(int[] encoded) {
        // 计算总异或 和
        int n = encoded.length + 1;
        int all = 1;
        for (int i = 2; i <= n; i++) {
            all ^= i;
        }
        // 计算第一个 perm, 偶数异或即可, 因为为奇数 v 选择, x 不选择
        // [1,2]x   [2,3]v   [3,4]x  [4,5]v
        int first = all;
        for (int i = 1; i < n - 1; i++) {
            if ((i & 1) == 1) {
                first ^= encoded[i];
            }
        }
        // 计算结果
        int[] ans = new int[n];
        ans[0] = first;
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] ^ encoded[i - 1];
        }
        return ans;
    }

    /**
     * 1482. 制作 m 束花所需的最少天数
     */
    class Index {
        public Integer key;
        public Integer val;

        public Index(Integer key, Integer val) {
            this.key = key;
            this.val = val;
        }
    }

    public int minDays(int[] bloomDay, int m, int k) {
        int ans = -1, n = bloomDay.length;
        if (m * k > n) return ans;
        if (m * k == n) return Arrays.stream(bloomDay).max().orElse(ans);

        // 降序
        Queue<Index> pq = new PriorityQueue<>((a, b) -> b.key - a.key);
        for (int i = 0; i < bloomDay.length; i++) {
            pq.offer(new Index(bloomDay[i], i));
        }

        TreeSet<Integer> set = new TreeSet<>();
        int flows = n / k;
        while (flows >= m && !pq.isEmpty()) {
            // 最大的key
            Index poll = pq.poll();
            ans = poll.key;
            int idx = poll.val;
            set.add(idx);
            Integer up = set.ceiling(idx + 1);
            up = up == null ? n : up;
            Integer down = set.floor(idx - 1);
            down = down == null ? -1 : down;
            flows -= (up - down - 1) / k - (idx - down - 1) / k - (up - idx - 1) / k;
        }
        return ans;
    }

    /**
     * 1723. 完成所有工作的最短时间
     * 输入：jobs = [1,2,4,7,8], k = 2
     * 输出：11
     * 解释：按下述方式分配工作：
     * 1 号工人：1、2、8（工作时间 = 1 + 2 + 8 = 11）
     * 2 号工人：4、7（工作时间 = 4 + 7 = 11）
     * 最大工作时间是 11 。
     */
    int[] jobs;
    int n, k;
    // 较大值.
    int ans = 0x3f3f3f3f;

    public int minimumTimeRequired(int[] jobs, int k) {
        // 初始化代码
        ans = 0x3f3f3f3f;

        this.jobs = jobs;
        this.n = jobs.length;
        this.k = k;
        int[] worker = new int[k];

        dfs(0, worker, 0, 0);
        return ans;
    }

    /**
     * 工作分配
     *
     * @param idx    当前工作
     * @param worker 工作分配情况
     * @param max    工人的最大用时
     */
    public void dfs(int idx, int[] worker, int max, int used) {
        // 结束条件
        if (max >= ans) return;
        if (idx == n) {
            ans = max;
            return;
        }

        // 优化分配策略
        if (used < k) {
            worker[used] += jobs[idx];
            dfs(idx + 1, worker, Math.max(worker[used], max), used + 1);
            worker[used] -= jobs[idx];
        }
        for (int i = 0; i < used; i++) {
            worker[i] += jobs[idx];
            dfs(idx + 1, worker, Math.max(worker[i], max), used);
            worker[i] -= jobs[idx];
        }
    }

    /**
     * 66. 加一
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        int[] f = new int[n];
        int[] f2 = new int[n + 1];
        int one = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] + one > 9) {
                if (i != 0) {
                    f[i] = (digits[i] + 1) % 10;
                    f2[i + 1] = f[i];
                } else {
                    f2[i + 1] = f[i];
                    f2[i] = 1;
                    return f2;
                }
            } else {
                f[i] = digits[i] + one;
                f2[i + 1] = f[i];
                one = 0;
            }
        }
        return f;
    }

    /**
     * 38. 外观数列
     *
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        String ans = "1";
        while (n > 1) {
            String temp = "";
            int num = 0;
            char cur = ans.charAt(0);
            for (int i = 0; i < ans.length(); i++) {
                if (ans.charAt(i) == cur) {
                    num++;
                } else {
                    temp = temp + num + cur;
                    num = 1;
                    cur = ans.charAt(i);
                }
            }
            if (num > 0) {
                temp = temp + num + cur;
            }
            ans = temp;
            n--;
        }
        return ans;
    }


    /**
     * 14. 最长公共前缀
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String first = strs[0];
        StringBuilder ans = new StringBuilder();
        end:
        for (int i = 0; i < first.length(); i++) {
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || first.charAt(i) != strs[j].charAt(i)) {
                    break end;
                }
            }
            ans.append(first.charAt(i));
        }
        return ans.toString();
    }

    /**
     * 740. 删除并获得点数
     *
     * @param nums
     * @return
     */
    public int deleteAndEarn(int[] nums) {
        int[] help = new int[10001];
        int limit = 0;
        for (int i : nums) {
            help[i] += i;
            limit = Math.max(limit, i);
        }
        for (int i = 2; i <= limit; i++) {
            help[i] = Math.max(help[i - 2] + help[i], help[i - 1]);
        }
        return help[limit];
    }

    /**
     * 120. 三角形最小路径和
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] f = new int[2][n];
        f[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (j == 0) {
                    f[i & 1][j] = f[(i - 1) & 1][j] + triangle.get(i).get(j);
                } else {
                    f[i & 1][j] = Math.min(f[(i - 1) & 1][j], f[(i - 1) & 1][j - 1]) + triangle.get(i).get(j);
                }
            }
            f[i & 1][i] = f[(i - 1) & 1][i - 1] + triangle.get(i).get(i);
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            ans = Math.min(f[(n - 1) & 1][i], ans);
        }
        return ans;
    }

    /**
     * 1473. 粉刷房子 III
     *
     * @param houses 房子数组
     * @param cost   每个房子 染指定颜色花费
     * @param m      m个 房子
     * @param n      n种 颜色
     * @param k      目标街区数量
     * @return 最小花费
     */
    public int minCost(int[] houses, int[][] cost, int m, int n, int k) {
        int inf = 0x3f3f3f3f;
        // 定义 f[m][n][k] 为考虑前 m 间房子，且第 m 间房子的颜色编号为 n，分区数量为 k 的所有方案中的「最小上色成本」。
        int[][][] fn = new int[m + 1][n + 1][k + 1];
        for (int i = 0; i < fn.length; i++) {
            for (int j = 0; j < fn[0].length; j++) {
                fn[i][j][0] = inf;
            }
        }

        for (int i = 1; i <= m; i++) {
            int color = houses[i - 1];
            for (int j = 1; j <= n; j++) {
                for (int l = 1; l <= k; l++) {
                    // 如果分区大于 房子数量
                    if (l > i) {
                        fn[i][j][l] = inf;
                        continue;
                    }
                    // 房子已上色
                    if (color != 0) {
                        if (j != color) {
                            fn[i][j][l] = inf;
                        } else {
                            int temp = inf;
                            for (int o = 1; o <= n; o++) {
                                if (o != j) {
                                    temp = Math.min(fn[i - 1][o][l - 1], temp);
                                }
                            }
                            fn[i][j][l] = Math.min(fn[i - 1][j][l], temp);
                        }
                    }
                    // 房子未上色
                    else {
                        int u = cost[i - 1][j - 1];
                        int temp = inf;
                        // 形成新分区找最优
                        for (int o = 1; o <= n; o++) {
                            if (o != j) {
                                temp = Math.min(fn[i - 1][o][l - 1], temp);
                            }
                        }
                        // 再结合「第 i 间房不形成新分区」方案中选最优（即与上一房间颜色相同）
                        // 并将「上色成本」添加进去
                        fn[i][j][l] = Math.min(fn[i - 1][j][l], temp) + u;
                    }
                }
            }
        }

        // 找到最小的花费
        int ans = inf;
        for (int i = 1; i <= n; i++) {
            ans = Math.min(ans, fn[m][i][k]);
        }
        return ans == inf ? -1 : ans;
    }

    /**
     * 131. 分割回文串
     *
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        // 判断回文串.
        int n = s.length();
        // fn[i][j] 表示 从i - j 是否是回文串
        boolean[][] fn = new boolean[n][n];
        char[] chars = s.toCharArray();
        for (int j = 0; j < chars.length; j++) {
            for (int i = j; i >= 0; i--) {
                // 如果是 1 位 则是
                if (j == i) {
                    fn[i][j] = true;
                } else {
                    // 如果是 2 位
                    if (j - i == 1) {
                        fn[i][j] = chars[i] == chars[j];
                    } else {
                        // 大于 2 位
                        fn[i][j] = fn[i + 1][j - 1] && chars[i] == chars[j];
                    }
                }
            }
        }
        List<List<String>> ans = new ArrayList<>();
        List<String> cur = new ArrayList<>();
        dfs(ans, cur, fn, s, 0);
        return ans;
    }

    /**
     * dfs
     *
     * @param ans 答案数组
     * @param cur 当前是回文串的字符串
     * @param fn  回文串状态
     * @param str str
     * @param u   以 s 中的那一位作为回文串分割起点
     */
    public void dfs(List<List<String>> ans, List<String> cur, boolean[][] fn, String str, int u) {
        int n = str.length();
        if (u == n) {
            // 防止其他地方移除
            ans.add(new ArrayList<>(cur));
        }
        for (int i = u; i < n; i++) {
            if (fn[u][i]) {
                cur.add(str.substring(u, i + 1));
                dfs(ans, cur, fn, str, i + 1);
                cur.remove(cur.size() - 1);
            }
        }
    }

    /**
     * 554. 砖墙
     *
     * @param wall
     * @return
     */
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> map = new HashMap<>();
        // 遍历 缝隙的数量
        for (List<Integer> bricks : wall) {
            int gap = 0;
            for (Integer brick : bricks) {
                gap += brick;
                if (map.get(gap) != null) {
                    map.put(gap, map.get(gap) + 1);
                } else {
                    map.put(gap, 1);
                }
            }
            // 移除最后一个缝隙.
            map.remove(gap);
        }
        // 找到最多的缝隙
        int size = wall.size();
        int minGap = size;
        for (Integer key : map.keySet()) {
            minGap = Math.min(minGap, size - map.get(key));
        }
        return minGap;
    }

    /**
     * 5747. 将字符串拆分为递减的连续值
     */
    Map<String, Boolean> cache = new HashMap<>();

    public boolean splitString(String s) {
        // 去除前缀 0
        while (s.charAt(0) == '0' && s.length() > 1) {
            s = s.substring(1);
        }

        if (s.length() <= 1) {
            return false;
        }

        char[] chars = s.toCharArray();
        String num = "";
        for (int i = 0; i < chars.length && i < 18; i++) {
            num += chars[i];
            long first = Long.parseLong(num);
            if (dfs(s.substring(i + 1), first - 1)) {
                return true;
            }
        }
        return false;
    }

    public boolean dfs(String s, long preNum) {
        String cacheKey = s + "-" + preNum;
        if (cache.get(s + "-" + preNum) != null) {
            return cache.get(cacheKey);
        }
        if (s.length() == 0) {
            return false;
        }
        if (preNum == 0) {
            long zero = Long.parseLong(s);
            return zero == 0;
        }
        // 寻找比 pre 小 1 的数字
        char[] chars = s.toCharArray();
        String num = "";
        for (int i = 0; i < chars.length; i++) {
            num += chars[i];
            long first = Long.parseLong(num);
            if (first > preNum) {
                return false;
            }
            if (first == preNum) {
                if (s.length() > num.length()) {
                    boolean dfs = dfs(s.substring(i + 1), first - 1);
                    cache.put(cacheKey, dfs);
                    return dfs;
                } else {
                    cache.put(cacheKey, true);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 5746. 到目标元素的最小距离
     */
    public int getMinDistance(int[] nums, int target, int start) {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                ans = Math.min(ans, Math.abs(start - i));
            }
        }
        return ans;
    }


    /**
     * 5733. 最近的房间
     */
    public int[] closestRoom(int[][] rooms, int[][] qs) {

        int n = rooms.length, m = qs.length;
        int[] res = new int[m];

        // 建立问题索引
        Integer[] pos = new Integer[m];
        for (int i = 0; i < m; i++) {
            pos[i] = i;
        }

        // 索引根据 需求面积 降序排序
        Arrays.sort(pos, (o1, o2) -> qs[o2][1] - qs[o1][1]);
        // 房屋 2级排序, 面积 > id  升序排序
        Arrays.sort(rooms, (o1, o2) -> o2[1] == o1[1] ? o1[0] - o2[0] : o2[1] - o1[1]);
        int k = 0;
        TreeSet<Integer> set = new TreeSet<>();
        for (int x : pos) {
            int id = qs[x][0], area = qs[x][1];
            while (k < n && rooms[k][1] >= area) {
                set.add(rooms[k++][0]);
            }
            if (set.size() == 0) {
                res[x] = -1;
            } else {
                // 返回 小于或等于 id 的值
                Integer floor = set.floor(id);
                // 返回 大于或等于 id 的值
                Integer ceil = set.ceiling(id);
                if (floor != null && ceil != null) {
                    res[x] = Math.abs(floor - id) <= Math.abs(ceil - id) ? floor : ceil;
                } else {
                    res[x] = floor == null ? ceil : floor;
                }
            }
        }
        return res;
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
