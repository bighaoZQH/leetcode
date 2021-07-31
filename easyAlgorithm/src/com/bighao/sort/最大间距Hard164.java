package com.bighao.sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/7/28 23:30
 *
 * https://leetcode-cn.com/problems/maximum-gap/
 */
public class 最大间距Hard164 {

    // 桶排序
    public int maximumGapByBucketSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        // 得到最大值和最小值
        /// int minVal = Arrays.stream(nums).min().getAsInt();
        /// int maxVal = Arrays.stream(nums).max().getAsInt();
        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }


        // 计算gap(最大间距不会小于gap)
        int gap = Math.max(1, (max - min) / (nums.length - 1));
        int bucketSize = (max - min) / gap + 1;

        // 初始化桶。每个桶里只要放最大值和最小值
        int[][] bucket = new int[bucketSize][2];
        for (int i = 0; i < bucketSize; i++) {
            // 初始化填充 (桶内最小值，桶内最大值)， (-1, -1) 表示该桶是空的。当然这样就没有处理负数了
            Arrays.fill(bucket[i], -1);
        }

        for (int i = 0; i < nums.length; i++) {
            // 计算当前值应该放到哪个桶里
            int idx = (nums[i] - min) / gap;
            // 空桶的话 直接塞进去就行了
            if (bucket[idx][0] == -1) {
                bucket[idx][0] = nums[i];
                bucket[idx][1] = nums[i];
            } else {
                // 更新桶里的最大值和最下值
                bucket[idx][0] = Math.min(bucket[idx][0], nums[i]);
                bucket[idx][1] = Math.max(bucket[idx][1], nums[i]);
            }
        }

        int maxGap = 0;
        int prev = bucket[0][1];
        // 遍历桶，得到最大间距
        for (int i = 1; i < bucketSize; i++) {
            // 不需要计算，跳过该桶
            if (bucket[i][0]== -1) {
                continue;
            }
            // 当前桶的最小值 - 上一个桶的最大值
            int curGap = bucket[i][0] - prev;
            maxGap = Math.max(maxGap, curGap);
            // 将prev更新成当前桶的最大值
            prev = bucket[i][1];
        }
        return maxGap;
    }

    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int max = 0;
        // Arrays.sort(nums)比手写快排快。。。。。。。。。。。
        // 还有特么的leetcode上的提交记录2ms、3ms的都是这样写的，
        // 但是现在提交的话，都是40多ms，坑爹啊

        /// quickSort(nums, 0, nums.length - 1);
        /// Arrays.sort(nums);

        // 因为数组里的数可能会比较大，因此计数排序不合适
        // 因此线性的排序 还剩下基数排序和桶排序
        // 这题应该主要考桶排序

        radixSort(nums);

        // 排序好后遍历原数组 求得最大间距
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i] - nums[i - 1]);
        }
        return max;
    }

    // 基数排序
    private void radixSort(int[] nums) {
        // 1.求出最大位数
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
        }

        // 2.按每一位进行排序
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(nums, exp);
        }
    }

    private void countSort(int[] nums, int exp) {
        // 初始化计数数组
        int[] countArr = new int[10];

        // 根据exp求得具体的位数，进行计数
        for (int i = 0; i < nums.length; i++) {
            int diget = (nums[i] / exp) % 10;
            countArr[diget]++;
        }
        // 进行计数累加
        for (int i = 1; i < countArr.length; i++) {
            countArr[i] += countArr[i - 1];
        }

        int[] outputArr = new int[nums.length];
        // 从右往左排序到outputArr中
        for (int i = nums.length - 1; i >= 0; i--) {
            int diget = (nums[i] / exp) % 10;
            int idx = countArr[diget] - 1;
            outputArr[idx] = nums[i];
            countArr[diget]--;
        }

        // 把排序好的值复制回原数组中
        for (int i = 0; i <nums.length; i++) {
            nums[i] = outputArr[i];
        }
    }

        // 快排
    private void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int less = start;
        int great = end;
        int idx = start;
        int pivot = nums[end];

        while (idx <= great) {
            if (nums[idx] < pivot) {
                int temp = nums[less];
                nums[less] = nums[idx];
                nums[idx] = temp;
                less++;
                idx++;
            } else if (nums[idx] > pivot) {
                int temp = nums[great];
                nums[great] = nums[idx];
                nums[idx] = temp;
                great--;
            } else {
                idx++;
            }
        }

        quickSort(nums, start, less - 1);
        quickSort(nums, great + 1, end);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{23, 23, 4, 34, 32, 432, 1, 432, 4, 32432, 232332, 54, 3, 655, 765, 7, 67, 6, 75, 321213123, 756, 7};
        new 最大间距Hard164().maximumGap(arr);
    }

}
