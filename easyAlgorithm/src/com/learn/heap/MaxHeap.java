package com.learn.heap;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/8/21 19:03
 *
 * 二叉堆 - 大顶堆
 * 1.是一颗完全二叉树(倒数第二层是满的，最后一层的最后一个节点必须是左节点)
 * 2.堆中每个节点的值总是不大于其父亲节点的值
 *
 * 可以链式存储
 * 也可以数组存储
 *      根节点可以存储在数组的第一个元素 leftChild = index * 2 + 1
 *                                  rightChild = index * 2 + 2
 *                                  parent = (index - 1) / 2
 *      也可以存储在数组第二个元素上，这样计算表达式可以变简单
 * 可用于实现优先队列
 *
 * 大顶堆特点：arr[i]-parent >= arr[2*i+1]-left && arr[i] >= arr[2*i+2]-right
 * 小顶堆特点：arr[i]-parent <= arr[2*i+1]-left && arr[i] <= arr[2*i+2]-right
 */
public class MaxHeap<E extends Comparable<E>> {

    private ArrayList<E> data;

    public MaxHeap(int capacity) {
        this.data = new ArrayList<>(capacity);
    }

    public MaxHeap() {
        this.data = new ArrayList<>();
    }

    /**
     * 堆化操作 - 可用于堆排序
     * 如果遍历数组 挨个add的话，时间复杂度是O(nlogN)
     *
     * https://ke.qq.com/webcourse/3098167/103219562#taid=10688721903896119&vid=5285890809837254637
     */
    public MaxHeap(E[] arr) {
        this.data = new ArrayList<>(arr.length);
        // 先把元素组当做一个堆
        Collections.addAll(data, arr);
        // 然后从当前最后一个非叶子节点开始 下浮 再往前下浮
        // 因此此时数组中最后一个非叶子节点的 后边的元素都是叶子节点，我们只需要将该节点和其前面的节点进行下浮就可以完成堆排序
        // O(2/n + 1) ==> O(nlogN) 但是这样要比直接add要更好
        for (int i = lastNonLeafIndex(); i >= 0; i--) {
            siftDown(i); //O(logN)
        }
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    // 返回一个索引表示的节点的左子节点
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    // 返回一个索引表示的节点的右子节点
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    // 返回一个索引表示的节点的父节点
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 dose not have parent");
        }
        return (index - 1) / 2;
    }

    /**
     * 往大顶堆中添加一个元素 O(logN)
     */
    public void add(E e) {
        // 1.先将新元素插入到数组的最后 O(1)
        data.add(data.size(), e);
        // 2.将最后一个节点进行上浮 O(logN)
        siftUp(data.size() - 1);
    }

    /**
     * 上浮
     * 不断和父节点进行比较，比父节点大就交换，小的话就结束上浮
     *
     * 优化后 不需要交换，直接把父节点插入到子节点的位置，然后继续往前找子节点要插入的位置
     */
    private void siftUp(int index) {
        E e = data.get(index);
        // 不断上浮比较，一直到root节点
        while (index > 0) {
            int parentIdx = parent(index);
            E parentNode = data.get(parentIdx);

            // 改成大于等于就是小顶堆
            if (e.compareTo(parentNode) <= 0) {
                break;
            }
            // 进行交换 优化后不需要交换了，直接把父节点插到子节点的位置
            data.set(index, parentNode);
            //data.set(parentIdx, e);

            // 更新idx，继续往前比较
            index = parentIdx;
        }
        // 将新加的节点插入到合适的位置
        data.set(index, e);
    }

    public E findMax() {
        if (data.size() == 0) {
            return null;
        }
        return data.get(0);
    }

    /**
     * 取出并删除最大的元素 O(logN)
     */
    public E removeMax() {
        E max = findMax();
        // 1.将最后一个节点替换根节点 O(1)
        E last = data.get(data.size() - 1);
        data.set(0, last);
        // 2.删除最后一个节点 O(1)
        data.remove(data.size() - 1);
        // 3.将新的根节点做下沉操作 O(logN)
        if (!data.isEmpty()) {
            siftDown(0);
        }
        return max;
    }


    /**
     * 下浮 O(logN)
     */
    private void siftDown(int index) {
        E e = data.get(index);
        // 一直找到没有左子节点时终止循环
        while (leftChild(index) < data.size()) {
            // 找到当前节点的最大子节点
            int maxNodeIndex = leftChild(index);
            int right = rightChild(index);
            // 如果有右子节点
            if (right < data.size()) {
                // 如果右子节点 > 左子节点
                if (data.get(right).compareTo(data.get(maxNodeIndex)) > 0) {
                    maxNodeIndex = right;
                }
            }

            // 已经满足了二叉堆的性质，直接退出循环
            if (e.compareTo(data.get(maxNodeIndex)) >= 0) {
                break;
            }

            // 2.将当前节点和最大子节点的值比较，如果小的话就交换
            data.set(index, data.get(maxNodeIndex));

            // 继续下浮
            index = maxNodeIndex;
        }
        // 将当前节点插入到合适的位置
        data.set(index, e);
    }


    /**
     * 返回最后一个非叶子节点的索引(即最后一个叶子节点的父节点)
     * arr.length / 2 - 1 直接这个公式也可以
     */
    private int lastNonLeafIndex() {
        // 最后一个叶子节点的索引
        int lastLeafIndex = data.size() - 1;
        return parent(lastLeafIndex);
    }

}
