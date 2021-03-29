package com.bighao.tree;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/
 * <p>
 * 1.递归
 * 2.迭代
 */
public class N叉树的前序遍历Easy589 {


    public static List<Integer> preorderIteration(Node root) {
        List<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Deque<Node> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            result.add(root.val);
            if (root.children != null) {
                // 将子节点翻转后放入stack中
                for (int i = root.children.size() - 1; i >= 0; i--) {
                    stack.push(root.children.get(i));
                }
            }
        }
        return result;
    }


    /**
     * 递归
     */
    public List<Integer> preorderRecursion(Node root) {
        List<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        preorderRecursion(root, result);
        return result;
    }

    public void preorderRecursion(Node root, List<Integer> result) {
        result.add(root.val);
        if (root.children != null) {
            for (Node child : root.children) {
                preorderRecursion(child, result);
            }
        }
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

        List<Integer> result = preorderIteration(t1);
        result.forEach(i -> System.out.print(i + " "));
    }

    static class Node {
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
