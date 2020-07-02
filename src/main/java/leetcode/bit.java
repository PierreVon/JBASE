package leetcode;

public class bit {
    // 面试题56 - I. 数组中数字出现的次数
    // 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。
    // 请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
    // 链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/
    public static int[] singleNumbers(int[] nums) {
        int sum = 0;
        for (int i : nums) sum ^= i;
        int bit = 1;
        while ((sum & bit) == 0) bit <<= 1;
        int a = 0, b = 0;
        for (int i : nums) {
            if ((bit & i) == 0) a ^= i;
            else b ^= i;
        }
        return new int[]{a, b};
    }

    // 若只有一个数字，仅一遍异或遍历即可
    public static int singleNumber(int[] nums) {
        int a = 0;
        for (int i : nums) a ^= i;
        return a;
    }
}
