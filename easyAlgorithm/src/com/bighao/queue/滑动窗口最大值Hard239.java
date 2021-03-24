package com.bighao.queue;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/2/2 16:41
 * <p>
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 */
public class 滑动窗口最大值Hard239 {

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null) {
            return null;
        }
        Deque<Integer> deque = new LinkedList();
        int[] res = new int[nums.length - k + 1];
        int idx = 0;

        for (int i = 0; i < k; i++) {
            // 如果当前元素比队列尾节点大，则移除尾节点
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.offerLast(nums[i]);
        }
        res[idx++] = deque.peek();

        for (int i = k; i < nums.length; i++) {
            // 如果队列中最大的元素已经不在滑动窗口中了，则出队列
            if (nums[i - k] == deque.peek()) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.offerLast(nums[i]);
            res[idx++] = deque.peek();
        }
        return res;
    }


    public static void main(String[] args) {
        //int[] arr = {3213, 43, 45, 2, 4324, 223, 346, -54, 45, -545435, -5435, 656, -654, -65, 0};
        //int[] res = maxSlidingWindow(arr, 4);
        //int[] arr = {-123, -32, 60, 0, 123, 3230, 230, 43, 0, -123, -34, -56, -32, -43, -155, 3231, -43, 34, 545, 65, 543};
        //int[] res = maxSlidingWindow(arr, 4);
        //int[] arr = {9, 10, 9, -7, -4, -8, 2, -6};
        //int[] res = maxSlidingWindow(arr, 5);

        //int[] arr = {-7, -8, 7, 5, 7, 1, 6, 0};
        //int[] res = maxSlidingWindow(arr, 4);

        int[] arr = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] res = maxSlidingWindow(arr, 3);
        System.out.println(Arrays.toString(res));
    }


}