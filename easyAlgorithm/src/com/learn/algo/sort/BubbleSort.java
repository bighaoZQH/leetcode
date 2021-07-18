package com.learn.algo.sort;

import java.util.Arrays;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/7/18 18:25
 *
 * 冒泡排序
 * 时间复杂度: O(n^2)
 * 空间复杂度: O(1)
 */
public class BubbleSort {


    /**
     * 两层循环，外层控制轮数，里层控制比较次数
     * 假设轮数从1开始，总共需要比较data.length - 1轮，每伦比较data.length - 轮数次
     * 如果改伦比较没有交换过，则直接break，后续也不用再交换了
     *
     * @param data
     */
    public void sort(int[] data) {
        if (data == null || data.length <= 1) {
            return;
        }
        // 控制冒泡轮数
        for (int i = 1; i < data.length; i++) {
            boolean hasSwap = false;
            // 每轮比较多少次(data.length - 轮数， 如果轮数从0开始，则还要减一)
            for (int j = 0; j < data.length - i; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                    hasSwap = true;
                }
            }
            // 如果该伦没有交换，则退出循环
            if (!hasSwap) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{15, 16156, 165165184, 61, 561, 651, 654, 984, 684, 65, 165, 1865, 416, 1, 651, 651, 65, -489};
        new BubbleSort().sort(data);
        System.out.println(Arrays.toString(data));
    }

}
