package com.learn.algo.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/7/25 20:37
 */
public class BucketSort {

    public void sort(int[] data) {
        if (data == null || data.length <= 1) {
            return;
        }

        // 1.遍历得到最大值
        int maxValue = Integer.MIN_VALUE;
        int minValue = Integer.MAX_VALUE;
        for (int val : data) {
            maxValue = Math.max(maxValue, val);
            minValue = Math.min(minValue, val);
        }
        // 2.创建几个空的bucket
        // 桶数
        int bucketNum = (maxValue - minValue) / data.length + 1;
        ArrayList<Integer>[] buckets = new ArrayList[bucketNum];

        // 3.将所有的元素都放到bucket中
        for (int val : data) {
            // 计算放到哪个桶里
            int index = (val - minValue) / (data.length);
            // 负数会计算出负数，则放到第一个桶里
            index = Math.max(index, 0);
            if (buckets[index] == null) {
                // 初始化桶
                buckets[index] = new ArrayList<>();
            }
            buckets[index].add(val);
        }

        // 4.将每个桶进行排序（这里用快排）
        for (List<Integer> bucketList : buckets) {
            if (bucketList != null) {
                quickSort(bucketList);
            }
        }

        // 5.遍历每一个桶，拿到排序后的数据放入原数组中
        int idx = 0;
        for (ArrayList<Integer> bucket : buckets) {
            if (bucket != null && bucket.size() > 0) {
                for (Integer val : bucket) {
                    data[idx++] = val;
                }
            }
        }
    }


    public void quickSort(List<Integer> list) {
        if (list == null || list.size() < 2) {
            return;
        }
        quickSort(list, 0, list.size() - 1);
    }

    private void quickSort(List<Integer> list, int lo, int hi) {
        if (lo > hi) {
            return;
        }

        int less = lo;
        int i = lo;
        int great = hi;
        while (i <= great) {
            if (list.get(i) < list.get(great)) {
                int temp = list.get(less);
                list.remove(less);
                list.add(less, list.get(i));
                list.remove(i);
                list.add(i, temp);
                i++;
                less++;
            } else if (list.get(i) > list.get(great)) {
                int temp = list.get(great);
                list.remove(great);
                list.add(great, list.get(i));
                list.remove(i);
                list.add(i, temp);
                great--;
            } else {
                i++;
            }
        }

        quickSort(list, lo, less - 1);
        quickSort(list, great + 1, hi);
    }

    public static void main(String[] args) {
        int[] data = new int[]{15, 16156, 165165184, 61, 561, 651, 654, 984, 684, 65, 165, 1865, 416, 1, 651, 651, 65, -489};
        new BucketSort().sort(data);
        System.out.println(Arrays.toString(data));
    }

}
