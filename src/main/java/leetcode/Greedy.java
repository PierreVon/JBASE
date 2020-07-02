package leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class Greedy {

    // 55. 跳跃游戏
    // 给定一个非负整数数组，你最初位于数组的第一个位置。
    // 数组中的每个元素代表你在该位置可以跳跃的最大长度。
    // 判断你是否能够到达最后一个位置。
    // 链接：https://leetcode-cn.com/problems/jump-game/
    public static boolean canJump(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i <= max) {
                max = Math.max(max, i + nums[i]);
            } else return false;
        }
        return true;
    }


    // 45. 跳跃游戏 II
    // 给定一个非负整数数组，你最初位于数组的第一个位置。
    // 数组中的每个元素代表你在该位置可以跳跃的最大长度。
    // 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
    // 链接：https://leetcode-cn.com/problems/jump-game-ii/
    public static int jump(int[] nums) {
        int max = 0;
        int end = 0;
        int steps = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            max = Math.max(max, i + nums[i]);
            if (i == end) {
                end = max;
                steps++;
            }
        }
        return steps;
    }

    // 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int dp_i_0 = 0;
        int dp_i_1 = -prices[0];
        for (int i = 0; i < prices.length; i++) {
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, -prices[i]);
        }
        return dp_i_0;
    }

    // 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
    public int maxProfitMutiple(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int dp_i_0 = 0;
        int dp_i_1 = -prices[0];
        int tmp;
        for (int i = 0; i < prices.length; i++) {
            tmp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, tmp - prices[i]);
        }
        return dp_i_0;
    }

    // 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE, tmp = 0;
        for (int i : nums) {
            if (tmp + i < i) tmp = i;
            else tmp += i;
            max = Math.max(max, tmp);
        }
        return max;
    }

    // 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) return 0;
        int count = 0;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                } else {
                    return o1[0] - o2[0];
                }
            }
        });
        int max, realmax = 0;
        int[] dp = new int[envelopes.length];
        for (int i = 1; i < envelopes.length; i++) {
            max = 0;
            for (int j = 0; j < i; j++) {
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
                    max = Math.max(max, dp[j] + 1);
                }
            }
            dp[i] = max;
            realmax = Math.max(max, realmax);
        }
        return realmax + 1;
    }

    // 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            dp[i][0] = matrix[i][0] - '0';
            if (dp[i][0] == 1) max = 1;
        }
        for (int j = 0; j < matrix[0].length; j++) {
            dp[0][j] = matrix[0][j] - '0';
            if (dp[0][j] == 1) max = 1;
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == '0') dp[i][j] = 0;
                else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                    max = Math.max(dp[i][j], max);
                }
            }
        }
        for (int[] ints : dp) {
            for (int i : ints) System.out.printf("%d, ", i);
            System.out.println();
        }
        return max * max;
    }

    public static void main(String[] args) {
        Greedy g = new Greedy();
        System.out.println(g.maximalSquare(new char[][]{{'1'}}));
    }
}
