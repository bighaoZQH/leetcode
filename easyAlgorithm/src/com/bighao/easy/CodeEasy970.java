package com.bighao.easy;

import java.util.*;

/**
 * @Author: bighao周启豪
 * @Date: 2020/4/17 17:29
 * @Version 1.0
 */
public class CodeEasy970 {

    public static void main(String[] args) {
        List<Integer> list = powerfulIntegers(2, 3, 10);
        System.out.println(Arrays.toString(list.toArray()));
    }

    public static List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> seen = new HashSet();
        for (int i = 0; i < 18 && Math.pow(x, i) <= bound; ++i)
            for (int j = 0; j < 18 && Math.pow(y, j) <= bound; ++j) {
                int v = (int) Math.pow(x, i) + (int) Math.pow(y, j);
                if (v <= bound)
                    seen.add(v);
            }

        return new ArrayList(seen);
    }


}