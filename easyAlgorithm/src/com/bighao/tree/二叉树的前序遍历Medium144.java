package com.bighao.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/2/18 10:14
 */
public class 二叉树的前序遍历Medium144 {


    /**
     * 迭代
     */
    public static List<Integer> preorderTraversal2(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        Deque<TreeNode> stack = new LinkedList<>();

        return list;
    }

    /**
     * 递归
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        preorderTraversal(root, list);
        return list;
    }

    public static void preorderTraversal(TreeNode root, List<Integer> list) {
        list.add(root.val);
        if (root.left != null) {
            preorderTraversal(root.left, list);
        }
        if (root.right != null) {
            preorderTraversal(root.right, list);
        }
    }

    public static void main(String[] args) {
        
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
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

}
