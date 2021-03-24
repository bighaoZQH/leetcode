package com.bighao.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2020/11/29 20:35
 * <p>
 * https://leetcode-cn.com/problems/3sum/
 * 1.暴力 - 但要额外处理结果不能重复的情况
 * 2.哈希表
 * 3.左右指针
 * 左右指针题解：https://leetcode-cn.com/problems/3sum/solution/3sumpai-xu-shuang-zhi-zhen-yi-dong-by-jyd/
 */
public class CodeEasyArray15 {

    /**
     * 左右指针无注释，简化版
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum0(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int k = 0, i, j, t;
        for (; k < nums.length - 2; k++) {
            if (nums[k] > 0) break;
            while (k > 0 && nums[k] == nums[k - 1]) k++;
            i = k + 1;
            j = nums.length - 1;
            while (i < j) {
                t = nums[k] + nums[i] + nums[j];
                if (t < 0) {
                    i++;
                } else if (t > 0) {
                    j++;
                } else {
                    if (i - 1 == k) {
                        result.add(Arrays.asList(nums[k], nums[i], nums[j]));
                    } else if (nums[i] != nums[i - 1]) {
                        result.add(Arrays.asList(nums[k], nums[i], nums[j]));
                    }
                    i++;
                    j--;
                }
            }
        }
        return result;
    }

    /**
     * -4 -1 -1 0 1 2
     * 左右指针移动法
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // 排序数组
        Arrays.sort(nums);
        // k作为target，i和j作为左右指针
        int k = 0, i, j, t;
        for (; k < nums.length - 2; k++) {
            // 因为数组是排序的，所以当num[k]>0就不需要再查找了
            if (nums[k] > 0) {
                break;
            }
            // 当k>0 并且 当前num[k]与上一次循环的num[k]的值一样，说明这种情况已经查找过了，就不用重复查找了
            /*if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }*/
            while (k > 0 && k < nums.length - 2 && nums[k] == nums[k - 1]) {
                k++;
            }
            // k移动后，重置i和j
            i = k + 1;
            j = nums.length - 1;

            // 当前nums[k]为target，将i和j向内收敛来查找满足条件的结果
            while (i < j) {
                t = nums[k] + nums[i] + nums[j];
                if (t == 0) {
                    // 因为是三个数的结果，k在上面已经确定不重复查找，因此这里只要确定i或者j不与前一个i或者j重复就行
                    // 两个数确定不会重复查找，最后一个数就可以不判断了
                    if (i - 1 == k) {
                        result.add(Arrays.asList(nums[k], nums[i], nums[j]));
                    } else if (nums[i] != nums[i - 1]) {
                        result.add(Arrays.asList(nums[k], nums[i], nums[j]));
                    }
                    // 找到后移动两个指针
                    i++;
                    j--;
                } else if (t < 0) {
                    // 因为是排序的，t<0说明num[i]小了，向后移动
                    i++;
                } else {
                    // 因为是排序的，t>0说明num[j]大了，向前移动
                    j--;
                }
            }
        }
        return result;
    }


    /**
     * 方法1
     * 暴力法 - 三层循环
     * 而且为了不能重复，还需要将当前结果排序后 来遍历原来的集合，看是否有和已存在的结果有重复的
     * <p>
     * 该方法leetcode超时
     */
    public static List<List<Integer>> threeSum3(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int x = j + 1; x < nums.length; x++) {
                    if (nums[i] + nums[j] + nums[x] == 0) {
                        // 当前结果
                        List<Integer> list = Arrays.asList(nums[i], nums[j], nums[x]);
                        // 排序
                        Collections.sort(list);
                        // 是否可以放入结果集
                        boolean flag = true;
                        // 遍历结果集，看是否有重复的结果
                        for (List<Integer> l : result) {
                            if (l.equals(list)) {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            // 没有重复的就将当前结果放入结果集
                            result.add(list);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        //List<List<Integer>> lists = threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        ///List<List<Integer>> lists = threeSum(new int[]{1, -2, -5, -13, -10, -11, 0, -12, -11, 13, -4, 9, 8, 10, -7, 3, -9, -12, -7, 8, -2, -12, 1, -10, -15, -8, 5, 14, -7, -8, -8, 9, -3, -6, 3, -5, -1, -11, -10, 3, -13, 1, -10, 3, -12, -10, -9, -13, -7, -1, 10, 6, -6, -12, 12, -13, -13, -6, -14, -13, -7, -7, 4, 6, -6, -8, 8, 8, -4, 13, -11, -1, -8, -14, 9, -5, -9, 7, -3, -1, 14, 14, 13, -7, 9, 2, -5, 12, 11, -12, 14, -11, -12, 3, 2, -2, 3, -5, -9, 14, -14, -13, -10, -7, -12, 14, 3, -6, -1, 8, 1, -2, -1, -1, 6, -6});
        //List<List<Integer>> lists = threeSum(new int[]{-1, 0, 1, 0});
        //List<List<Integer>> lists = threeSum(new int[]{0, 0, 0, 0});
        List<List<Integer>> lists = threeSum(new int[]{-1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4});
        lists.forEach(l -> System.out.println(Arrays.toString(l.toArray())));
    }

}
