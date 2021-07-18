package com.bighao.queue;

import java.util.Stack;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/7/17 17:17
 *
 * 方法1：
 * 一个栈用来appendTail，另一个用来deleteHead
 * appendTail时，如果stack2不为空，则将stack2的pop出来再push到stack1中，最后将新加的push到stack1
 * deleteHead时，如果stack1不为空，则将stack1的pop出来再push到stack2中，最后将stack2的栈顶pop就行
 *
 * 方法2：优化方法1
 * appendTail时，可以直接push到stack1，不需要管stack2是否为空
 * deleteHead时，直接pop stack2的栈顶，stack2为空时，才把stack1的挪到stack2
 *
 * https://ke.qq.com/webcourse/3065907/103186001#taid=10592527521335347&vid=5285890809401099420
 */
public class 剑指Offer09用两个栈实现队列Easy {

    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public 剑指Offer09用两个栈实现队列Easy() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.isEmpty() ? -1 : stack2.pop();
    }


    public static void main(String[] args) {

    }
}
