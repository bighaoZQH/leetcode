package com.bighao.tree;


import com.bighao.tree.btree.TreeNode;

import java.util.ArrayList;
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
     * 迭代 自己写的 感觉比leetcode官方的要好理解
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> reuslt = new ArrayList<>();
        if (root == null) {
            return reuslt;
        }
        // 需要一个栈
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            // pop出parent节点
            TreeNode parent = stack.pop();
            reuslt.add(parent.val);
            // 先把right节点压入栈中
            if (parent.right != null) {
                stack.push(parent.right);
            }
            // 再把left节点压入栈中，然后循环处理...
            if (parent.left != null) {
                stack.push(parent.left);
            }
        }
        return reuslt;
    }

    /**
     * 迭代
     */
    public static List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> reuslt = new LinkedList<>();
        if (root == null) {
            return reuslt;
        }
        Deque<TreeNode> stack = new LinkedList();
        while (!stack.isEmpty() || root != null) {
            // 先处理parent自己和其左节点
            while (root != null) {
                reuslt.add(root.val);
                stack.push(root);
                root = root.left;
            }
            // 再处理parent的右节点
            root = stack.pop();
            root = root.right;
        }
        return reuslt;
    }

    /**
     * 递归
     */
    public static List<Integer> preorderTraversalRecursion(TreeNode root) {
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
    // 自己写的，感觉比官方的要好
    private void preorderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        preorderTraversal(root.right, result);
        preorderTraversal(root.left, result);
    }

}


