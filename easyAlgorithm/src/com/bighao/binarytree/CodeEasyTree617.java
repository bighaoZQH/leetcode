package com.bighao.binarytree;

/**
 * @Author: bighao周启豪
 * @Date: 2020/4/20 3:22
 * @Version 1.0
 */
public class CodeEasyTree617 {

    public static void main(String[] args) {
        Integer[] arr1 = {1, 3, 2, 5};
        Integer[] arr2 = {2, 1, 3, null, 4, null, 7};
        TreeNode t1 = createBinaryTreeByArray(arr1, 0);
        TreeNode t2 = createBinaryTreeByArray(arr2, 0);
        //pre(t1);
        TreeNode treeNode = mergeTrees(t1, t2);
        System.out.println(treeNode);
    }

    public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }

    // 数组转二叉树
    public static TreeNode createBinaryTreeByArray(Integer[] arr, int i) {
        if (i >= arr.length || arr[i] == null) {
            return null;
        }
        TreeNode root = new TreeNode(arr[i]);
        root.left = createBinaryTreeByArray(arr, 2 * i + 1);
        root.right = createBinaryTreeByArray(arr, 2 * i + 2);
        return root;
    }

    // 前序遍历
    public static void pre(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        pre(root.left);
        pre(root.right);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


}
