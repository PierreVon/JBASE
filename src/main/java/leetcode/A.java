package leetcode;

public class A {

    public static int testt(int[] nums) {
        if (nums.length == 0) return 0;
        int max = nums[0];
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (sum + nums[i] <= nums[i]) sum = nums[i];
            else sum += nums[i];
            max = Math.max(sum, max);
        }
        return max;
    }


    public static void main(String[] args) {
        System.out.println(testt(new int[]{1, -2, 3, -1, 2}));
        System.out.println(testt(new int[]{-3}));
    }
}
