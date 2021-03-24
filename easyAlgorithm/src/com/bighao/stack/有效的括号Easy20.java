package com.bighao.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/1/17 13:35
 * <p>
 * https://leetcode-cn.com/problems/valid-parentheses/
 * <p>
 * 什么样的东西可以用栈来解决？ 具有最近相关性
 */
public class 有效的括号Easy20 {

    public static boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : chars) {
            if (map.containsValue(c)) {
                stack.push(c);
            } else if (stack.empty() || map.get(c) != stack.pop()) {
                return false;
            }
        }
        return stack.size() == 0;
    }

    public static void main(String[] args) {
        String s = "]";
        System.out.println(isValid(s));
    }

}
