package com.bighao.tree.bts;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/8/13 19:35
 *
 * 二叉搜索树 递归实现
 * 要让泛型E实现可比较，可以通过Comparable接口
 */
public class BTSR<E extends Comparable<E>> {

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

    public BTSR() {
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
        root = add(root, e);
    }

    private TreeNode add(TreeNode node, E e) {
        // node为空返回新节点
        if (node == null) {
            size++;
            return new TreeNode(e);
        }

        // 比node小，往左边递归
        if (e.compareTo(node.data) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.data) > 0) {
            // 往右递归
            node.right = add(node.right, e);
        }
        return node;
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
        return find(root, target);
    }

    public TreeNode find(TreeNode node, E target) {
        if (node == null) {
            return null;
        }
        if (target.compareTo(node.data) == 0) {
            return node;
        }
        if (target.compareTo(node.data) < 0) {
            return find(node.left, target);
        } else {
            return find(node.right, target);
        }
    }


    /**
     * 最小值
     */
    public E minValue() {
        if (root == null) {
            throw new RuntimeException("tree is null");
        }
        return minValue(root).data;
    }

    public TreeNode minValue(TreeNode node) {
        return node.left == null ? node : minValue(node.left);
    }

    /**
     * 最大值
     */
    public E maxValue() {
        if (root == null) {
            throw new RuntimeException("tree is null");
        }
        return maxValue(root).data;
    }

    public TreeNode maxValue(TreeNode node) {
        return node.right == null ? node : maxValue(node.right);
    }

    /**
     * 移除最小值
     */
    public E removeMin() {
        if (root == null) {
            return null;
        }
        return removeMin(root).data;
    }

    public TreeNode removeMin(TreeNode node) {
        if (node.left == null) {
            // 说明node是最小节点，把node的right返回出去，挂到上一层节点的left
            TreeNode right = node.right;
            node.right = null;
            size--;
            return right;
        }
        node.left = removeMin(node.left);
        return node;
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
        root = remove(root, e);
    }

    public TreeNode remove(TreeNode node, E e) {
        if (node == null) {
            return null;
        }

        if (e.compareTo(node.data) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.data) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {
            // 要删除的节点就是node
            if (node.left == null) {
                TreeNode right = node.right;
                node.right = null;
                size--;
                return right;
            }

            if (node.right == null) {
                TreeNode left = node.left;
                node.left = null;
                size--;
                return left;
            }

            // node的 left 和 right 都不为空
            TreeNode successor = minValue(node.right);
            successor.left = node.right;
            successor.right = removeMin(node.right);

            node.left = null;
            node.right = null;
            size--;
            return successor;
        }

    }

}
