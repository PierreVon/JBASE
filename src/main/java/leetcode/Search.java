package leetcode;

public class Search {
    public static int search(int[] nums, int target) {
        int n = nums.length;
        int i = 0, j = n - 1;
        if (n == 1) return nums[0] == target ? 0 : -1;
        while (i <= j) {
            int mid = (i + j) >> 1;
            if (nums[mid] == target) return mid;
            // 4,5,6,7,8,9,10,1,2,3
            // 8,9,10,1,2,3,4,5,6,7
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) j = j - 1;
                else i = i + 1;
            } else {
                if (nums[mid] < target && target <= nums[n-1]) i = mid + 1;
                else j = j - 1;
            }
        }
        return -1;
    }
}
