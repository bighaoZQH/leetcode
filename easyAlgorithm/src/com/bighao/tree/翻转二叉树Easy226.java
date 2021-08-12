package com.bighao.tree;


/**
 * https://leetcode-cn.com/problems/invert-binary-tree/
 */
public class 翻转二叉树Easy226 {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }

        TreeNode right = invertTree(root.left);
        TreeNode left =  invertTree(root.right);

        root.left = left;
        root.right = right;

        return root;
    }

    public static void main(String[] args) {

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
