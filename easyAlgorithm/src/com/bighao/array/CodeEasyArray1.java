package com.bighao.array;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2020/11/29 19:43
 * <p>
 * https://leetcode-cn.com/problems/two-sum/
 * 1.暴力法
 * 2.哈希表
 */
public class CodeEasyArray1 {

    /**
     * 想法：当9-nums[i]=7, 那么我怎么快速知道原数组中是否有7呢？ 通过哈希表
     * 哈希表
     * 遍历数组，将当前值作为key，下标作为value，放入哈希表，
     * 如果target - 当前值 能够从哈希表中取出，说明找到了结果，则返回结果
     *
     * 本质思想是利用哈希来快速找到原数组中的值，这种方式可以减少一层循环
     * 如果是3数相加，这种方式可以通过1层与2层的计算结果来从哈希表中取。
     */
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * 暴力法 双指针 j指向i后面一个位置，两数相加看是否等于target
     */
    public static int[] twoSum2(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }


    public static void main(String[] args) {
        int[] arr = twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println(Arrays.toString(arr));
    }

}
