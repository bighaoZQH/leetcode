package com.bighao.tree;

import com.bighao.tree.btree.TreeNode;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/8/12 23:14
 */
public class 相同的树Easy100 {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

}
