package com.learn.algo.sort;

import java.util.Arrays;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/7/18 22:36
 *
 * 选择排序
 * 时间复杂度: O(n^2)
 * 空间复杂度: O(1)
 */
public class SelectionSort {

    public void sort(int[] data) {
        if (data == null || data.length == 0) {
            return;
        }

        // 外层循环控制选择排序的轮数
        for (int i = 0; i < data.length - 1; i++) {
            int minIdx = i;
            // 找到i后面比i小的值的下标
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] < data[minIdx]) {
                    minIdx = j;
                }
            }
            // 然后进行交换
            int temp = data[i];
            data[i] = data[minIdx];
            data[minIdx] = temp;
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{15, 16156, 165165184, 61, 561, 651, 654, 984, 684, 65, 165, 1865, 416, 1, 651, 651, 65, -489};
        new SelectionSort().sort(data);
        System.out.println(Arrays.toString(data));
    }

}
