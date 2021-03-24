package com.bighao.hash;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/2/15 2:25
 * <p>
 * https://leetcode-cn.com/problems/valid-anagram/
 * 1.暴力 字符排序后看两个字符串是否相同
 * 2.hash map
 * 3.数组
 */
public class 有效的字母异位词Easy242 {

    /**
     * map
     * 1.先遍历一个字符串，计数存入map
     * 2.然后遍历第二个字符串，如果get不到，则返回false
     * 如果get到，减1后为0，则移除该元素，否则将key的计数减1，
     * 最后如果map不为空说明就不是异位词
     */
    public static boolean isAnagram(String s, String t) {
        HashMap<Integer, Integer> map = new HashMap<>();
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        for (int key : sc) {
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        for (int key : tc) {
            Integer c = map.get(key);
            if (c == null) {
                return false;
            } else if (c - 1 == 0) {
                map.remove(key);
            } else {
                map.put(key, c - 1);
            }
        }
        return map.isEmpty();
    }

    /**
     * 数组
     */
    public static boolean isAnagram3(String s, String t) {
        int[] sCounts = new int[26];
        int[] tCounts = new int[26];
        for (char ch : s.toCharArray()) {
            sCounts[ch - 'a']++;
            // csapp...
            //tCounts[ch - 'a']++;
        }
        for (char ch : t.toCharArray()) {
            tCounts[ch - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (sCounts[i] != tCounts[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 暴力 leetcode这里竟然这个更快。。。
     */
    private static boolean isAnagram2(String s, String t) {
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        Arrays.sort(sc);
        Arrays.sort(tc);
        return Arrays.equals(sc, tc);
        // 一行代码解决
        //return Arrays.equals(s.chars().sorted().toArray(), t.chars().sorted().toArray())
    }


    public static void main(String[] args) {
        String s = "ab", t = "a";
        System.out.println(isAnagram3(s, t));
    }


}
