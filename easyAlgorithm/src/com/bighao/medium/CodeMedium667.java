package com.bighao.medium;

import java.util.Arrays;

/**
 * @Author: bighao周启豪
 * @Date: 2020/4/17 18:55
 * @Version 1.0
 *
 * https://leetcode-cn.com/problems/beautiful-arrangement-ii/
 */
public class CodeMedium667 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(constructArray(50, 20)));
    }

    /**
     * 1 2 3 4 5 6 7 8 9 10
     * k=1      1 2 3 4 5 6 7 8 9 10
     * k=2      1 3 2 4 5 6 7 8 9 10
     * k=3      1 4 3 2 5 6 7 8 9 10
     * k=4      1 5 2 3 4 6 7 8 9 10
     * k=5      1 6 2 5 3 4 7 8 9 10
     * k=6      1 7 2 6 3 5 4 8 9 10
     *
     * 分两段来填充数组，前段将k+1开始向前插入，一直插到i=k为止
     */
    public static int[] constructArray(int n, int k) {
        int[] arr = new int[n];
        // 第一个数恒定为1
        arr[0] = 1;
        // i作为位置的下标，a+1表示基数位的值， j+1表示偶数位的值
        int i = 1, a = 1, j = k;
        for (; i < k + 1; i++) {
            // 偶数位的值用插入进来的值，最大的值就是k+1
            if ((i + 1) % 2 == 0) {
                arr[i] = j-- + 1;
                continue;
            }
            // 填充基数位
            a++;
            arr[i] = a;
        }

        // 从k+1开始填充剩余的
        for (i = k + 1; i < n; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

}
