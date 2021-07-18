package com.bighao.stack;

import java.util.Stack;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/1/17 14:16
 *
 * https://leetcode-cn.com/problems/min-stack/
 *
 * 还有两种方式：
 * 1.单个栈也能解决，将数据封装成一个node，里面存储值和[当前]最小值
 * push的时候，每次将当前值和栈顶的node中的最小值比较，更小的话自己就存该最小值
 *
 * 2.自定义栈 -- 用单向链表来实现，当然每个node节点中也需要封装一个[当前]的最小值
 * 每次push都是增加在头部，并和原链表头中的最小值进行比较
 * 这里有个技巧，在真正的链表头前面加一个哨兵，这样更方便，即哨兵.next才是真正的链表头
 * pop的话只要移除表头就行了，最新的最小值就是下一个链表头中封装的min
 */
public class 最小栈Easy155_优化 {

    Stack<Integer> stack = new Stack<>();
    // 单调递减栈，新增的<=栈顶元素
    Stack<Integer> minStack = new Stack<>();

    /** initialize your data structure here. */
    public 最小栈Easy155_优化() {}

    public void push(int val) {
        if (minStack.isEmpty() || val <= minStack.peek()) {
            // 新加的元素如果小于当前的最小值，则加入到minStack中
            minStack.push(val);
        }
        stack.push(val);
    }

    public void pop() {
        // 如果当前出栈的元素是最小的，则也从minStack中出栈
        if (stack.pop().equals(minStack.peek())) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }


    public static void main(String[] args) {
        最小栈Easy155_优化 minStack = new 最小栈Easy155_优化();
        minStack.push(512);
        minStack.push(-1024);
        minStack.push(-1024);
        minStack.push(512);
        minStack.pop();
        minStack.pop();
        minStack.pop();
        System.out.println(minStack.getMin());
    }

}
