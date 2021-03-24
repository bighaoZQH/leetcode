package com.bighao.binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: bighao周启豪
 * @Date: 2020/4/19 14:46
 * @Version 1.0
 */
public class CodeEasyTreeMS27 {
    public static void main(String[] args) {
        Integer[] arr = {4,2,7,1,3,6,9};
        Integer[] arr2 = {1, 2};

                //TreeNode root = new TreeNode(arr[0]);
        //TreeNode treeNode = mirrorTree(root);

        TreeNode root = createBinaryTreeByArray(arr2, 0);
        TreeNode treeNode = mirrorTree(root);
        System.out.println(treeNode);
        /*ArrayList<Integer> list = new ArrayList<>();
        list.add(root.val);
        Integer[] pre = pre(root, list);
        System.out.println(Arrays.toString(pre));*/

    }

    // 镜像树 - 递归法（本质是DFS）
    public static TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.left;

        root.left = root.right;
        mirrorTree(root.left);

        root.right = temp;
        mirrorTree(root.right);

        return root;
    }

    // 二叉树转数组顺序存储
    public static Integer[] pre(TreeNode root, List<Integer> list) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            list.add(root.left.val);
        }
        if (root.right != null) {
            list.add(root.right.val);
        }
        pre(root.left, list);
        pre(root.right, list);
        return list.toArray(new Integer[list.size()]);
    }

    // 数组转二叉树
    public static TreeNode createBinaryTreeByArray(Integer[] arr, int i) {
        if (i >= arr.length || arr[i] == null) {
            return null;
        }
        TreeNode root = new TreeNode(arr[i]);
        root.left = createBinaryTreeByArray(arr, i * 2 + 1);
        root.right = createBinaryTreeByArray(arr, i * 2 + 2);
        return root;
    }


    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


}

