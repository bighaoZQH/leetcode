package com.bighao.sort;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/8/1 0:30
 */
public class 数组中的逆序对剑指OfferHard51 {

    public int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        // 如果不想改变原数组的顺序，则这里可以新复制一个数组来进行计算
        // new int[]
        return sort(nums, 0, nums.length - 1, new int[nums.length]);
    }

    public int sort(int[] nums, int left, int right, int[] temp) {
        if (left >= right) {
            return 0;
        }
        int mid = (left + right) / 2;
        int count = sort(nums, left, mid, temp);
        count += sort(nums, mid + 1, right, temp);
        count += merge(nums, left, mid, right, temp);
        return count;
    }

    private int merge(int[] data, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int pos = left;

        int count = 0;

        while (i <= mid && j <= right) {
            // 如果i < j 则不需要统计逆序对
            if (data[i] <= data[j]) {
                temp[pos++] = data[i++];
            } else {
                // 如果i > j，说明是是逆序对
                temp[pos++] = data[j++];
                // 统计逆序对 有多少个呢？ mid - i + 1个
                // 因为此时i已经大于了j，那么(i-mid)的都是大于j的，这几个的数量就是mid - i + 1个
                count += mid - i + 1;
            }
        }

        // 如果左边还有剩余的
        while (i <= mid) {
            temp[pos++] = data[i++];
        }

        // 如果右边还有剩余的
        while (j <= right) {
            temp[pos++] = data[j++];
        }

        // 将temp的left位置开始拷贝回data
        while (left <= right) {
            data[left] = temp[left];
            left++;
        }
        return count;
    }


    public int reversePairs1(int[] nums) {
        // prev = 0
        // 1 + 1 + 1
        // prev*2 + 1
        // 7 5 6 4 3 2 1
        int res = 0;
        int count = 1;
        int prev = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                // 1 1+2  3+3 6+4
                res = res == 0 ? 1 : res + count;
                count++;
                prev = i + 1;
            } else if (nums[i] > nums[prev]) {
                res = res + count;
                count++;
                prev--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{7, 5, 6, 4, 3, 2, 1};
        new 数组中的逆序对剑指OfferHard51().reversePairs(arr);
    }

}
