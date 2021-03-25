package com.bighao.tree;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/
 */
public class N叉树的后序遍历Easy590 {

    /**
     * 迭代
     * 2 6 14 11 7 3 12 8 4 13 9 10 5 1
     */
    public static List<Integer> postorder2(Node root) {
        LinkedList<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Deque<Node> stack = new LinkedList<>();
        stack.add(root);
        /**
         * 1.将栈顶取出来为当前节点，将当前节点添加到结果集的第一个位置，
         * 2.然后将遍历 当前节点 的 子节点 并加入到栈中
         * 3.循环重复上面的步骤直到栈为空
         */
        while (!stack.isEmpty()) {
            // 将栈顶弹出
            Node node = stack.pop();
            // 添加到结果集的首部
            result.addFirst(node.val);
            if (node.children != null) {
                for (Node child : node.children) {
                    stack.push(child);
                }
            }
        }
        return result;
    }


    /**
     * 递归方式
     * 从root开始，如果有子节点，就递归下去，如果没有子节点就加入到结果集，回到上一层
     */
    public static List<Integer> postorder(Node root) {
        List<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        postorder(root, result);
        return result;
    }

    public static void postorder(Node root, List<Integer> result) {
        List<Node> children = root.children;
        if (children != null) {
            for (Node child : children) {
                postorder(child, result);
            }
        }
        result.add(root.val);
    }

    public static void main(String[] args) {
        Node t1 = new Node(1);
        Node t2 = new Node(2);
        Node t3 = new Node(3);
        Node t4 = new Node(4);
        Node t5 = new Node(5);
        Node t6 = new Node(6);
        Node t7 = new Node(7);
        Node t8 = new Node(8);
        Node t9 = new Node(9);
        Node t10 = new Node(10);
        Node t11 = new Node(11);
        Node t12 = new Node(12);
        Node t13 = new Node(13);
        Node t14 = new Node(14);

        t1.children = Arrays.asList(t2, t3, t4, t5);
        t3.children = Arrays.asList(t6, t7);
        t7.children = Arrays.asList(t11);
        t11.children = Arrays.asList(t14);
        t4.children = Arrays.asList(t8);
        t8.children = Arrays.asList(t12);
        t5.children = Arrays.asList(t9, t10);
        t9.children = Arrays.asList(t13);

        List<Integer> result = postorder2(t1);
        result.forEach(i -> System.out.print(i + " "));
    }


    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

}
