package com.bighao.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2021/7/26 23:17
 */
public class 合并区间Medium45 {

    public int[][] merge(int[][] intervals) {
        if (intervals == null) {
            return new int[][]{};
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        //  [[1,3],[2,6],[8,10],[15,18]]
        ArrayList<int[]> resList = new ArrayList();
        int[] temp = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if (temp[1] < cur[0]) {
                resList.add(temp);
                temp = cur;
            } else {
                temp[1] = Math.max(temp[1], cur[1]);
            }
        }
        resList.add(temp);

        return resList.toArray(new int[resList.size()][]);
    }


    public static void main(String[] args) {

    }

}
