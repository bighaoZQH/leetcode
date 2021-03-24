package com.bighao.array;

import java.util.Arrays;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/1/11 17:32
 * <p>
 * https://leetcode-cn.com/problems/rotate-array/
 */
public class 旋转数组Meduim189 {


    /**
     * 原地数组
     * k %= nums.length; 处理k>num.length的情况
     */
    public static void rotate(int[] nums, int k) {
        k %= nums.length;
        reverseArr(nums, 0, nums.length - 1);
        reverseArr(nums, 0, k - 1);
        reverseArr(nums, k, nums.length - 1);
    }

    private static void reverseArr(int[] arr, int s, int e) {
        while (s < e) {
            int temp = arr[s];
            arr[s++] = arr[e];
            arr[e--] = temp;
        }
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        rotate(arr, 7);
        System.out.println(Arrays.toString(arr));
    }

}
