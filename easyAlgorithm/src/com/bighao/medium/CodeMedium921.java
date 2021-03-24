package com.bighao.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: bighao周启豪
 * @Date: 2020/5/17 23:42
 * @Version 1.0
 */
public class CodeMedium921 {

    public static void main(String[] args) {
        int i = minAddToMakeValid("()()((()()())))(())()(");
        //int i = minAddToMakeValid("(())");
        System.out.println(i);
    }


    // 用栈的思路，不用栈来实现
    public static int minAddToMakeValid(String s) {
        char[] chars = s.toCharArray();
        int x = 0, y = 0;
        for (char c : chars) {
            // 为'(' 就将x+1
            if (c == '(') {
                x++;
            } else if (x > 0) {
                // 如果当前为')'，且有'('的计数，就将该计数-1
                x--;
            } else {
                // 否则将')'的计数+1
                y++;
            }
        }
        // 最后返回两个计数的和
        return x + y;
    }


    // 栈实现
    public static int minAddToMakeValid2(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> s1 = new Stack<>();
        int x = 0;

        for (Character c : chars) {
            if (c == '(') {
                s1.push(c);
            } else if (!s1.isEmpty()) {
                s1.pop();
            } else {
                x++;
            }
        }
        return s1.size() + x;
    }

}
