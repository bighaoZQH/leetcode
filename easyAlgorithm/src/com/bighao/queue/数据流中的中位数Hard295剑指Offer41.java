package com.bighao.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/8/29 2:02
 *
 * 1.简单排序
 * 2.插入排序
 *
 * 将数组分成两组，两边要自平衡，所以可以想到用自平衡的树来做
 *  3.通过优先队列(二叉堆)
 *  4.红黑树
 */
public class 数据流中的中位数Hard295剑指Offer41 {
    /**
     * 插入排序
     */
    List<Integer> data;

    /** initialize your data structure here. */
    public 数据流中的中位数Hard295剑指Offer41() {
        data = new ArrayList();
    }

    public void addNum(int num) {
        if (data.isEmpty()) {
            data.add(num);
        } else {
            int i = 0;
            for (; i < data.size(); i++) {
                if (num <= data.get(i)) {
                    break;
                }
            }
            data.add(i, num);
        }
    }

    public double findMedian() {
        if (data.isEmpty()) {
            return 0;
        }
        int mid = data.size() / 2;
        return data.size() % 2 == 0 ? (data.get(mid - 1) + data.get(mid)) / 2.0 : data.get(mid);
    }
}


/**
 * 二叉堆
 */
class 数据流中的中位数Hard295剑指Offer41_2 {

    // 存放数组排序后右半边较大的数
    PriorityQueue<Integer> minHeap;
    // 存放数组排序后左半边较小的数
    PriorityQueue<Integer> maxHeap;

    /** initialize your data structure here. */
    public 数据流中的中位数Hard295剑指Offer41_2() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
    }

    public void addNum(int num) {
        if (maxHeap.isEmpty()) {
            maxHeap.offer(num);
            return;
        }

        // 入队
        if (num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }

        // 如果为偶数个，则保持两边数量相等，但如果是基数个，则保持maxHeap或者minHeap为多一个
        // 我这里选择的是基数时maxHeap比minHeap为多一个
        int n = maxHeap.size() - minHeap.size();
        if (n > 1) {
            // max比min多超过1个时，把max出队入min
            minHeap.offer(maxHeap.remove());
        } else if (n < 0) {
            // min只要比max多，min就出队入max
            maxHeap.offer(minHeap.remove());
        }
    }

    public double findMedian() {
        if (maxHeap.isEmpty()) {
            return 0;
        }
        // 因为add时保证了基数时，max一定比min多，因此基数时直接返回max的头就行
        return maxHeap.size() == minHeap.size() ? (maxHeap.peek() + minHeap.peek()) / 2.0 : maxHeap.peek();
    }

}