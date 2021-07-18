package com.bighao.array;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/7/18 0:24
 */
public class 验证回文串Easy125 {

    public static boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            if ((chars[left] < 'a' || chars[left] > 'z') && (chars[left] < '0' || chars[left] > '9') && (chars[left] < 'A' || chars[left] > 'Z')) {
                left++;
            } else if ((chars[right] < 'a' || chars[right] > 'z') && (chars[right] < '0' || chars[right] > '9') && (chars[right] < 'A' || chars[right] > 'Z')) {
                right--;
            } else if (String.valueOf(chars[left]).equalsIgnoreCase(String.valueOf(chars[right]))) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 优化：
     * 1.判断是否是数字和字母优化
     * 2.判断是否相等优化
     *
     * @param s
     * @return
     */
    public static boolean isPalindrome2(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            if (!Character.isLetterOrDigit(chars[left])) {
                left++;
            } else if (!Character.isLetterOrDigit(chars[right])) {
                right--;
            } else if (Character.toLowerCase(chars[left]) == Character.toLowerCase(chars[right])) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "ab_a";
        System.out.println(isPalindrome(s));
    }
}
