package com.learn.algo.sort;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/7/29 23:33
 */
public class CountSort {

    /**
     * 时间复杂度 O(n + k)，k表示数组元素最大范围
     * 计数排序只能用在数据范围不大的场景中，
     * 如果数据范围k比排序的数据n大很多，
     * 就不适合用计数排序了。
     *
     * 空间复杂度是O(n),不是原地排序算法
     * 是稳定的排序算法
     *
     */
    public void sort(int[] data) {
        if (data == null || data.length < 2) {
            return;
        }

        // 1.找到最大值，用于初始化计数器
        int max = data[0];
        int min = data[0];
        for (int i = 1; i < data.length; i++) { //O(n)
            max = Math.max(max, data[i]);
            min = Math.min(min, data[i]);
        }

        // 初始化计数器
        int[] count = new int[max - min + 1];

        // 2.计数
        /*for (int i = 0; i < data.length; i++) {
            count[data[i]]++;
        }*/
        for (int val : data) { //O(n)
            count[val - min]++;
        }

        // 3.计数累加
        for (int i = 1; i < count.length; i++) {  //O(k) k表示0~max
            count[i] += count[i - 1];
        }

        // 4.计算每个元素在排序数组中的位置
        int[] output = new int[data.length];
        // 从data数组的后面往前遍历
        // 为什么要从后往前遍历?
        // 因为顺序遍历的话，相同的数据顺序会颠倒，因此要从后往前遍历，才能是一个稳定的排序
        for (int i = data.length - 1; i >= 0 ; i--) { //O(n)
            int val = data[i];
            // 这里k要减一是因为数组下标从0开始的，而计数是从1开始的
            int k = count[val - min] - 1;
            output[k] = val;
            // 计数-1
            count[val- min]--;

        }

        // 5.拷贝回原数组
        for (int i = 0; i < data.length; i++) { //O(n)
            data[i] = output[i];
        }
    }

    public static void main(String[] args) {

    }
}
