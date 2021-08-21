package com.learn.heap;

import java.util.Random;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/8/21 23:49
 */
public class HeapTest {
    public static void main(String[] args) {
        Random random = new Random();

        int n = 10000;
        MaxHeap<Integer> heap = new MaxHeap<>();

        for (int i = 0; i < n; i++) {
            heap.add(random.nextInt());
        }

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = heap.removeMax();
        }

        for (int i = 1; i < n; i++) {
            if (arr[i - 1] < arr[i]) {
                throw new RuntimeException("error");
            }
        }

        System.out.println("success");
    }
}
