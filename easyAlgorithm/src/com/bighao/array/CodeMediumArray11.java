package com.bighao.array;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2020/11/29 15:28
 * <p>
 * https://leetcode-cn.com/problems/container-with-most-water/
 * <p>
 * 方式1: 暴力法(枚举) 时间复杂度很低
 * 方法2: 双指针，从数组两个边界开始计算，两个指针向内遍历，直到相遇，
 * 注意，每次挪动是更小值的那个指针往里挪动。
 */
public class CodeMediumArray11 {

    /**
     * 方法2: 双指针，从数组两个边界开始计算，两个指针向内遍历，直到相遇，
     * 注意，每次挪动是更小值的那个指针往里挪动。
     * 如果挪动后的值比之前的大，则 有可能 面积会更大，
     * 如果比之前的值小，则面积 肯定 会更小，就不用计算了，因此就把另一个指针移动后进行面积计算
     * 然后再拿该面积与之前计算的面积进行比大小就行。
     */
    public static int maxArea(int[] arr) {
        int max = 0;
        for (int i = 0, j = arr.length - 1; i < j; ) {
            // 值更小的指针进行挪动
            int minHeight = arr[i] < arr[j] ? arr[i++] : arr[j--];
            // 挪动后计算面积，并和之前计算的面积进行比大小
            max = Math.max(max, (j + 1 - i) * minHeight);
        }
        return max;
    }


    // 方式1: 暴力法(枚举)
    public static int maxArea2(int[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int tmpArea = (j - i) * Math.min(arr[i], arr[j]);
                max = Math.max(tmpArea, max);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(arr));
    }

}
