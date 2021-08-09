package com.bighao.tree;

import com.bighao.tree.btree.TreeNode;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 */
public class 二叉树的后序遍历Medium145 {


    /**
     * 老汤的迭代 根据前序遍历进行一个变形，然后反转
     * 前序: parent -> left -> right
     * 变形: parent -> right -> left 反转得到后续==>
     * 后序: left -> right -> parent
     *
     * 当然这里反转可以改进下，我干脆添加的时候直接添加到list的表头就行了
     */
    public List<Integer> postorderTraversalByDouma(TreeNode root) {
        LinkedList<Integer> reuslt = new LinkedList<>();
        if (root == null) {
            return reuslt;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode parent = stack.pop();
            //reuslt.add(parent.val);
            reuslt.addFirst(parent.val);
            if (parent.left != null) {
                stack.push(parent.left);
            }
            // 先处理右子树
            if (parent.right != null) {
                stack.push(parent.right);
            }
        }
        // 直接反转 得到的结果就是后序遍历的结果
        //Collections.reverse(reuslt);
        return reuslt;
    }

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
        // 用来指向上一个处理的节点
        TreeNode prev = null;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 如果当前节点没有右子树，或者其右子树已经被处理过了，则直接处理自己
            if (root.right == null || root.right == prev) {
                reuslt.add(root.val);
                // 将prev设置为当前节点
                prev = root;
                root = null;
            } else {
                // 如果当前节点有右子节点，且还没处理过，
                // 则还要把自己压入栈中，先处理完右子节点后，才能来处理自己
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

    // 自己的迭代
    private static void postorderTraversalRecursion2(TreeNode parent, List<Integer> reuslt) {
        if (parent == null) {
            return;
        }
        postorderTraversalRecursion(parent.left, reuslt);
        postorderTraversalRecursion(parent.right, reuslt);
        reuslt.add(parent.val);
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

}


