package com.bighao.tree.avl;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/8/13 19:35
 *
 * 二叉搜索树 递归实现
 * 要让泛型E实现可比较，可以通过Comparable接口
 *
 * 四种不平衡的情况
 * LL:          RR:             LR:         RL:
 *      Y        Y               Y           Y
 *     /          \             /             \
 *    X            X           X               X
 *   /              \           \             /
 *  Z                Z           Z            Z
 *
 *  LL:Y的平衡因子 > 1 && X的平衡因子 >= 0  右旋
 *  RR:Y的平衡因子 < -1 && X的平衡因子 <= 0 左旋
 *  LR:Y的平衡因子 > 1 &&  X的平衡因子 < 0  先对X进行左旋转成LL，再对Y进行右旋
 *  RL:Y的平衡因子 < -1 && X的平衡因子 > 0
 */
public class AVLTree<E extends Comparable<E>> {

    private class TreeNode {
        E data;
        TreeNode left;
        TreeNode right;
        // 节点高度，因为新创建的节点都是叶子节点，所以默认为1
        int height = 1;

        public TreeNode(E data) {
            this.data = data;
        }
    }

    private TreeNode root;
    private int size;

    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /** 得到节点高度 */
    private int getHeight(TreeNode node) {
        return node == null ? 0 : node.height;
    }

    /**
     * 右旋转
     * 对Y节点进行右旋转，返回旋转后的新节点X，并更新x和y的高度（z不需要，因为z没动）
     * 右旋转的条件：
     * 1.节点的平衡因子大于1
     * 2.并且节点的左节点的平衡因子大于等于0
     *          Y                   X
     *         / \      右旋转      / \
     *        x   t4    ===>     Z     Y
     *       / \                / \   / \
     *      Z   t3             t1 t2 t3 t4
     *     / \
     *    t1  t2
     */
    private TreeNode rightRatate(TreeNode y) {
        TreeNode x = y.left;
        TreeNode t3 = x.right;

        // 右旋转
        x.right = y;
        y.left = t3;
        // 调整完后，更新x和y的高度
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        // 返回调整后的根节点
        return x;
    }


    /**
     * 左旋转
     *
     *      y                       X
     *     / \                    /   \
     *   T4   X         左旋转    Y      Z
     *       / \        ====>   / \    / \
     *      T3  Z               T4 T3 T1 T2
     *         / \
     *        T1  T2
     */
    private TreeNode leftRatate(TreeNode y) {
        TreeNode x = y.right;
        TreeNode t3 = x.left;

        // 左旋转
        x.left = y;
        y.right = t3;

        // 调整完后，更新x和y的高度
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        // 返回调整后的根节点
        return x;
    }

    /** 得到平衡因子 */
    private int getBalanceFactor(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 平衡因子等于左右子节点的高度差
        return getHeight(node.left) - getHeight(node.right);
    }

    /** 判断是不是二叉查找树 */
    public boolean isBST() {
        // 得到中序遍历的结果
        List<E> res = inOrder(root);
        if (res.size() <= 1) {
            return true;
        }

        // 判断结果列表是否是增序的
        for (int i = 1; i < res.size(); i++) {
            if (res.get(i).compareTo(res.get(i - 1)) < 0) {
                return false;
            }
        }
        return true;
    }


    /** 判断是否是平衡树 */
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(TreeNode node) {
        if (node == null) {
            return true;
        }
        // 得到平衡因子
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }


    /** 中序遍历 递归 */
    public List<E> inOrder(TreeNode node) {
        List<E> list = new ArrayList<>();
        return inOrder(root, list);
    }

    public List<E> inOrder(TreeNode node, List<E> list) {
        if (node == null) {
            return list;
        }
        inOrder(node.left, list);
        list.add(node.data);
        inOrder(node.right, list);
        return list;
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

        node.height = Math.max(getHeight(node.right), getHeight(node.right)) + 1;

        int balanceFactor = getBalanceFactor(node);
        // LL 处理左边不平衡，进行右旋转
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            //
            return rightRatate(node);
        }

        // RR 处理右边不平衡，进行左旋转
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return rightRatate(node);
        }

        // LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            // 先将node.left左旋，转成LL
            node.left = leftRatate(node.left);
            // 对node进行右旋
            return rightRatate(node);
        }

        // RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            // 先将node.right进行右旋，转成RR
            node.right = rightRatate(node.right);
            // 对node进行左旋
            return leftRatate(node);
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
