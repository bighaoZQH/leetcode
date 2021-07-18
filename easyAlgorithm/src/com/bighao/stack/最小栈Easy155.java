package com.bighao.stack;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/1/17 14:16
 *
 * https://leetcode-cn.com/problems/min-stack/
 */
public class 最小栈Easy155 {

    int min = Integer.MAX_VALUE;
    Stack<Integer> stack = new Stack<>();
    // 单调递减栈，新增的<=栈顶元素
    Stack<Integer> minStack = new Stack<>();

    /** initialize your data structure here. */
    public 最小栈Easy155() {}

    public void push(int val) {
        if (val <= min) {
            // 新加的元素如果小于当前的最小值，则加入到minStack中
            min = val;
            minStack.push(val);
        }
        stack.push(val);
    }

    public void pop() {
        if (stack.pop() == min) {
            // 如果当前出栈的元素是最小的，则也从minStack中出栈，并将min设置为minStack最新的栈顶元素
            minStack.pop();
            min = minStack.empty() ? Integer.MAX_VALUE : minStack.peek();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }


    public static void main(String[] args) {
        最小栈Easy155 minStack = new 最小栈Easy155();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        minStack.top();
        System.out.println(minStack.getMin());
    }

}
