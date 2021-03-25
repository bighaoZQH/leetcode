package com.bighao.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 *
 * 二叉树的遍历。。
 * https://zhuanlan.zhihu.com/p/73438175?utm_source=qq
 */
public class 二叉树的前序遍历Medium144 {


    /**
     * 迭代
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> reuslt = new LinkedList<>();
        if (root == null) {
            return reuslt;
        }
        Deque<TreeNode> stack = new LinkedList();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                reuslt.add(root.val);
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }
        return reuslt;
    }

    /**
     * 递归
     */
    public static List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> reuslt = new LinkedList<>();
        if (root == null) {
            return reuslt;
        }
        preorderTraversalRecursion(root, reuslt);
        return reuslt;
    }

    private static void preorderTraversalRecursion(TreeNode root, List<Integer> reuslt) {
        reuslt.add(root.val);
        if (root.left != null) {
            preorderTraversalRecursion(root.left, reuslt);
        }
        if (root.right != null) {
            preorderTraversalRecursion(root.right, reuslt);
        }
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
        List<Integer> result = preorderTraversal(root);
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


