package com.bighao.array;

import java.util.Arrays;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/1/14 15:07
 * <p>
 * https://leetcode-cn.com/problems/merge-sorted-array/
 */
public class 合并两个有序数组Easy88 {


    /**
     * 双指针，从后往前填充
     */
    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        int i = m  - 1, j = n - 1, t = m + n - 1;
        while (i >= 0 && j >= 0) {
            /*if (nums1[i] < nums2[j]) {
                nums1[t--] = nums2[j--];
            } else if (nums1[i] > nums2[j]) {
                nums1[t--] = nums1[i--];
            } else {
                nums1[t--] = nums1[i--];
                nums1[t--] = nums2[j--];
            }*/
            nums1[t--] = nums1[i] < nums2[j] ? nums2[j--] : nums1[i--];
        }
        while (j >= 0) {
            nums1[t--] = nums2[j--];
        }
    }

    /**
     * 先合并后排序，简单但时间复杂度低
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        /*for (int i = 0; i < n; i++) {
            nums1[m + i] = nums2[i];
        }*/
        //System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 6, 7, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        merge2(nums1, 3, nums2, nums2.length);
        System.out.println(Arrays.toString(nums1));
    }
}
