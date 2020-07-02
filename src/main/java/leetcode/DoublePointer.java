package leetcode;

public class DoublePointer {
    // 11. 盛最多水的容器
    // 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
    // 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
    // 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
    // 链接：https://leetcode-cn.com/problems/container-with-most-water
    public static int maxArea(int[] height) {
        int max = 0;
//        for(int i=0;i<height.length;i++){
//            for(int j=i+1;j<height.length;j++){
//                max=Math.max(max, Math.min(height[i], height[j])*(j-i));
//            }
//        }
        int i = 0, j = height.length - 1;
        while (i < j) {
            max = Math.max(max, Math.min(height[i], height[j]) * (j - i));
            if (height[i] < height[j]) i++;
            else j--;
        }

        return max;
    }

    // 42. 接雨水
    // 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
    // 链接：https://leetcode-cn.com/problems/trapping-rain-water
    public static int trap(int[] height) {
        int capacity = 0;
        int i = 0, j = height.length - 1;
        while (i < j) {
            if (height[i] < height[j]) {
                int init = height[i];
                while (i < j && height[i] <= height[j]) {
                    if (height[i] < init) capacity += init - height[i];
                    else init = height[i];
                    i++;
                }
            } else {
                int init = height[j];
                while (i < j && height[j] <= height[i]) {
                    if (height[j] < init) capacity += init - height[j];
                    else init = height[j];
                    j--;
                }
            }
        }
        return capacity;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(nums));
    }
}
