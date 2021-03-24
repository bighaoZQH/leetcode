package com.bighao.binarytree;


/**
 * @Author: bighao周启豪
 * @Date: 2020/4/16 19:47
 * @Version 1.0
 * <p>
 * 235题 找最近公共父节点
 */

public class CodeEasyTree235 {


    /**
     * 思路：
     * 1.如果两个节点分别在根节点的左右，那么最近公共祖先就是根节点
     * 2.如果不是，就说明两个节点都在根节点的左子树或右子树，将根节点替换后，进行递归
     * 3.如果两个节点有一个是根节点，那么最近公共祖先就是根节点
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if ((p == root || q == root) ||
                (p.val < root.val && q.val > root.val) ||
                (p.val > root.val && q.val < root.val)) {
            return root;
        }
        root = p.val < root.val ? root.left : root.right;
        return lowestCommonAncestor(root, p, q);
    }


    // 第二种，一样的思路，不一样的写法，更简单
    public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor2(root.left, p, q);
        }
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor2(root.right, p, q);
        }
        return root;
    }


    // 数组转二叉树，按前序遍历的顺序
    public static TreeNode createBinaryTreeByArray(Integer[] array, int i) {
        if (i >= array.length || array[i] == null) {
            return null;
        }
        TreeNode root = new TreeNode(array[i]);
        root.left = createBinaryTreeByArray(array, 2 * i + 1);
        root.right = createBinaryTreeByArray(array, 2 * i + 2);
        return root;
    }

    public static void main(String[] args) {
        //Integer[] node = {6, 2, 8, 0, 4, 7, 9, null, null, 3, 5};
        Integer[] node = {5, 3, 6, 1, 4, null, null, null, 2};
        TreeNode root = createBinaryTreeByArray(node, 0);

        TreeNode treeNode = lowestCommonAncestor2(root, root.left.right, root.left.left.right);
        System.out.println(treeNode.val);
    }


    static class TreeNode {
        TreeNode(int x) {
            val = x;
        }
        int val;
        TreeNode left;
        TreeNode right;
    }

}

