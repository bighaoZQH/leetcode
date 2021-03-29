package com.bighao.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/
 */
public class N叉树的层序遍历Medium429 {

    public List<List<Integer>> levelOrderIteration(Node root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }

        Deque<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            LinkedList<Integer> list = new LinkedList<>();
            // 当前层级的个数
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.pop();
                list.add(node.val);
                queue.addAll(node.children);
            }
            result.add(list);
        }
        return result;
    }


    public static void main(String[] args) {

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
