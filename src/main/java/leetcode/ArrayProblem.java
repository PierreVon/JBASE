package leetcode;

import java.util.*;

public class ArrayProblem {
    // 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> set = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int head = i + 1, tail = nums.length - 1;
            while (head < tail) {
                int sum = -(nums[head] + nums[tail]);
                if (nums[i] == sum) {
                    set.add(Arrays.asList(nums[i], nums[head], nums[tail]));
                }
                if (sum <= nums[i]) tail--;
                else head++;
            }
        }
        return new ArrayList<>(set);
    }

    // 给定一个未经排序的整数数组，找到最长且连续的的递增序列。
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = 1;
        int tmp = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] > 0) {
                tmp++;
                max = Math.max(tmp, max);
            } else {
                tmp = 1;
            }
        }
        return max;
    }

    // 给定一个未排序的整数数组，找出最长连续序列的长度。
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums) set.add(i);
        int longest = 0;
        int tmp = 0, n;
        for (int i : nums) {
            if (!set.contains(i - 1)) {
                tmp = 1;
                n = i + 1;
                while (set.contains(n)) {
                    tmp++;
                    n++;
                }
            }
            longest = Math.max(tmp, longest);
        }
        return longest;
    }

    // 数组中的第K个最大元素
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length < k) return 0;
        Queue<Integer> q = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for (int i : nums) {
            if (q.size() < k) q.add(i);
            else {
                if (i > q.peek()) {
                    q.poll();
                    q.add(i);
                }
            }
        }
        return q.peek();
    }

    public String getPermutation(int n, int k) {
        String str = "";
        for (int i = 1; i <= n; i++) str += i;
        int count = 0;
        return "";
    }

    // 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。
    public int findCircleNum(int[][] M) {
        if (M == null) return 0;
        boolean[] stu = new boolean[M.length];
        int count = 0;
        Queue<Integer> q = new LinkedList<>();
        int tmp;
        for (int i = 0; i < M.length; i++) {
            if (!stu[i]) {
                count++;
                q.add(i);
                while (!q.isEmpty()) {
                    tmp = q.poll();
                    stu[tmp] = true;
                    for (int j = 0; j < M.length; j++) {
                        if (M[tmp][j] == 1) {
                            if (!stu[j]) q.add(j);
                        }
                    }
                }
            }
        }
        return count;
    }

    // 给出一个区间的集合，请合并所有重叠的区间。
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        List<int[]> res = new ArrayList<>();
        int begin = -1;
        int end = -1;
        for (int[] l : intervals) {
            if (begin == -1) {
                begin = l[0];
                end = l[1];
            } else {
                if (end >= l[0]) {
                    end = Math.max(end, l[1]);
                } else {
                    res.add(new int[]{begin, end});
                    begin = l[0];
                    end = l[1];
                }
            }
        }
        if (begin != -1) {
            res.add(new int[]{begin, end});
        }
        return res.toArray(new int[0][]);
    }

    // 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
    public int trap(int[] height) {
        return 0;
    }

    // 300. 最长上升子序列
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int max, realmax = 0;
        for (int i = 0; i < nums.length; i++) {
            max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, dp[j] + 1);
                }
            }
            dp[i] = max;
            realmax = Math.max(max, realmax);
        }
        return realmax;
    }

    public static void main(String[] args) {
        ArrayProblem s = new ArrayProblem();
        System.out.println(s.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));

        System.out.println();
        System.out.println(s.findLengthOfLCIS(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println(s.findLengthOfLCIS(new int[]{1, 3, 5, 4, 2, 3, 4, 5}));
        System.out.println(s.findLengthOfLCIS(new int[]{1, 3, 5, 4, 7}));
        System.out.println(s.findLengthOfLCIS(new int[]{2, 2, 2, 2, 2}));

        System.out.println();
        System.out.println(s.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));

        System.out.println();
        System.out.println(s.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
        System.out.println(s.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));

        System.out.println();
        System.out.println(s.findCircleNum(new int[][]{
                {1, 1, 0},
                {1, 1, 1},
                {0, 1, 1}
        }));
        System.out.println(s.findCircleNum(new int[][]{
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        }));

        System.out.println();
        s.merge(new int[][]{
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18}
        });

        s.merge(new int[][]{
                {1, 4},
                {2, 3}
        });

        s.merge(new int[][]{
                {1, 4},
                {1, 5}
        });
    }
}
