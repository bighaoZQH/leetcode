package com.bighao.bs;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/8/4 23:26
 */
public class 搜索插入位置Easy35 {

    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (target <= nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // 查找第一个大于等于target的
    public int searchInsert1(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (target == nums[mid]) {
                if (mid == 0 || nums[mid - 1] < target) {
                    return mid;
                }
                right = mid - 1;
            } else if (target < nums[mid]) {
                if (mid == 0 ||  target > nums[mid - 1]) {
                    return mid;
                }
                right = mid - 1;
            } else {
                if (mid == nums.length - 1 || target < nums[mid + 1]) {
                    return mid + 1;
                }
                left = mid + 1;
            }
        }

        return 0;
    }

}
