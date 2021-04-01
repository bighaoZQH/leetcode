package com.bighao.algorithm.recursion;


import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/generate-parentheses/
 * <p>
 * https://leetcode-cn.com/problems/generate-parentheses/solution/pei-yang-chou-xiang-si-wei-hui-su-jie-fa-7dwu/
 *
 *
 * 回溯、贪心和动态规划三类算法思想是层层递进，环环相扣的，如果想学习好的话，就应该放在一起系统的学习，否则，你很难学会他们
 *
 * 如果你能系统的完成下面的 36 道算法面试题的话，那么对于回溯、贪心和动态规划的问题，就会很容易的解答了：
 *
 * 回溯算法思想
 * leetcode 112 号算法题：路径总和
 * leetcode 113 号算法题：路径总和 II
 * leetcode 46 号算法题：全排列
 * leetcode 47 号算法题：全排列 II
 * leetcode 77 号算法题：组合
 * leetcode 39 号算法题：组合总和
 * leetcode 40 号算法题：组合总和 Ⅱ
 * leetcode 216 号算法题：组合总和 Ⅲ
 * leetcode 78 号算法题：子集
 * leetcode 90 号算法题：子集Ⅱ
 * leetcode 17 号算法题：电话号码的字母组合
 * leetcode 93 号算法题：复原 IP 地址
 * leetcode 22 号算法题：括号生成
 * leetcode 51 号算法题：N 皇后
 * leetcode 37 号算法题：数独问题
 * 贪心算法思想
 * leetcode 455 号算法题：分发饼干
 * leetcode 322 号算法题：零钱兑换
 * leetcode 45 号算法题：跳跃游戏 Ⅱ
 * leetcode 1578 号算法题：避免重复字母的最小删除成本
 * leetcode 402 号算法题：移掉K位数字
 * 动态规划算法思想
 * leetcode 509 号算法题：斐波那契数
 * leetcode 322 号算法题：零钱兑换
 * leetcode 64 号算法题：最小路径和
 * leetcode 53 号算法题：最大子数组之和
 * leetcode 647 号算法题：回文子串
 * leetcode 5 号算法题：最长回文串
 * leetcode 131 号算法题：分割回文串
 * leetcode 516 号算法题：最长回文子序列
 * leetcode 300 号算法题：最长上升子序列
 * leetcode 1143 号算法题：最长公共子序列
 * leetcode 72 号算法题：编辑距离
 * leetcode 44 号算法题：通配符匹配
 * leetcode 10 号算法题：正则表达式匹配
 * leetcode 486 号算法题：预测赢家
 * leetcode 877 号算法题：石子游戏
 * 0-1 背包问题
 * 你可以按照上面的顺序来刷这些题目，注意，一定要按照 回溯，然后 贪心，最后才 动态规划 的顺序来刷题，我相信你能系统的学会回溯、贪心和动态规划
 *
 * 如果你觉得刷这些题有困难的话，你也可以看这里：老汤视频讲解：回溯、贪心和动态规划
 * https://ke.qq.com/course/3139267
 */
public class 括号生成Medium22 {

    /**
     * 输入：n = 3
     * 输出：["((()))","(()())","(())()","()(())","()()()"]
     *
     * 递归 + 回溯 + 剪枝
     */
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n <= 0) {
            return result;
        }
        dfs(n, "", result, 0, 0);
        return result;
    }

    /**
     * 暴力求出所有结果集，看结果发现当)括号个数 > (括号个数时，进行剪枝即可
     * 以及 当 (括号的数量 > n的话 也要进行剪枝
     */
    private static void dfs(int n, String path, List<String> res) {
        if (path.length() == n * 2) {
            res.add(path);
            return;
        }
        dfs(n, path + "(", res);
        dfs(n, path + ")", res);
    }

    private static void dfs(int n, String path, List<String> res, int open, int close) {
        // 剪枝
        if (open > n || close > open) {
            return;
        }
        if (path.length() == n * 2) {
            res.add(path);
            return;
        }
        dfs(n, path + "(", res, open + 1, close);
        dfs(n, path + ")", res, open, close + 1);
    }

    // 第二种剪枝方式
    private static void dfs2(int n, String path, List<String> res, int open, int close) {
        if (path.length() == n * 2) {
            res.add(path);
            return;
        }
        if (open < n) {
            dfs(n, path + "(", res, open + 1, close);
        }
        if (close < open) {
            dfs(n, path + ")", res, open, close + 1);
        }
    }

    public static void main(String[] args) {
        List<String> res = generateParenthesis(3);
        res.forEach(System.out::println);
    }

}
