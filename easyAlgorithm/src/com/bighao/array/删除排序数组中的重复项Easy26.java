package com.bighao.array;

import java.util.Arrays;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/1/11 16:55
 *
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 */
public class 删除排序数组中的重复项Easy26 {


    /**
     * 双指针法
     */
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                nums[++i] = nums[j];
            }
        }
        return i + 1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        Arrays.sort(arr);
        System.out.println(removeDuplicates(arr));
        System.out.println(Arrays.toString(arr));
    }

}
