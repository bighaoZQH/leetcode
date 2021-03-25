package com.bighao.tree;

/**
 * https://leetcode-cn.com/problems/ping-heng-er-cha-shu-lcof/
 */
public class 平衡二叉树剑指EasyOffer55II {


    public static boolean isBalanced2(TreeNode root) {
        if (root == null) {
            return true;
        }
        return recur(root) == -1;
    }

    public static int recur(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = recur(root.left);
        // 判断当前节点的 左子树 是不是平衡树
        if (left == -1) {
            return -1;
        }
        int right = recur(root.right);
        // 判断当前节点的 右子树 是不是平衡树
        if (right == -1) {
            return -1;
        }
        // 如果两个子树的最大深度差超过1，则返回-1表示不是平衡树
        return Math.abs(left - right) <= 1 ? Math.max(left, right) + 1 : -1;
    }

    /**
     * abs(self.depth(root.left) - self.depth(root.right)) <= 1 ：判断 当前子树 是否是平衡树；
     * self.isBalanced(root.left) ： 先序遍历递归，判断 当前子树的左子树 是否是平衡树；
     * self.isBalanced(root.right) ： 先序遍历递归，判断 当前子树的右子树 是否是平衡树；
     */
    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int left = depth(root.left);
        int right = depth(root.right);
        return Math.abs(left - right) <= 1 &&
                isBalanced(root.left) &&
                isBalanced(root.right);
    }

    public static int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = depth(root.left);
        int right = depth(root.right);
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {

    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}