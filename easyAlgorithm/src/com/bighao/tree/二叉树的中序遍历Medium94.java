package com.bighao.tree;

import javax.swing.tree.TreeNode;
import java.util.*;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/2/15 19:31
 *
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 *
 * 1.递归
 * 递归方法最为直观易懂，但考虑到效率，我们通常不推荐使用递归，而且容易栈溢出
 *
 * 2.迭代（栈）
 * “操作系统/虚拟机自动帮我们用栈来保存了每个调用的函数，现在我们需要自己模拟这样的调用过程"
 * 栈迭代方法虽然提高了效率，但其嵌套循环却非常烧脑，不易理解，容易造成“一看就懂，一写就废”的窘况。
 * 而且对于不同的遍历顺序（前序、中序、后序），循环结构差异很大，更增加了记忆负担。
 *
 * 3.Morris莫里斯遍历算法,它能将非递归的中序遍历空间复杂度降为 O(1)
 * 莫里斯遍历的优点是没有使用任何辅助空间。
 * 缺点是改变了整个树的结构，强行把一棵二叉树改成一段链表结构。遍历完后还需要恢复树结构
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/dong-hua-yan-shi-94-er-cha-shu-de-zhong-xu-bian-li/
 */
public class 二叉树的中序遍历Medium94 {


    public static List<Integer> inorderTraversal3(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        TreeNode predecessor;
        /**
         * x无左孩子
         *   x加入结果
         *   x = x.right
         * x有左孩子，找predecessor( 找到 xx 左子树上最右的节点（即左子树中序遍历的最后一个节点，xx 在中序遍历中的前驱节点） )
         *   predecessor右孩子为空，右孩子指向x，x = x.left
         *   predecessor右孩子不为空, x加入结果，x = x.right
         */
        while (root != null) {
            if (root.left != null) {
                // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }
                // 让 predecessor 的右指针指向 root，继续遍历左子树
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                }
                // 说明左子树已经访问完了，我们需要断开链接
                else {
                    list.add(root.val);
                    predecessor.right = null;
                    root = root.right;
                }
            }
            // 如果没有左孩子，则直接访问右孩子
            else {
                list.add(root.val);
                root = root.right;
            }
        }
        return list;
    }

    /**
     * 基于栈的迭代
     */
    public static List<Integer> inorderTraversal2(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            // 一直找到最左的叶子节点
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            // 走到这里说明，root为null，此时已经走到最左了，然后弹出当前栈顶并打印
            root = stack.pop();
            list.add(root.val);
            // 开始遍历右子树..
            root = root.right;
        }
        return list;
    }


    /**
     * 递归
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        inorderTraversal(root, list);
        return list;
    }

    public static void inorderTraversal(TreeNode root, List list) {
        if (root.left != null) {
            inorderTraversal(root.left, list);
        }
        list.add(root.val);
        if (root.right != null) {
            inorderTraversal(root.right, list);
        }
    }

    // 递归
    public static void inorderTraversal2(TreeNode parent, List list) {
        if (parent == null) {
            return;
        }

        inorderTraversal(parent.left, list);
        list.add(parent.val);
        inorderTraversal(parent.right, list);
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(3);
        TreeNode n3 = new TreeNode(4);
        TreeNode n14 = new TreeNode(5);
        System.out.println(Arrays.toString(inorderTraversal(root).toArray()));
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
