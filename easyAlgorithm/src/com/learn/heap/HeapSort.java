package com.learn.heap;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/8/22 0:23
 */
public class HeapSort {

    /**
     * 时间复杂度 O(nlogN)
     * 空间复杂度 O(n)
     */
    public void sort(Integer[] data) {
        // 1.建堆，堆化
        MaxHeap<Integer> heap = new MaxHeap<>(data);
        // 2.排序
        Integer[] tmp = new Integer[data.length];
        // i=0 升序 i=data.length-1 降序 也可以用小顶堆实现
        int i = 0;
        while (!heap.isEmpty()) {
            tmp[i++] = heap.removeMax();
        }
        // 3.拷贝回原数组
        for (i = 0; i <data.length; i++) {
            data[i] = tmp[i];
        }
    }

}