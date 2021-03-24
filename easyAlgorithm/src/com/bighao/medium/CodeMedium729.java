package com.bighao.medium;

/**
 * @Author: bighao周启豪
 * @Date: 2020/4/18 14:32
 * @Version 1.0
 */
public class CodeMedium729 {
    public static void main(String[] args) {
        MyCalendar c = new MyCalendar();
        System.out.println(c.book(10, 20));
        System.out.println(c.book(21, 25));
        System.out.println(c.book(20, 30));
    }
}


class MyCalendar {
    Node root;

    public MyCalendar() {}

    public boolean book(int start, int end) {
        if (start > end) {
            return false;
        }
        if (root == null) {
            root = new Node(start, end);
            return true;
        }
        return checkNode(start, end, root);
    }

    public boolean checkNode(int start, int end, Node root) {
        if (start == root.start || end == root.end) {
            return false;
        }

        // 如果当前start < 根start
        if (start < root.start) {
            // 如果当前end > 跟start说明冲突了
            if (end > root.start) {
                return false;
            }
            // 如果没有左子树，直接赋值，返回true
            if (root.left == null) {
                root.left = new Node(start, end);
                return true;
            }
            // 否则替换root节点后，递归查找
            root = root.left;
            return checkNode(start, end, root);
        }

        if (start > root.start) {
            if (start < root.end) {
                return false;
            }
            if (root.right == null) {
                root.right = new Node(start, end);
                return true;
            }
            root = root.right;
            return checkNode(start, end, root);
        }
        return false;
    }
}

/**
 * 每个节点start开始和end，根据start来判断往右边还是左边查找
 */
class Node {
    int start;
    int end;
    Node right;
    Node left;

    Node(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
