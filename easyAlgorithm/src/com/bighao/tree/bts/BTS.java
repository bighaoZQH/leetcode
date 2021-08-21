package com.bighao.tree.bts;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/8/13 19:35
 *
 * 二叉搜索树
 * 要让泛型E实现可比较，可以通过Comparable接口
 */
public class BTS<E extends Comparable<E>> {

    private class TreeNode {
        E data;
        TreeNode left;
        TreeNode right;

        public TreeNode(E data) {
            this.data = data;
        }
    }

    private TreeNode root;
    private int size;

    public BTS() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * 添加新节点
     */
    public void add(E e) {
        if (root == null) {
            root = new TreeNode(e);
            return;
        }

        TreeNode cur = root;
        while (true) {
            // 相同的不做操作
            if (e.compareTo(cur.data) == 0) {
                return;
            } else if (e.compareTo(cur.data) < 0) {
                // 往左子树找
                if (cur.left == null) {
                    cur.left = new TreeNode(e);
                    size++;
                    return;
                }
                cur = cur.left;
            } else {
                // 往右字数找
                if (cur.right == null) {
                    cur.right = new TreeNode(e);
                    size++;
                    return;
                }
                cur = cur.right;
            }
        }
    }


    /**
     * 查询是否存在某个值
     */
    public boolean contains(E target) {
        if (root == null) {
            return false;
        }
        TreeNode cur = root;
        while (cur != null) {
            if (target.compareTo(cur.data) == 0) {
                return true;
            } else if (target.compareTo(cur.data) < 0) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return false;
    }


    /**
     * 得到某个节点
     */
    public TreeNode find(E target) {
        if (root == null) {
            return null;
        }
        TreeNode cur = root;
        while (cur != null) {
            if (target.compareTo(cur.data) == 0) {
                return cur;
            } else if (target.compareTo(cur.data) < 0) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return null;
    }


    /**
     * 前序遍历
     */
    public List<E> preOrder() {
        List<E> list = new ArrayList();
        if (root == null) {
            return list;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            list.add(cur.data);

            if (cur.right != null) {
                stack.push(cur.right);
            }

            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        return list;
    }


    /**
     * 中序遍历
     */
    public List<E> inOrder() {
        List<E> list = new ArrayList();
        if (root == null) {
            return list;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;

        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            list.add(cur.data);
            cur = cur.right;
        }
        return list;
    }


    /**
     * 后续遍历
     */
    public List<E> postOrder() {
        List<E> list = new ArrayList();
        if (root == null) {
            return list;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        TreeNode prev = null;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            if (cur.right == null || prev == root) {
                list.add(cur.data);
                cur = null;
            } else {
                stack.push(cur);
                prev = cur;
                cur = cur.right;
            }
        }
        return list;
    }


    /**
     * 最小值
     */
    public E minValue() {
        if (root == null) {
            throw new RuntimeException("tree is null");
        }
        TreeNode min = root;
        while (min.left != null) {
            min = min.left;
        }
        return min.data;
    }

    /**
     * 移除最小值
     */
    public E removeMin() {
        if (root == null) {
            throw new RuntimeException("tree is null");
        }
        TreeNode min = root;
        TreeNode parent = null;
        while (min.left != null) {
            parent = min;
            min = min.left;
        }

        if (parent == null) {
            // 说明删除的是root节点
            root = root.right;
        } else {
            // 指向min的子树（min是不会有左子树的）
            parent.left = min.right;
            min.right = null;
        }
        size--;
        return min.data;
    }


    /**
     * 移除最大值
     */
    public E removeMax() {
        if (root == null) {
            throw new RuntimeException("tree is null");
        }
        TreeNode max = root;
        TreeNode parent = null;
        while (max.right != null) {
            parent = max;
            max = max.right;
        }

        if (parent == null) {
            root = root.left;
        } else {
            parent.right = max.left;
            max.left = null;
        }
        size--;
        return max.data;
    }

    /**
     * 移除指定值
     */
    public void remove(E e) {
        if (root == null) {
            return;
        }

        TreeNode cur = root;
        TreeNode parent = null;

        // 找到要删除的节点
        while (cur != null && e.compareTo(cur.data) == 0) {
            parent = cur;
            if (e.compareTo(cur.data) < 0) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }

        // 没有找到则直接返回
        if (cur == null) {
            return;
        }

        /**
         * 找到后分五种情况
         * 1.要删除的节点,是root节点，直接删除
         * 2.要删除的节点,是叶子节点，直接删除
         * 3.要删除的节点左、右子树都不为空，则应该在左、右子树中，找到一个大于左边全部，小于右边全部的节点,用这个节点来替换要删除的节点，来维持二插查找树的特性
         * 4.要删除的节点只有左子树
         * 5.要删除的节点只有右子树
         */
        if (cur.right == null && cur.left == null) {
            if (parent == null) {
                // 删除根节点
                root = null;
            } else if (cur == parent.left) {
                // cur是parent的left
                parent.left = null;
            } else {
                parent.right = null;
            }

        } else if (cur.left != null && cur.right != null) {
            // 左、右子树都不为空
            // 应该在左、右子树中，找到一个大于左边全部，小于右边全部的节点
            // 这个节点，就是右子树的最小节点, 用这个节点来替换要删除的节点，来维持二插查找树的特性
            TreeNode min = cur.right;
            TreeNode minParent = cur;
            while (min.left != null) {
                minParent = min;
                min = min.left;
            }
            // 找到要替换的节点后，进行替换
            cur.data = min.data;
            minParent.left = null;

        } else if (cur.left != null) {
            // 只有左子树
            if (parent == null) {
                root = root.left;
            } else if (cur == parent.left) {
                // 如果当前节点是父亲节点的左子节点
                parent.left = cur.left;
                cur.left = null;
            } else {
                // 如果当前节点是父亲节点的右子节点
                parent.right = cur.left;
                cur.left = null;
            }
        } else {
            // cur.right != null && cur.left == null
            // 只有右子树
            if (parent == null) {
                root = root.right;
            } else if (cur == parent.left) {
                parent.left = cur.right;
                cur.right = null;
            } else {
                parent.right = cur.right;
                cur.right = null;
            }
        }
    }

}
