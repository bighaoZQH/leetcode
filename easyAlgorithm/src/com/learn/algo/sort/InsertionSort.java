package com.learn.algo.sort;

import java.util.Arrays;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/7/18 23:09
 *
 * 插入排序
 * 将后面乱序的元素，插入到前面有序的元素中
 *
 * 时间复杂度: O(n^2)
 * 空间复杂度: O(1)
 */
public class InsertionSort {


    /**
     * 对sort的优化。。
     * 不进行交换，而是将大的后移，将data[j]的记录到一个临时变量temp里，最后将temp更新到合适的位置
     */
    public void sort(int[] data) {
        if (data == null || data.length == 0) {
            return;
        }

        // 外层控制轮数 data.length - 1轮
        // 直接从下标1开始
        for (int i = 1; i < data.length; i++) {
            // 将i插入到前面[0 - i-1)的元素中
            // 从i开始往前遍历
            int j = i;
            // 记录下当前data[j]的值
            int temp = data[j];
            for (; j > 0; j--) {
                if (temp < data[j - 1]) {
                    // 如果temp小于data[j - 1]，则将tdata[j - 1]后移
                    data[j] = data[j - 1];
                } else {
                    // 因为前面都是排序好的，>=前面一个就不用再往前比较了，直接break
                    break;
                }
            }
            // 最后将data[j]设置为temp
            data[j] = temp;
        }
    }

    public void sort1(int[] data) {
        if (data == null || data.length == 0) {
            return;
        }

        // 外层控制轮数 data.length - 1轮
        // 直接从下标1开始
        for (int i = 1; i < data.length; i++) {
            // 将i插入到前面[0 - i-1)的元素中
            // 从i开始往前遍历，进行data[j] 和 data[j - 1]两两判断，交换
            for (int j = i; j > 0; j--) {
                if (data[j] < data[j - 1]) {
                    int temp = data[j];
                    data[j] = data[j - 1];
                    data[j - 1] = temp;
                } else {
                    // 因为前面都是排序好的，>=前面一个就不用再往前比较了，直接break
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{15, 16156, 165165184, 61, 561, 651, 654, 984, 684, 65, 165, 1865, 416, 1, 651, 651, 65, -489};
        new InsertionSort().sort(data);
        System.out.println(Arrays.toString(data));
    }

}
