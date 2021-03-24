package com.bighao.array;

import java.util.Arrays;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/1/17 0:28
 * <p>
 * https://leetcode-cn.com/problems/plus-one/
 */
public class 加一Easy66 {

    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            // 此时不需要再向前进位，直接+1后return
            if (digits[i] != 9) {
                digits[i] = digits[i]++;
                return digits;
            }
        }
        // 走到这说明原来的最高位也要向前进位
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    public static void main(String[] args) {
        int[] digits = {9, 9, 9, 9};
        int[] ints = plusOne(digits);
        System.out.println(Arrays.toString(ints));
    }
}
