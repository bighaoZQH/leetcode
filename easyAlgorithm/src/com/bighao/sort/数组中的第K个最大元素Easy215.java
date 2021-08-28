package com.bighao.sort;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/8/22 2:20
 *
 * 1.快排 每次分区完后 看下分区点是不是target，是的话返回，否则继续分区
 * 2.优先队列 当k比nums.length - k小时，用小顶堆，否则用大顶堆
 */
public class 数组中的第K个最大元素Easy215 {

    public int findKthLargestByQueue(int[] nums, int k) {
        if (k < nums.length - k) {
            // 小顶堆
            PriorityQueue<Integer> queue = new PriorityQueue<>(k);
            // 优先队列个数没有k个就加入
            for (int i = 0; i < k; i++) {
                queue.add(nums[i]);
            }
            for (int i = k; i < nums.length; i++) {
                // 如果优先队列个数达到了k个，则看n是否大于优先队列中的最小值，大于的话则把队列头出队，把n加入队列
                if (nums[i] > queue.peek()) {
                    queue.remove();
                    queue.add(nums[i]);
                }
            }
            // 最后队列头就是第k个大的元素
            return queue.peek();
        } else {
            int cap = nums.length - k + 1;
            // 大顶堆
            PriorityQueue<Integer> queue = new PriorityQueue<>(cap, (a, b) -> b - a);
            for (int i = 0; i < cap; i++) {
                queue.add(nums[i]);
            }
            for (int i = cap; i < nums.length; i++) {
                // nums[i] < 队列头则入队
                if (nums[i] < queue.peek()) {
                    queue.remove();
                    queue.add(nums[i]);
                }
            }
            // 队列头代表第k大的元素
            return queue.peek();
        }

    }


    private static Random random = new Random();

    /**
     * 时间复杂度: O(n)
     */
    public int findKthLargestByQuickSort(int[] nums, int k) {
        // 要找的元素
        int target = nums.length - k;
        int left = 0;
        int right = nums.length - 1;
        while (true) {
            // 分区点
            int index = partition(nums, left, right);
            if (index == target) {
                return nums[index];
            } else if (index < target) {
                // 往右边找
                left = index + 1;
            } else {
                // 往左边找
                right = right - 1;
            }
        }
    }

    private int partition(int[] nums, int left, int right) {
        // 因为快排对于已经排序好的数组进行排序的效率是O(n^2)，
        // 所以这里随机选择一个数与数组最后一个进行交换（即随机选择一个分区点）
        if (right > left) {
            // left - right中随机选择一个
            int randomIndex = left + 1 + random.nextInt(right - left);
            int temp = nums[randomIndex];
            nums[randomIndex] = nums[right];
            nums[randomIndex] = temp;
        }
        // 二路快排
        int pivot = nums[right];
        int great = left;
        int less = left;
        for (; great < right; great++) {
            if (nums[great] < pivot) {
                int temp = nums[great];
                nums[great] = nums[less];
                nums[less] = temp;
                less++;
            }
        }

        // 将less和pivot进行交换
        nums[right] = nums[less];
        nums[less] = pivot;
        return less;
    }


}
