package com.bighao.bs;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/8/5 23:37
 */
public class 旋转数组的最小数字剑指offerEasy11 {

    public static int minArray(int[] numbers) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            // mid > right 那小的肯定在mid+1-right中
            if (numbers[mid] > numbers[right]) {
                left = mid + 1;
            }
            //else if (numbers[mid] < numbers[left]) {这两行都可以
            else if (numbers[mid] < numbers[right]) {
                // mid < right 那小的肯定left-mid中间
                right = mid;
            } else {
                // numbers[mid] == numbers[right]时 right移动一格
                right--;
            }
        }
        return numbers[left];
    }

    public static void main(String[] args) {
        int[] a1 = new int[]{2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2};
        System.out.println(minArray(a1));

        int[] a2 = new int[]{1, 3};
        System.out.println(minArray(a2));

        int[] a3 = new int[]{3, 1};
        System.out.println(minArray(a3));

        int[] a4 = new int[]{3, 3, 1, 3};
        System.out.println(minArray(a4));

        int[] a5 = new int[]{3, 4, 5, 1, 2};
        System.out.println(minArray(a5));

        int[] a6 = new int[]{2, 2, 2, 0, 1};
        System.out.println(minArray(a6));

        int[] a7 = new int[]{10, 1, 10, 10, 10};
        System.out.println(minArray(a7));
    }
}
