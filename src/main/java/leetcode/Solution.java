package leetcode;

import com.sun.source.tree.BreakTree;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;

import java.lang.reflect.Array;
import java.util.*;

public class Solution {


    public static List<List<String>> permute(int n) {
        if (n < 1) return null;
        List<String> init = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            init.add(String.valueOf(i));
        }
        List<List<String>> l = new LinkedList<>();
        permute(init, l, 0);
        return l;
    }

    public static void permute(List<String> s, List<List<String>> l, int present) {
        if (present == s.size()) {
            l.add(new LinkedList<>(s));
        } else {
            for (int i = present; i < s.size(); i++) {
                Collections.swap(s, present, i);
                permute(s, l, present + 1);
                Collections.swap(s, present, i);
            }
        }
    }

    public static int bag(int[] weight, int[] volume, int M) {
        if (M == 0 || weight == null || weight.length == 0) return 0;
        int N = weight.length;
        int[][] dp = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (j >= volume[i - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - volume[i - 1]] + weight[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        for (int[] i : dp) {
            for (int j : i) {
                System.out.printf("%d ", j);
            }
            System.out.println();
        }
        return dp[N][M];
    }

    public static int bag1D(int[] weight, int[] volume, int M) {
        if (M == 0 || weight == null || weight.length == 0) return 0;
        int N = weight.length;
        int[] dp = new int[M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = M; j >= volume[i - 1]; j--) {
                dp[j] = Math.max(dp[j], dp[j - volume[i - 1]] + weight[i - 1]);
            }
            for (int j : dp) {
                System.out.printf("%d ", j);
            }
            System.out.println();
        }
        return dp[M];
    }

    public static int topK(int[] nums, int k) {
        Queue<Integer> q = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for (int i = 0; i < nums.length; i++) {
            if (i < k) q.add(nums[i]);
            else {
                if (nums[i] > q.peek()) {
                    q.poll();
                    q.add(nums[i]);
                }
            }
        }
        return q.peek();
    }

    public static List<Integer> fib(int n) {
        List<Integer> res = new LinkedList<>();
        res.add(0);
        int tmp;
        if (n < 1) return res;
        else {
            int a = 0, b = 1;
            while (n-- > 0) {
                res.add(b);
                tmp = b;
                b = a + b;
                a = tmp;
            }
        }
        return res;
    }

    public static boolean isPalindrome(String s) {
        if (s == null) return true;
        int i = 0, j = s.length() - 1;
        s = s.toLowerCase();
        System.out.println(s);
        boolean result = true;
        while (i < j) {
            while (i < j && !((s.charAt(i) >= 'a' && s.charAt(i) <= 'z') || (s.charAt(i) >= '0' && s.charAt(i) <= '9')))
                i++;
            while (i < j && !((s.charAt(j) >= 'a' && s.charAt(j) <= 'z') || (s.charAt(j) >= '0' && s.charAt(j) <= '9')))
                j--;
            System.out.printf("%d, %d, %s, %s\n", i, j, s.charAt(i), s.charAt(j));
            if (i < j) {
                if (s.charAt(i) != s.charAt(j)) {
                    result = false;
                    break;
                }
            }
            i++;
            j--;
        }
        return result;
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        if (wordDict == null || s.length() == 0) return false;
        Map<Character, List<String>> map = new HashMap<>();
        for (String word : wordDict) {
            if (map.get(word.charAt(0)) == null) {
                List<String> l = new ArrayList<>();
                l.add(word);
                map.put(word.charAt(0), l);
            } else {
                List<String> l = map.get(word.charAt(0));
                l.add(word);
                map.put(word.charAt(0), l);
            }
        }
        return serachBreak(s, map);
    }

    private static boolean serachBreak(String s, Map<Character, List<String>> map) {
        if (s == null) return true;
        if (s.length() == 0) return true;
        if (map.get(s.charAt(0)) == null) return false;
        else {
            List<String> list = map.get(s.charAt(0));
            for (String str : list) {
                int size = str.length();
                if (s.length() >= size && s.substring(0, size).equals(str)) {
                    if (serachBreak(s.substring(size), map)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0) return 0;
        int min = Integer.MAX_VALUE;
        int tmp = 0;
        int i = 0, j = 0;
        while (i < nums.length) {
            tmp += nums[i];
            while (tmp >= s) {
                min = Math.min(min, i - j + 1);
                tmp -= nums[j];
                j++;
            }
            i++;
        }
        return min;
    }

    public static int findLength(int[] A, int[] B) {
        if (A == null || B == null) return 0;
        if (A.length == 0 || B.length == 0) return 0;
        int[][] dp = new int[A.length][B.length];
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (i == 0 || j == 0) {
                    if (A[i] == B[j]) {
                        dp[i][j] = 1;
                        max = Math.max(max, 1);
                    }
                } else {
                    if (A[i] == B[j]) {
                        dp[i][j] = 1 + dp[i - 1][j - 1];
                        max = Math.max(max, dp[i][j]);
                    }
                }
            }
        }
        print2Dint(dp);
        return max;
    }

    public static void print2Dint(int[][] arr) {
        for (int[] i : arr) {
            for (int j : i) {
                System.out.printf("%d, ", j);
            }
            System.out.println();
        }
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        return uniquePathsWithObstacles(obstacleGrid, 0, 0);
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid, int x, int y) {
        int n = obstacleGrid.length - 1;
        int m = obstacleGrid[0].length - 1;
        if (x > n || y > m) return 0;
        if (obstacleGrid[x][y] == 1) return 0;
        if (x == n && y == m) return 1;
        else {
            return uniquePathsWithObstacles(obstacleGrid, x + 1, y) + uniquePathsWithObstacles(obstacleGrid, x, y + 1);
        }
    }

    public int[] divingBoard(int shorter, int longer, int k) {
        if (k < 1 || longer < shorter) return new int[0];
        int total = k * shorter;
        int diff = longer - shorter;
        if (diff == 0) {
            return new int[]{total};
        }
        int[] res = new int[k + 1];
        res[0] = total;
        for (int i = 1; i <= k; i++) {
            total += diff;
            res[i] = total;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        List<List<String>> l = permute(3);
        for (List<String> ll : l) System.out.println(ll);

        System.out.println(bag(new int[]{2, 3, 4, 5}, new int[]{3, 2, 4, 3}, 6));
        System.out.println(bag1D(new int[]{2, 3, 4, 5}, new int[]{3, 2, 4, 3}, 6));

        System.out.println(topK(new int[]{3, 4, 5, 9, 2, 5, 7, 1, 2, 6}, 4));

        for (Integer i : fib(5)) System.out.println(i);

        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));


        System.out.println(wordBreak("leetcode", Arrays.asList("leet", "code")));

        System.out.println(wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));

        System.out.println(findLength(new int[]{0, 0, 0, 0, 1}, new int[]{1, 0, 0, 0, 0}));
    }
}
