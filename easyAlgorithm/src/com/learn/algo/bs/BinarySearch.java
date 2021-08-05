package com.learn.algo.bs;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/8/2 23:58
 */
public class BinarySearch {

    // 返回第一个目标值的索引
    public int firstTargetElement(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (target == nums[mid]) {
                // 直接写while不太好，如果等于的值太多，还是得用二分
                /*while (mid > 0 && (nums[mid - 1] == nums[mid])) {
                    mid--;
                }*/
                if (mid == 0 || (nums[mid - 1] < nums[mid])) {
                    return mid;
                }
                right = mid - 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }

    // 返回第一个大于等于目标值的索引
    public int firstTargetGEElement(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (target == nums[mid]) {
                if (mid == 0 || (nums[mid - 1] < target)) {
                    return mid;
                }
                right = mid - 1;
            } else if (target < nums[mid]) {
                if (mid == 0 || (nums[mid -  1] < target)) {
                    return mid;
                }
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5, 5, 5, 6, 12, 23, 23, 213, 213, 312, 536};
        System.out.println(new BinarySearch().firstTargetElement(nums, 213));
        System.out.println(new BinarySearch().firstTargetGEElement(nums, 6));

    }
}
