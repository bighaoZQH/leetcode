package com.learn.algo.sort;

import java.util.Arrays;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/7/25 17:22
 */
public class ThreeWayQuickSort {

    /**
     * 三路快排，对原来的快速排序进行优化
     */
    public void sort(int[] data) {
        if (data == null || data.length < 2) {
            return;
        }
        sort(data, 0, data.length - 1);
    }

    private void sort(int[] data, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        // 进行分区
        int pivot = data[hi];
        int less = lo;
        int great = hi;
        int i = lo;
        while (i <= great) {
            if (data[i] < pivot) {
                int temp = data[i];
                data[less] = data[i];
                data[i] = temp;
                less++;
                i++;
            } else if (data[i] > pivot) {
                int temp = data[i];
                data[great] = data[i];
                data[i] = temp;
                great--;
            } else {
                // 这个分区是多出来的
                i++;
            }
        }

        sort(data, lo, less - 1);
        sort(data, great + 1, hi);
    }


    public static void main(String[] args) {
        int[] data = new int[]{15, 16156, 165165184, 61, 561, 651, 654, 984, 684, 65, 165, 1865, 416, 1, 651, 651, 65, -489};
        new QuickSort().sort(data);
        System.out.println(Arrays.toString(data));
    }

}
