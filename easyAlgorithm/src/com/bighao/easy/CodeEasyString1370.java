package com.bighao.easy;

import java.util.*;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2020/11/25 10:25
 * <p>
 * https://leetcode-cn.com/problems/increasing-decreasing-string/
 */
public class CodeEasyString1370 {


    /**
     * 就用一个长度为26的桶来计数，最后通过正向和反向遍历桶，来拼接结果字符串即可
     * @param s
     * @return
     */
    public static String sortString(String s) {
        String result = "";
        int[] count = new int[26];
        // s.toCharArray()底层进行了数组拷贝 不太好
        /*for (char c : s.toCharArray()) {
            count[c - 97]++;
        }*/
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 97]++;
        }

        while (result.length() < s.length()) {
            for (int x = 0; x < 26; x++) {
                if (count[x]-- > 0) {
                    result += (char) (x + 97);
                }
            }

            for (int y = 25; y >= 0; y--) {
                if (count[y]-- > 0) {
                    result += (char) (y + 97);
                }
            }
        }

        return result;
    }

    /**
     * 桶遍历的方式
     * 这里的方式不太好 直接通过两个基本类型数组来解决
     *
     * @param s
     * @return
     */
    public static String sortString2(String s) {
        String result = "";
        char[] chars = s.toCharArray();

        Map<Character, Integer> map = new HashMap<>(26);

        int max = 1;
        for (int i = 0; i < chars.length; i++) {
            Integer count = map.get(chars[i]);
            if (count == null) {
                map.put(chars[i], 1);
            } else {
                count++;
                map.put(chars[i], count);
                if (count > max) {
                    max = count;
                }
            }
        }

        ArrayList<Map.Entry<Character, Integer>> entryList = new ArrayList<>(map.entrySet());
        entryList.sort(Comparator.comparing(Map.Entry::getKey));

        for (int x = 0; x < max; x++) {
            Iterator<Map.Entry<Character, Integer>> iterator = entryList.iterator();
            while (iterator.hasNext()) {
                Map.Entry<Character, Integer> entry = iterator.next();
                if (entry.getValue() > 0) {
                    entry.setValue(entry.getValue() - 1);
                    result += entry.getKey();
                }
            }

            ListIterator<Map.Entry<Character, Integer>> iterator1 = entryList.listIterator(map.size());
            while (iterator1.hasPrevious()) {
                Map.Entry<Character, Integer> entry = iterator1.previous();
                if (entry.getValue() > 0) {
                    entry.setValue(entry.getValue() - 1);
                    result += entry.getKey();
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(sortString("wfqwfwqfegresfveggwefweqfqwfwegreqwrwqergvewrg"));
    }

}
