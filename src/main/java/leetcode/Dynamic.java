package leetcode;

public class Dynamic {

    // 面试题 08.11. 硬币
    // 硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，
    // 编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)
    // 链接：https://leetcode-cn.com/problems/coin-lcci/

    // #1：使用回溯法，容易溢栈，程序超时
    static int base = 1000000007;
    static int ans = 0;
    static int[] money = new int[]{25, 10, 5, 1};

    public int waysToChange(int n) {
        if (n < 1) return 0;
        backtracing(n, 0);
        return ans;
    }

    private void backtracing(int n, int last) {
        if (n < 0) return;
        if (n == 0) {
            ans = ++ans % base;
            return;
        }
        for (int i = last; i < 4; i++) {
            backtracing(n - money[i], i);
        }
    }

    // #2：动态规划，O(n)=n, S(n)=n
    public static int waysToChangeDP(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 0; i < 4; i++) {
            int coin = money[i];
            for (int j = coin; j <= n; j++) {
                dp[j] = (dp[j] + dp[j - coin]) % base;
            }
            for (int a : dp) {
                System.out.printf("%d, ", a);
            }
            System.out.println();
        }
        return dp[n];
    }

    public static int mincostTickets(int[] days, int[] costs) {
        if (days.length == 0) return 0;
        if (days.length == 1) return costs[0];
        int n = days[days.length - 1];
        int[] pay = new int[n + 1];
        int j = 0;
        for (int i = 0; i <= n; i++) {
            if (i < days[0]) pay[i] = 0;
            else if (i == days[j]) {
                j++;
                pay[i] = pay[i - 1] + costs[0];
                if (i - 7 >= 0) pay[i] = Math.min(pay[i], pay[i - 7] + costs[1]);
                if (i - 7 >= 30) pay[i] = Math.min(pay[i], pay[i - 30] + costs[2]);
            } else {
                pay[i] = pay[i - 1];
            }
        }
        return pay[n];
    }

    // 221. 最大正方形
    // 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
    // 链接：https://leetcode-cn.com/problems/maximal-square/
    public static int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int max = 0;
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) dp[i][j] = 1;
                    else dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    max = Math.max(dp[i][j], max);
                }
            }
        }
        return max * max;
    }

    public static int maxSubArray(int[] nums) {
        int pre = 0, max = nums[0];
        for (int x : nums) {
            pre = Math.max(x, pre + x);
            max = Math.max(max, pre);
        }
        return max;
    }

    // 121. 买卖股票的最佳时机
    // 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
    // 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
    // 注意：你不能在买入股票前卖出股票。
    // 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
    public static int maxProfit(int[] prices) {
        int n = prices.length;
        int max = 0, pro = 0;
        for (int i = 1; i < n; i++) {
            pro = Math.max(0, pro + prices[i] - prices[i - 1]);
            max = Math.max(max, pro);
        }
        return max;
    }


    // 300. 最长上升子序列
    // 给定一个无序的整数数组，找到其中最长上升子序列的长度。
    // 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence/
    public static int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int[] dp = new int[n];
        dp[0] = 0;
        int realMax = 0;
        for (int i = 1; i < n; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, dp[j] + 1);
                }
            }
            dp[i] = max;
            realMax = Math.max(dp[i], realMax);
        }
        for (int x : dp) {
            System.out.printf("%d, ", x);
        }
        System.out.println();
        return realMax + 1;
    }

    // *
    // 152. 乘积最大子数组
    // 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（
    // 该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
    // 链接：https://leetcode-cn.com/problems/maximum-product-subarray/
    public static int maxProduct(int[] nums) {
        int max = nums[0], min = nums[0], res = nums[0];
        int m, n;
        System.out.println("-----");
        for (int i = 1; i < nums.length; i++) {
            m = max;
            n = min;
            max = Math.max(m * nums[i], Math.max(nums[i], n * nums[i]));
            min = Math.min(n * nums[i], Math.min(nums[i], m * nums[i]));
            res = Math.max(max, res);
            System.out.printf("%d,%d,%d\n", min, max, res);
        }
        return res;
    }

    // 518. 零钱兑换 II
    // 给定不同面额的硬币和一个总金额。
    // 写出函数来计算可以凑成总金额的硬币组合数。
    // 假设每一种面额的硬币有无限个。
    public static int change(int amount, int[] coins) {
        if (coins.length == 0 || amount < 1) return 0;
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i < amount + 1; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{2, 3, -2, 4}));
        System.out.println(maxProduct(new int[]{-2, 0, -1}));
        System.out.println(maxProduct(new int[]{-2, 0, 4, -1, -3, -2, 2}));
        System.out.println(maxProduct(new int[]{-2, 4, -1, -3, -2, 2, 0, 1, 2}));
        System.out.println(maxProduct(new int[]{3, -1, 4}));
        System.out.println();
        System.out.println(change(5, new int[]{1, 2, 5}));
        System.out.println(change(10, new int[]{1, 2, 5}));
    }
}
