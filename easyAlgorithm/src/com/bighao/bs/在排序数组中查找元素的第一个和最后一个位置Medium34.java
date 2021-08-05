package com.bighao.bs;

import java.util.Arrays;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/8/4 0:14
 */
public class 在排序数组中查找元素的第一个和最后一个位置Medium34 {

    public static int[] searchRange(int[] nums, int target) {
        int[] res = new int[] {-1, -1};
        if (nums == null || nums.length == 0) {
            return res;
        }

        int left = 0;
        int right = nums.length - 1;
        // 先找最早出现的
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (target == nums[mid]) {
                if (mid == 0 || nums[mid - 1] != nums[mid]) {
                    res[0] = mid;
                    break;
                }
                right = mid - 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // 如果没有出现过
        if (res[0]== -1) {
            return res;
        }

        // 再找最后出现的
        left = res[0];
        right = nums.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (target == nums[mid]) {
                if (mid == nums.length - 1 || nums[mid + 1] != nums[mid]) {
                    res[1] = mid;
                    break;
                }
                left = mid + 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {2, 2};
        System.out.println(searchRange(nums, 2));
    }

}
