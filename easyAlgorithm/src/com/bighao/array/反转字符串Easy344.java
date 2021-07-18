package com.bighao.array;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/7/17 22:54
 */
public class 反转字符串Easy344 {

    /**
     * 对撞指针
     */
    public static void reverseString(char[] s) {
        if (s == null || s.length == 0) {
            return;
        }
        for (int i = 0, j = s.length - 1; i < s.length; i++, j--) {
            if (i >= j) {
                return;
            }
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
        }
    }

    public static void main(String[] args) {
        char[] s = {'H', 'a', 'n', 'n', 'a', 'h'};
        reverseString(s);
    }
}
