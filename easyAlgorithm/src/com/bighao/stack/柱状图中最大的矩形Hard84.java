package com.bighao.stack;

import java.util.Stack;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/1/17 15:29
 * <p>
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
 */
public class 柱状图中最大的矩形Hard84 {


    /**
     * 最优解 - 单调栈
     */
    public static int largestRectangleArea3(int[] heights) {
        // 栈里存放右边界下边，从小到大存
        Stack<Integer> stack = new Stack<>();
        // 先存入一个假定的最小右边界
        stack.push(-1);
        int max = 0;

        for (int i = 0; i < heights.length; i++) {
            int h = heights[i];
            // 如果当前柱子的高度和上一个柱子的高度相同，则不处理
            /*if (stack.peek() != -1 && h == heights[stack.peek()]) {
                continue;
            }*/
            // 如果当前柱子高度 比 栈顶大，则将栈顶出栈，计算栈顶元素的最大面积
            while (stack.peek() != -1 && h < heights[stack.peek()]) {
                int pop = heights[stack.pop()];
                max = Math.max(max, pop * (i - stack.peek() - 1));
            }
            stack.push(i);
        }

        // 处理栈里剩余的柱子
        while (stack.size() != 1) {
            int idx = stack.pop();
            int right = heights.length;
            int left = stack.peek();
            max = Math.max(max, heights[idx] * (right - left - 1));
        }
        //stack.pop();
        return max;
    }


    /**
     * 暴力求解2 - 优化法 先枚举高度，再根据当前高度去枚左右边界
     * 但如果数据里重复数据很多的情况下，用单调栈更合适
     */
    public static int largestRectangleArea2(int[] heights) {
        int max = 0;
        for (int mid = 0; mid < heights.length; mid++) {
            // 如果是同样高度的柱子，就不用重复计算了
            if (mid > 0 && heights[mid] == heights[mid - 1]) {
                continue;
            }
            int h = heights[mid];
            int right = mid;
            int left = mid;

            // 枚举右边界，如果右边界比当前高，则继续往右边找，直到找到比当前高度小的那个边界，此时rMin停留在最小那个边界的前一个位置
            while (right < heights.length - 1 && heights[right + 1] >= h) {
                right++;
            }
            while (left > 0 && heights[left - 1] >= h) {
                left--;
            }
            int localMax = Math.max(h, h * (right - left + 1));
            max = Math.max(max, localMax);
        }
        return max;
    }


    public static void main(String[] args) {
        int[] arr = {3, 43, 4324, 643, 1, 2, 3, 5, 898, 65, 3};
        int[] arr1 = {2, 1, 5, 6, 2, 3};
        int[] arr2 = {0, 9};
        int[] arr3 = {9};
        int[] arr4 = {2, 1, 2};
        int[] arr5 = {1, 1};
        int[] arr6 = {0, 1, 0, 1};
        int[] arr7 = {1, 0, 0, 1, 0, 1, 0, 0, 0, 1};
        //System.out.println(largestRectangleArea3(arr));
        //System.out.println(largestRectangleArea3(arr1));
        //System.out.println(largestRectangleArea3(arr2));
        //System.out.println(largestRectangleArea3(arr3));
        //System.out.println(largestRectangleArea3(arr4));
        //System.out.println(largestRectangleArea3(arr5));
        //System.out.println(largestRectangleArea3(arr6));
        System.out.println(largestRectangleArea3(arr7)); //1

        /*long start = System.currentTimeMillis();
        System.out.println(largestRectangleArea3(bigArr));
        System.out.println("耗时= " + (System.currentTimeMillis() - start));

        long start1 = System.currentTimeMillis();
        System.out.println(largestRectangleArea2(bigArr));
        System.out.println("耗时= " + (System.currentTimeMillis() - start1));*/
    }

    private static int[] bigArr = new int[999999];

    static {
        for (int i = 0; i < bigArr.length; i++) {
            bigArr[i] = 808;
            //bigArr[i] = (int) (Math.random() * 1000);
        }
    }

}
