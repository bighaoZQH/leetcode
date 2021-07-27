package com.learn.algo.sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/7/19 23:05
 */
public class ShellSort {


    /**
     * 优化sort1的递增序列
     */
    public void sort(int[] data) {
        if (data == null || data.length < 2) {
            return;
        }

        // 1.计算递增序列
        // 1, 4, 13, 40, 121...
        int h = 1;
        while (h < data.length / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            // 将数组变为h有序 以下代码就是插入排序演化来的
            for (int i = h; i < data.length; i++) {
                // 用一个临时变量来记录当前最小值，直接将大的值往后挪动来代替交换
                int j = i;
                int temp = data[j];
                for (; j >= h; j -= h) {
                    if (temp < data[j - h]) {
                        data[j] = data[j - h];
                    } else {
                        break;
                    }
                }
                // 将本次区间排序的值放到合适的位置
                data[j] = temp;
            }
            h = h / 3;
        }
    }

    public void sort1(int[] data) {
        if (data == null || data.length < 2) {
            return;
        }

        // 1.计算递增序列
        // 1, 4, 13, 40, 121...
        ArrayList<Integer> list = new ArrayList<>();
        int k = 1;
        int h;

        do {
            h = ((int) Math.pow(3, k) - 1) / 2;
            if (h > data.length / 3) {
                break;
            }
            list.add(h);
            k++;
        } while (h <= data.length / 3);

        // 进行希尔排序 倒序遍历
        for (k = list.size() - 1; k >= 0; k--) {
            h = list.get(k);
            // 将数组变为h有序 以下代码就是插入排序演化来的
            for (int i = h; i < data.length; i++) {
                // 用一个临时变量来记录当前最小值，直接将大的值往后挪动来代替交换
                int j = i;
                int temp = data[j];
                for (; j >= h; j -= h) {
                    if (temp < data[j - h]) {
                        /*int temp = data[j];
                        data[j] = data[j - h];
                        data[j - h] = temp;*/
                        data[j] = data[j - h];
                    } else {
                        break;
                    }
                }
                // 将本次区间排序的值放到合适的位置
                data[j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{15, 16156, 165165184, 61, 561, 651, 654, 984, 684, 65, 165, 1865, 416, 1, 651, 651, 65, -489};
        new ShellSort().sort(data);
        System.out.println(Arrays.toString(data));
    }

}
