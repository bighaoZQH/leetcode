package com.bighao.array;

import java.util.Arrays;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2020/11/29 1:00
 * <p>
 * https://leetcode-cn.com/problems/move-zeroes/
 * 移动零
 * <p>
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数
 *
 * 方法1，双指针，直接在原数组上进行交换
 *   i指向不为0的位置 j指向为0的位置
 *   i迭代，j先不动，如果判断i的位置不为0，
 *   则判断j的位置如果为0则进行数值交换，不为0则不交换，最后将j也向后挪一步
 *
 * 方法2，双指针，两次循环，
 *   i指向不为0的位置，将所有非0的挪到前面，j指向当前非0要挪到的位置
 *   第一次循环完后，所有非0就都挪到前面了，再从j开始向后填充。
 *   即0,0,1,2,3,0,4 ==> 1,2,3,4,3,0,1 此时最后一个非0(即4)挪到的位置下标为3，从下标3开始向后填0
 *
 * 这里的双指针就是快慢指针
 */
public class 移动零Easy283 {

    /**
     * 方法1,双指针 i指向不为0的位置 j指向为0的位置
     *
     * i迭代，j先不动，如果判断i的位置不为0，
     * 则判断j的位置如果为0则进行交换，不为0则不交换，最后将j也向后挪一步
     */
    public static void moveZeroes(int[] nums) {
        for (int i = 0, j = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (nums[j] == 0) {
                    nums[j] = nums[i];
                    nums[i] = 0;
                }
                j++;
            }
        }
    }

    /**
     * 方法2 双指针 i指向不为0的位置，将所有非0的挪到前面，
     * j指向当前非0要挪到的位置
     * 第一个循环完后，所有非0就都挪到前面了，
     *  0,0,1,2,3,0,4 ==> 1,2,3,4,3,0,1
     * 再从j开始向后填充0。
     */
    public static void moveZeroes2(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }

        for (; j < nums.length; j++) {
            nums[j] = 0;
        }
        //while (j < nums.length) nums[j++] = 0;
    }


    /**
     * 极客时间-谭超
     */
    public static void moveZeroes3(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j] = nums[i];
                if (i != j) {
                    nums[i] = 0;
                }
                j++;
            }
        }
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1,0,1};
        moveZeroes(arr);
        System.out.println(Arrays.toString(arr));
    }


}
