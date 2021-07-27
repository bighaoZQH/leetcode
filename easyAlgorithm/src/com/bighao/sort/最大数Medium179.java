package com.bighao.sort;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/7/26 23:40
 *
 * https://leetcode-cn.com/problems/largest-number/
 */
public class 最大数Medium179 {

    public String largestNumber(int[] nums) {
        if (nums == null) {
            return "";
        }

        String[] arr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = String.valueOf(nums[i]);
        }

        // 3,30,34,5,9 -> 9,5,34,3,30
        // 降序，String是按个根据ASCII码进行比较的 (b + a).compareTo(a + b)是降序
        ///Arrays.sort(arr, (a, b) -> (b + a).compareTo(a + b));

        // 用快排改写
        quickSort(arr, 0, nums.length - 1);

        if (nums[0] == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (int s : nums) {
            sb.append(s);
        }
        return sb.toString();
    }

    private void quickSort(String[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        String pivot = arr[end];
        int less = start;
        int great = end;
        int idx = start;

        while (idx <= great) {
            String xy = arr[idx] + pivot;
            String yx = pivot + arr[idx];
            // 如果xy > yx 则把x放到pivot前面
            if (xy.compareTo(yx) > 0) {
                String tmep = arr[less];
                arr[less] = arr[idx];
                arr[idx] = tmep;
                less++;
                idx++;
            } else if (xy.compareTo(yx) < 0) {
                // 如果 xy < yx, 则把x放到pivot后面
                String tmep = arr[great];
                arr[great] = arr[idx];
                arr[idx] = tmep;
                great--;
            } else {
                idx++;
            }
        }
        // 三路快排最后不需要将less和pivot交换，因为在上面的循环中其实已经处理了

        quickSort(arr, start, less - 1);
        quickSort(arr, great + 1, end);
    }

    public static void main(String[] args) {

    }

}
