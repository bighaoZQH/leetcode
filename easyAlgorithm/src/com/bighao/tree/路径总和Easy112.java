package com.bighao.tree;

import com.bighao.tree.btree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/8/11 23:46
 */
public class 路径总和Easy112 {

    // BFS
    public boolean hasPathSumByBFS(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        Queue<TreeNode> queue = new LinkedList();
        Queue<Integer> sumQueue = new LinkedList();
        queue.offer(root);
        sumQueue.offer(root.val);
        while (!queue.isEmpty()) {
            // 当前一层的节点数
            int size = queue.size();
            while (size > 0) {
                // 当前节点
                TreeNode curNode = queue.poll();
                // 从root到当前节点的路径和
                int sum = sumQueue.poll();
                // 如果当前节点是叶子节点
                if (curNode.left == null && curNode.right == null) {
                    if (sum == targetSum) {
                        return true;
                    }
                }
                // 不是叶子节点，则继续计算自己的左右节点
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                    sumQueue.offer(sum + curNode.left.val);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                    sumQueue.offer(sum + curNode.right.val);
                }
                size--;
            }

        }
        return false;
    }

    // 递归优化
    public boolean hasPathSum2(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.right == null && root.left == null) {
            return targetSum == root.val;
        }
        return hasPathSum2(root.left, targetSum - root.val) || hasPathSum2(root.right, targetSum - root.val);
    }

    // DFS 递归实现
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        return hasPathSum(root, 0, targetSum);
    }

    public boolean hasPathSum(TreeNode parent, int curSum, int targetSum) {
        if (parent == null) {
            return targetSum == curSum;
        }
        curSum += parent.val;
        if (parent.left == null && parent.right == null) {
            return curSum == targetSum;
        }
        boolean lF = parent.left != null && hasPathSum(parent.left, curSum, targetSum);
        boolean rF = parent.right != null && hasPathSum(parent.right, curSum, targetSum);
        return lF || rF;
    }

}
