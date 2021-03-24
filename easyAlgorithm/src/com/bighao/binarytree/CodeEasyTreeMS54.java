package com.bighao.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: bighao周启豪
 * @Date: 2020/4/19 19:37
 * @Version 1.0
 */
public class CodeEasyTreeMS54 {
    public static void main(String[] args) {
        Integer[] arr = {5, 3, 6, 2, 4, null, null, 1};
        TreeNode root = createBinaryTreeByArr(arr, 0);
        /*Integer[] infix = infix(root, new ArrayList<Integer>());
        System.out.println(Arrays.toString(infix));*/
        System.out.println(kthLargest(root, 3));
    }


    public static int kthLargest(TreeNode root, int k) {
        if (k < 0) {
            return -1;
        }

        List<Integer> infix = infix(root, new ArrayList<Integer>());
        return k > infix.size() ? -1 : infix.get(infix.size() - k);
    }


    // 中序
    public static List<Integer> infix(TreeNode root, List<Integer> list) {
        if (root == null) {
            return null;
        }

        infix(root.left, list);
        list.add(root.val);
        infix(root.right, list);
        return list;
    }

    // 数组顺序存储转二叉树
    public static TreeNode createBinaryTreeByArr(Integer[] arr, int i) {
        if (i >= arr.length || arr[i] == null) {
            return null;
        }

        TreeNode root = new TreeNode(arr[i]);
        root.left = createBinaryTreeByArr(arr, 2 * i + 1);
        root.right = createBinaryTreeByArr(arr, 2 * i + 2);
        return root;
    }


    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }



    /** 第二种解法 */
    int count = 0;
    int result = 0;

    public int kthLargest2(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        middleOrder(root, k);
        return result;
    }

    //二叉搜索树的中序遍历倒序为递减序列
    public void middleOrder(TreeNode node, int k) {
        if (node != null) {
            middleOrder(node.right, k);
            count++;
            if (count == k) {
                result = node.val;
                return;
            }
            middleOrder(node.left, k);
        }
    }
}
