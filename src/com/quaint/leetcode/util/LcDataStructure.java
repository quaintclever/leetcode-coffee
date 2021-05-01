package com.quaint.leetcode.util;

import java.util.List;

/**
 * @author quaint
 * @date 2021/4/25
 */
public abstract class LcDataStructure {

    /**
     * Definition for a binary tree node.
     */
    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Definition for Employee.
     */
    public class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    };

}
