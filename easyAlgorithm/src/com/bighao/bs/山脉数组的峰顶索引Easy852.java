package com.bighao.bs;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/8/7 3:30
 */
public class 山脉数组的峰顶索引Easy852 {

    /**
     * 24,69,100,99,79,78,67,36,26,19
     *
     *          ——
     *         /  \
     *        /    \
     *       /      \
     *      /        \
     */
    public int peakIndexInMountainArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            // 判断mid在上坡，还是下坡
            if (arr[mid] > arr[mid + 1]) {
                // mid在下坡 说明最大值在left - mid中(包括mid)
                right = mid;
            } else {
                // mid在上坡，最大值在mid+1 - right中
                left = mid + 1;
            }
        }
        // left == right 说明就是山顶元素
        // 最后return left或者right都行
        return left;
    }

}
