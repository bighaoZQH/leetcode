package com.bighao.array;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/2/3 18:02
 */
public class Test01 {

    public static int for1(int[] arr1, int[] arr2) {
        int sum = 0;
        for (int i = 0; i < 1000000000; i++) {
            sum += arr1[i];
            sum += arr2[i];
        }
        return sum;
    }

    public static int for2(int[] arr1, int[] arr2) {
        int sum = 0;
        for (int i = 0; i < 1000000000; i++) {
            sum += arr1[i];
        }
        for (int i = 0; i < 1000000000; i++) {
            sum += arr2[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            long l1 = System.currentTimeMillis();
            for1(arr1, arr2);
            System.out.println(System.currentTimeMillis() - l1); //2020

            long l2 = System.currentTimeMillis();
            for2(arr1, arr2);
            System.out.println(System.currentTimeMillis() - l2); //1749
            System.out.println("\n");
        }
    }

    static int[] arr1 = new int[1000000000];
    static int[] arr2 = new int[1000000000];
    static {
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = i;
            arr2[i] = i;
        }
        System.out.println("end...");
    }
}
