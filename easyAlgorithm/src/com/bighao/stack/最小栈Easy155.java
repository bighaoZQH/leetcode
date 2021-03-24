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
    Stack<Integer> minStack = new Stack<>();

    /** initialize your data structure here. */
    public 最小栈Easy155() {}

    public void push(int x) {
        if (min >= x) {
            min = x;
            minStack.push(min);
        }
        stack.push(x);
    }

    public void pop() {
        if (stack.pop() == min) {
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
