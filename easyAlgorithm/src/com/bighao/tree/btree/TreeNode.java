package com.bighao.tree.btree;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/8/8 19:49
 */
public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

}
