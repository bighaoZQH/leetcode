package com.bighao.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 */
public class 二叉树的后序遍历Medium145 {


    /**
     * 迭代
     * 6 9 8 7 4 5 2 3 1
     */
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> reuslt = new LinkedList<>();
        if (root == null) {
            return reuslt;
        }
        Deque<TreeNode> stack = new LinkedList();
        // 用来指向上一个打印的节点
        TreeNode prev = null;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == prev) {
                reuslt.add(root.val);
                // 将prev设置为当前节点
                prev = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
        return reuslt;
    }

    /**
     * 递归
     */
    public static List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> reuslt = new LinkedList<>();
        if (root == null) {
            return reuslt;
        }
        postorderTraversalRecursion(root, reuslt);
        return reuslt;
    }

    private static void postorderTraversalRecursion(TreeNode root, List<Integer> reuslt) {
        if (root.left != null) {
            postorderTraversalRecursion(root.left, reuslt);
        }
        if (root.right != null) {
            postorderTraversalRecursion(root.right, reuslt);
        }
        reuslt.add(root.val);
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode t1 = new TreeNode(2);
        TreeNode t2 = new TreeNode(3);
        TreeNode t3 = new TreeNode(4);
        TreeNode t4 = new TreeNode(5);
        TreeNode t5 = new TreeNode(6);
        TreeNode t6 = new TreeNode(7);
        TreeNode t7 = new TreeNode(8);
        TreeNode t8 = new TreeNode(9);
        root.left = t1;
        root.right = t2;
        t1.left = t3;
        t1.right = t4;
        t3.left = t5;
        t3.right = t6;
        t6.left = t7;
        t7.right = t8;
        List<Integer> result = postorderTraversal(root);
        result.forEach(i -> System.out.print(i + " "));
    }


    public static class TreeNode {
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


