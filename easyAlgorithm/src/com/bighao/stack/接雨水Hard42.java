package com.bighao.stack;

import java.util.Stack;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/2/3 14:07
 */
public class 接雨水Hard42 {

    /**
     * 单调栈
     */
    public static int trap(int[] height) {
        int count = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            int right = height[i];
            // 栈为空时，为0就跳过，不为0直接入栈
            if (stack.isEmpty()) {
                if (right != 0) {
                    stack.push(i);
                }
            }
            // 和栈顶相等就移除栈顶，将当前放入
            else if (right == height[stack.peek()]) {
                stack.pop();
                stack.push(i);
            }
            // 小于栈顶 直接入栈
            else if (right < height[stack.peek()]) {
                stack.push(i);
            } else {
                while (right > height[stack.peek()]) {
                    // 如果栈中只剩一个元素了，说明此时没有左边界了，移除栈顶后直接将当前加入
                    if (stack.size() == 1) {
                        stack.pop();
                        stack.push(i);
                        break;
                    }
                    Integer mid = stack.pop();
                    Integer left = stack.peek();
                    count += (Math.min(height[left], right) - height[mid]) * (i - left - 1);
                }
                stack.push(i);
            }


        }
        return count;
    }

    public static void main(String[] args) {
        //int[] height = {0, 0, 0, 0, 0, 342, 56456, 65463, 45, 0, 0, 6546, 0, 7657, 0, 45434, 3246, 546, 7456, 5435, 0, 0, 565, 87, 0};
        //int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        //int[] height = {4, 2, 0, 3, 2, 5};
        int[] height = {5, 5, 1, 7, 1, 1, 5, 2, 7, 6};
        System.out.println(trap(height));
    }

}
