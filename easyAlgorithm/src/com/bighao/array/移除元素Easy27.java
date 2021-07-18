package com.bighao.array;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/7/17 23:26
 */
public class 移除元素Easy27 {

    /**
     * 跟移动零Easy283一样...
     * 快慢指针
     */
    public int removeElement(int[] nums, int val) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                if (nums[slow] == val) {
                    nums[slow] = nums[fast];
                    nums[fast] = val;
                }
                slow++;
            }
        }
        return slow;
    }


    /**
     * 对撞指针
     * 如果left为val，则和right交换，然后right--，此时left不动
     *      为什么left不动？因为right换过来的可能还是val，则下一次循环可以再次判断下
     * 如果left不为val，则left++后进行下一次循环
     *
     * 我个人感觉还是第一种方式快慢指针好点，效率更高
     */
    public static int removeElement2(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] == val) {
                nums[left] = nums[right];
                right--;
            } else {
                left++;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 2, 2};
        removeElement2(nums, 2);

    }

}
