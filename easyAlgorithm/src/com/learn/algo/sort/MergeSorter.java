package com.learn.algo.sort;

import java.util.Arrays;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/7/24 18:32
 */
public class MergeSorter {

    public void sort(int[] data) {
        if (data == null || data.length < 2) {
            return;
        }
        int[] temp = new int[data.length];
        sort(data, 0, data.length - 1, temp);
    }

    public void sort(int[] data, int left, int right, int[] temp) {
        // 递归终止条件
        if (left == right) {
            return;
        }
        // 分割数组进行递归，即分别对两个子问题进行求解
        int mid = (left + right) / 2;
        sort(data, left, mid, temp);
        sort(data, mid + 1, right, temp);
        // 合并
        merge(data, left, mid, right, temp);
    }

    private void merge(int[] data, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int tmpPos = left;

        while (i <= mid && j <= right) {
            if (data[i] <= data[j]) {
                temp[tmpPos++] = data[i++];
            } else {
                temp[tmpPos++] = data[j++];
            }
        }

        // 如果左边还有剩余的，都填到temp中
        while (i <= mid) {
            temp[tmpPos++] = data[i++];
        }
        // 如果右边还有剩余的，都填到temp中
        while (j <= right) {
            temp[tmpPos++] = data[j++];
        }

        // 从temp拷回data中
        // 从temp的left开始一直到right 拷贝回原数组
        while (left <= right) {
            data[left] = temp[left];
            left++;
        }
    }


    /**
     * 自底向上
     */
    public void sortBU(int[] data) {
        if (data == null || data.length < 2) {
            return;
        }
        int len = data.length;
        int[] temp = new int[len];
        for (int size = 1; size < len; size += size) {
            for (int left = 0; left < len - size; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min(left + 2 * size - 1, len - 1);
                merge(data, left, mid, right, temp);
            }
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{15, 16156, 165165184, 61, 561, 651, 654, 984, 684, 65, 165, 1865, 416, 1, 651, 651, 65, -489};
        new MergeSorter().sort(data);
        System.out.println(Arrays.toString(data));
    }

}
