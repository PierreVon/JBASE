package leetcode;

import java.util.*;

public class BackTracking {

    // 22. 括号生成
    // 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
    // 链接：https://leetcode-cn.com/problems/generate-parentheses/

    /**
     * 当n=10时，使用回溯法仅耗时14毫秒，而循环插入耗时703毫秒
     * 回溯法可以避免重复
     */
    public static List<String> generateParenthesis(int n) {
        HashSet<String> set = new HashSet<>();
        HashSet<String> tmpSet;
        List<String> list = new ArrayList<>();
        if (n == 0) return list;
        set.add("()");
        for (int i = 1; i < n; i++) {
            tmpSet = new HashSet<>();
            for (String str : set) {
                for (int k = 0; k <= str.length(); k++) {
                    String newStr = str.substring(0, k) + "(" + str.substring(k);
                    for (int j = k + 1; j <= newStr.length(); j++) {
                        String lastStr = newStr.substring(0, j) + ")" + newStr.substring(j);
                        tmpSet.add(lastStr);
                    }
                }
            }
            set = tmpSet;
        }
        if (!set.isEmpty()) {
            list.addAll(set);
        }
        return list;
    }

    public static List<String> generateParenthesis2(int n) {
        List<String> list = new ArrayList<>();
        backTracking(0, 0, list, new StringBuffer(), n);
        return list;
    }

    private static void backTracking(int left, int right, List<String> list, StringBuffer sb, int n) {
        if (left == n && right == n) {
            list.add(sb.toString());
            return;
        }
        if (left < n) {
            int now = sb.length();
            sb.append("(");
            backTracking(left + 1, right, list, sb, n);
            sb.delete(now, sb.length());
        }
        if (right < left) {
            sb.append(")");
            backTracking(left, right + 1, list, sb, n);
        }
    }

    // 46. 全排列
    // 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
    // 链接：https://leetcode-cn.com/problems/permutations
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lists = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        int n = nums.length;
        for (int num : nums)
            list.add(num);
        backtrackingPermute(lists, list, 0, n);
        return lists;
    }

    private static void backtrackingPermute(List<List<Integer>> lists, List<Integer> list,
                                            int present, int n) {
        if (present == n)
            lists.add(new ArrayList<Integer>(list));
        for (int i = present; i < n; i++) {
            Collections.swap(list, present, i);
            backtrackingPermute(lists, list, present + 1, n);
            Collections.swap(list, present, i);
        }
    }

    // 473. 火柴拼正方形
    // 还记得童话《卖火柴的小女孩》吗？现在，你知道小女孩有多少根火柴，
    // 请找出一种能使用所有火柴拼成一个正方形的方法。不能折断火柴，可以
    // 把火柴连接起来，并且每根火柴都要用到。
    // 输入为小女孩拥有火柴的数目，每根火柴用其长度表示。输出即为是否能用所有的火柴拼成正方形。
    // 链接：https://leetcode-cn.com/problems/matchsticks-to-square/
    public static boolean makesquare(int[] nums) {
        int n = nums.length;
        if (n < 4) return false;
        int total = 0;
        for (int i : nums) total += i;
        int width = total / 4;
        if (total != width * 4) return false;
        int[] square = new int[4];
        return makesqureBT(0, n, square, nums, width);
    }

    public static boolean makesqureBT(int i, int n, int[] square, int[] nums, int width) {
        if (i == n && square[0] == square[1] && square[1] == square[2] && square[2] == square[3])
            return true;
        for (int j = 0; j < 4; j++) {
            if (width < square[j] + nums[i]) continue;
            square[j] += nums[i];
            if (makesqureBT(i + 1, n, square, nums, width)) return true;
            square[j] -= nums[i];
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(makesquare(new int[]{1,1,2,2,2}));
    }
}
