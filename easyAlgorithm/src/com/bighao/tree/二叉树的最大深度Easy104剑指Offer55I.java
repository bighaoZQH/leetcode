package com.bighao.tree;

import com.bighao.tree.btree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 */
public class 二叉树的最大深度Easy104剑指Offer55I {


    /**
     * 广度优先搜索 迭代
     */
    public static int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList();
        int heigh = 0;
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                size--;
            }
            heigh++;
        }
        return heigh;
    }

    /**
     * 深度优先搜索 递归
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * 深度优先搜索 迭代
     */
    public int maxDepthByDFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int maxDepth = 0;
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(new Node(root, 1));
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            maxDepth = Math.max(node.depth, maxDepth);
            TreeNode parent = node.node;
            if (parent.left != null) {
                stack.push(new Node(parent.left, node.depth + 1));
            }
            if (parent.right != null) {
                stack.push(new Node(parent.right, node.depth + 1));
            }
        }
        return maxDepth;
    }


    private class Node {
        TreeNode node;
        int depth;

        public Node(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

}
