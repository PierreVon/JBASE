package leetcode;

public class Dichotomy {

    // 50. Pow(x, n)
    // 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
    // 链接：https://leetcode-cn.com/problems/powx-n/
    // 将乘法二分，有效减少运算
    public static double myPow(double x, int n) {
        long N = n;
        return N > 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    public static double quickMul(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }

}
