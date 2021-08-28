package com.bighao.queue;

import java.util.PriorityQueue;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/8/22 23:30
 */
public class 数据流中的第K大元素Easy703 {

    private PriorityQueue<Integer> minHeap;
    int k;

    public 数据流中的第K大元素Easy703(int k, int[] nums) {
        minHeap = new PriorityQueue<>(k);
        this.k = k;
        for (int n : nums) {
            add(n);
        }
    }

    // 官方的
    public int add2(int val) {
        minHeap.add(val);
        if (minHeap.size() > k) {
            minHeap.remove();
        }
        return minHeap.peek();
    }

    // 自己写的 速度更快，内存更小
    public int add(int val) {
        if (minHeap.size() < k) {
            minHeap.add(val);
        } else if (val > minHeap.peek()) {
            minHeap.remove();
            minHeap.add(val);
        }
        return minHeap.peek();
    }

}
