package com.learn.algo.sort;

import java.util.Arrays;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/7/25 22:33
 */
public class RadixSort {

    public void sort(int[] data) {
        if (data == null || data.length < 2) {
            return;
        }

        // 1.找到最大值
        int max = data[0];
        for (int i = 1; i < data.length - 1; i++) {
            max = Math.max(max, data[i]);
        }

        // 2.对数组按照每个元素的每位进行计数排序
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(data, exp);
        }
    }


    private void countSort(int[] data, int exp) {
        // 之所以是10，是因为单个数字只有0~9 十个数字
        int[] count = new int[10];
        for (int i = 0; i < data.length; i++) {
            // 得到个位数
            int digit = (data[i] / exp) % 10;
            count[digit]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        int[] output = new int[data.length];
        for (int i = data.length - 1; i >= 0; i--) {
            int digit = (data[i] / exp) % 10;
            int k = count[digit] - 1;
            output[k] = data[i];
            count[digit]--;
        }

        for (int i = 0; i < data.length ; i++) {
            data[i] = output[i];
        }
    }



    public static void main(String[] args) {
        int[] data = new int[] {4512, 4231, 1923, 2165, 1141};
        new RadixSort().sort(data);
        System.out.println(Arrays.toString(data));
    }

}
