package com.bighao.bs;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/8/7 3:37
 */
public class 山脉数组中查找目标值Hard1095 {


    /**
     * [1,2,3,4,5,3,1]
     * 3
     * [0,1,2,4,2,1]
     * 3
     *
     * 返回上坡中的，但是可能上坡没有，下坡有
     *
     * 24,69,78,100,99,79,78,67,36,26,19
     * 78
     *
     * [1,5,2]
     * 2
     *
     * 1.先找山顶元素，判断山顶元素是否是target，是则return
     * 2.其次往上坡找，这是里递增的二分查找
     * 3.上坡没找到往下坡找，这是递减的二分
     */
    public static int findInMountainArray(int target, MountainArray mountainArr) {
        int left = 0;
        int right = mountainArr.length() - 1;
        // 先找山顶元素
        while (left < right) {
            int mid = (right - left) / 2 + left;
            // 判断是上坡还是下坡
            if (mountainArr.get(mid) > mountainArr.get(mid + 1)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if (mountainArr.get(left) == target) {
            return left;
        }
        int top = left;
        // 先找上坡 递增的二分
        left = 0;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (target == mountainArr.get(mid)) {
                return mid;
            } else if (target < mountainArr.get(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // 找下坡 注意这里是递减的二分查找
        left = top;
        right = mountainArr.length() - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (target == mountainArr.get(mid)) {
                return mid;
            } else if (target < mountainArr.get(mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

}

interface MountainArray {
    int get(int index);

    int length();
}