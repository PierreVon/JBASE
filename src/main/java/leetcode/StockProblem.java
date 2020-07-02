package leetcode;

public class StockProblem {

    // 122. 买卖股票的最佳时机 II
    // 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
    // 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
    // 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
    // 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
    public int maxProfitInfinity(int[] prices) {
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        int tmp;
        for (int i = 0; i < prices.length; i++) {
            tmp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, tmp - prices[i]);
        }
        return dp_i_0;
    }

    // 123. 买卖股票的最佳时机 III
    // 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
    // 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
    // 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
    // 连接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/
    public int maxProfitK2(int[] prices) {
        int[][][] dp = new int[prices.length][3][2];
        for (int i = 0; i < prices.length; i++) {
            for (int k = 2; k >= 1; k--) {
                if (i == 0) {
                    dp[0][k][0] = 0;
                    dp[0][k][1] = Integer.MIN_VALUE;
                } else {
                    dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                    dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
                }
            }
        }
        return dp[prices.length - 1][2][0];
    }

    public static void main(String[] args) {
        StockProblem sp = new StockProblem();
        System.out.println(sp.maxProfitK2(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
        System.out.println(sp.maxProfitK2(new int[]{1, 2, 3, 4, 5}));
    }
}
