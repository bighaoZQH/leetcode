package com.bighao.sort;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/7/27 23:53
 */
public class 颜色分类Medium75 {

    public void sortColors(int[] nums) {
        // 三路快排，但是不需要递归，因为数据规定了只有0,1,2三种
        int less = 0;
        int great = nums.length - 1;
        int idx = 0;
        while (idx <= great) {
            if (nums[idx] == 0) {
                int temp = nums[less];
                nums[less] = nums[idx];
                nums[idx] = temp;
                less++;
                idx++;
            } else if (nums[idx] == 2) {
                int temp = nums[great];
                nums[great] = nums[idx];
                nums[idx] = temp;
                great--;
            } else {
                idx++;
            }
        }
    }

    public static void main(String[] args) {

    }

}
