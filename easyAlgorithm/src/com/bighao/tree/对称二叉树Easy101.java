package com.bighao.tree;

import com.bighao.tree.btree.TreeNode;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/8/12 23:18
 */
public class 对称二叉树Easy101 {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return false;
        }
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

}
