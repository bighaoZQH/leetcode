package com.learn.algo.sort;

import java.util.Arrays;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/7/25 0:20
 */
public class QuickSort {

    public void sort(int[] data) {
        if (data == null || data.length < 2) {
            return;
        }
        sort(data, 0, data.length - 1);
    }

    // 子问题
    private void sort(int[] data, int lo, int hi) {
        // 递归终止条件
        if (lo > hi) {
            return;
        }
        // 进行分区
        int j = partition(data, lo, hi);
        // 对左边数组进行排序
        sort(data, lo, j - 1);
        // 对右边数组进行排序
        sort(data, j + 1, hi);
    }

    private int partition(int[] data, int lo, int hi) {
        int pivot = data[hi];
        int great = lo;
        int less = lo;
        for (; great < hi; great++) {
            // 如果great比pivot小，则great和less进行交换
            if (data[great] < pivot) {
                int temp = data[less];
                data[less] = data[great];
                data[great] = temp;
                less++;
            }
        }
        // 将less和hi进行交换
        data[hi] = data[less];
        data[less] = pivot;
        return less;
    }

    public static void main(String[] args) {
        int[] data = new int[]{15, 16156, 165165184, 61, 561, 651, 654, 984, 684, 65, 165, 1865, 416, 1, 651, 651, 65, -489};
        new QuickSort().sort(data);
        System.out.println(Arrays.toString(data));
    }
}
