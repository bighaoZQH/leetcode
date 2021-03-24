package com.bighao.hash;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/2/15 3:05
 * <p>
 * https://leetcode-cn.com/problems/group-anagrams
 * <p>
 * 1.排序
 * 2.计数
 */
public class 字母异位词分组Medium49 {


    /**
     * 计数
     * 18ms 18.06%
     */
    public static List<List<String>> groupAnagrams4(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] countArr = new int[26];
            for (char c : str.toCharArray()) {
                countArr[c - 'a']++;
            }
            StringBuilder sBuilder = new StringBuilder();
            for (int i = 0; i < countArr.length; i++) {
                if (countArr[i] != 0) {
                    sBuilder.append((char) (i + 'a')).append(countArr[i]);
                }
            }
            String key = sBuilder.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 排序 + stream流分组
     * 10ms, 39.38%
     * <p>
     * 还有更短的
     * https://leetcode-cn.com/problems/group-anagrams/solution/kan-wo-yi-ju-hua-ac-zi-mu-yi-wei-ci-fen-yrnis/
     */
    public static List<List<String>> groupAnagrams3(String[] strs) {
        return new ArrayList<>(Arrays.stream(strs).collect(Collectors.groupingBy(s -> {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            return String.valueOf(chars);
        })).values());
    }

    /**
     * 排序 + hash表
     * 8ms 超过64%
     */
    public static List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] ch = str.toCharArray();
            Arrays.sort(ch);
            String key = String.valueOf(ch);
            if (!map.containsKey(key)) {
                map.put(key, new LinkedList<>());
            }
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }


    /**
     * 排序
     * 没问题，leetcode ac超时
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<String> list = new ArrayList<>(Arrays.asList(strs));
        List<List<String>> result = new LinkedList<>();

        while (!list.isEmpty()) {
            // 移除 并 得到list中的第一个元素,作为当前要寻找的异位词
            String cur = list.remove(0);
            int[] cArr = cur.chars().sorted().toArray();
            LinkedList<String> l1 = new LinkedList<>();
            l1.add(cur);
            result.add(l1);
            // 通过迭代器来遍历
            Iterator<String> iterator = list.iterator();
            // 从剩余的集合中查询是否是cur的异位词，如果是则加入到当前list中，并从原来的集合中移除
            while (iterator.hasNext()) {
                String next = iterator.next();
                if (Arrays.equals(cArr, next.chars().sorted().toArray())) {
                    iterator.remove();
                    l1.add(next);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        for (List<String> groupAnagram : groupAnagrams4(strs)) {
            System.out.println(Arrays.toString(groupAnagram.toArray()));
        }
    }

}
