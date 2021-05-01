package com.quaint.leetcode.year2021;

import com.quaint.leetcode.util.LcDataStructure;

import java.util.*;
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
     * 690. 员工的重要性
     */
    public int getImportance(List<Employee> employees, int id) {
        if (employees == null) {
            return 0;
        }
        Map<Integer, Employee> map = employees.stream().collect(Collectors.toMap(e -> e.id, e -> e));
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
