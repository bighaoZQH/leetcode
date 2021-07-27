package com.bighao.sort;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/7/27 23:47
 */
public class 把数组排成最小的数剑指OfferMedium45 {

    public String minNumber(int[] nums) {
        String[] arr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = String.valueOf(nums[i]);
        }

        // 三路快排
        quickSort(arr, 0, nums.length - 1);
        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s);
        }
        return sb.toString();
    }

    private void quickSort(String[] arr, int start, int end) {
        if (start >= end) {
            return;
        }

        int less = start;
        int great = end;
        int idx = start;
        String pivot = arr[end];

        while (idx <= great) {
            String xy = arr[idx] + pivot;
            String yx = pivot + arr[idx];
            if (xy.compareTo(yx) > 0) {
                // 如果xy > yx，则把x放到pivot的右边
                String temp = arr[great];
                arr[great] = arr[idx];
                arr[idx] = temp;
                great--;
            } else if (xy.compareTo(yx) < 0) {
                // 如果xy < yx，则把x放到pivot的左边
                String temp = arr[less];
                arr[less] = arr[idx];
                arr[idx] = temp;
                less++;
                idx++;
            } else {
                idx++;
            }
        }

        quickSort(arr, start, less - 1);
        quickSort(arr, great + 1, end);
    }

    public static void main(String[] args) {

    }
}
