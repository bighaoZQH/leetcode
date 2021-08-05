package com.learn.algo.bs;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/8/2 0:19
 */
public class BasicBinarySearch {

    public boolean contains(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            // bug: left + right会溢出
            // 整数的最大值: 2^31 - 1
            // 第一种解决方案 就用这种
            int mid = (right - left) / 2 + left;
            // 第二种 位移来计算 但现在的JVM其实也会把/号翻译成位移运算
            ///int mid = ((right - left) >> 1) + left;
            // 第三种 无符号右移
            ///int mid = (left + right) >>> 1;

            ///int mid = (left + right) / 2;
            if (target == nums[mid]) {
                return true;
            } else if (target < nums[mid]) {
                // 下一次搜索区间[left, mid - 1]
                right = mid - 1;
            } else {
                // 下一次搜索区间[mid + 1, right]
                left = mid + 1;
            }
        }
        return true;
    }

    private boolean containsR(int[] nums, int target) {
        return contains(nums, 0, nums.length - 1, target);
    }

    // 递归实现
    private boolean contains(int[] nums, int left, int right, int target) {
        if (left > right) {
            return false;
        }
        int mid = (right - left) / 2 + left;
        if (nums[mid] == target) {
            return true;
        }

        if (target < nums[mid]) {
            return contains(nums, left, mid - 1, target);
        } else {
            return contains(nums, mid + 1, left, target);
        }
    }

}
