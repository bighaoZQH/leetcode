package com.bighao.array;

/**
 * @version 1.0
 * @author: bighao周启豪
 * @date 2020/11/29 18:57
 * <p>
 * <p>
 * https://leetcode-cn.com/problems/climbing-stairs/
 * <p>
 * 计算机根本就是只能做，if-else，for loop，recursion(递归)
 * 懵逼的时候怎么办？
 * 暴力能否解？基本情况？
 * 找 最近 重复子问题
 * <p>
 * 扩展：
 * 2020-9-6 字节跳动笔试题第一题就是这个题目。 但是题目做了修改（限制条件做了修改，加了第二个限制条件）：
 * 1、每次你可以爬 1 或 2 个台阶。 2、不能连续跳两个台阶。
 * （个人理解为这次跳了两个台阶，下次不能再跳两个台阶）
 * public int climbStairs(int n) {
 * // 初始值
 * if (n <= 2) return n;
 * // f2 = f(i-2)
 * int f2 = 1;
 * // f1 = f(i-1)
 * int f1 = 2;
 * int res = 0;
 * for (int i = 2; i < n; i++) {
 * // 状态转移方程式:f(i)=f(i-1)+f(i-2)
 * res = f1 + f2;
 * // 保存下一轮 f(i-1) 和 f(i-2) 的值,重复利用
 * f2 = f1;
 * f1 = res;
 * }
 * return res;
 * }
 */
public class CodeEasyArray70 {

    /**
     * 爬楼梯
     * 1: 1
     * 2: 2
     * 3: 3
     * 4: 5 当n为4，最后一步的情况下，要么你人在第2阶跨到n，要么你人在第3阶跨到n
     * 5: 8
     * 方法1：动态规划 - 斐波那契 - 滚动数组
     * 因此递推出公式 f(n) = f(n-2) + f(n-1)
     */
    public static int climbStairs(int n) {
        int q, w = 0, e = 1;
        for (int i = 1; i <= n; i++) {
            q = w;
            w = e;
            e = q + w;
        }
        return e;
    }

    /**
     * 方法2.递归 - 斐波那契 数字大一点就跑不出来。。。
     * n=1，2，3的时候 结果就是n，n>=4的时候用公式 f(n) = f(n-2) + f(n-1) 进行递归
     */
    public static int climbStairs2(int n) {
        /*if (n < 4) {
            return n;
        }
        return climbStairs2(n - 2) + climbStairs2(n - 1);*/
        return n < 4 ? n : climbStairs2(n - 2) + climbStairs2(n - 1);
    }


    public static void main(String[] args) {
        System.out.println(climbStairs2(7));
    }

}
