package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 199. 二叉树的右视图
    // 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
    // 链接：https://leetcode-cn.com/problems/binary-tree-right-side-view/
    public static List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        queue.add(root);
        queue.add(null);
        int last = root.val;
        TreeNode tmp;
        while (!queue.isEmpty()) {
            tmp = queue.poll();
            if (tmp == null) {
                list.add(last);
                if (!queue.isEmpty()) queue.add(null);
            } else {
                last = tmp.val;
                if (tmp.left != null) queue.add(tmp.left);
                if (tmp.right != null) queue.add(tmp.right);
            }
        }
        return list;
    }

    // 542. 01 矩阵
    // 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
    // 两个相邻元素间的距离为 1 。
    // 示例 2:
    // 输入:
    // 0 0 0
    // 0 1 0
    // 1 1 1
    //
    // 输出:
    // 0 0 0
    // 0 1 0
    // 1 2 1
    // 链接：https://leetcode-cn.com/problems/01-matrix/
    public static int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return matrix;
        int N = matrix.length, M = matrix[0].length;
        Queue<Integer[]> q = new LinkedList<>();
        boolean[][] flag = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] == 0) {
                    flag[i][j] = true;
                    q.add(new Integer[]{i, j});
                }
            }
        }
        int[][] dist = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int r = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            System.out.println(size);
            r++;
            for (int i = 0; i < size; i++) {
                Integer[] at = q.poll();
                if(at==null) continue;
                for (int j = 0; j < 4; j++) {
                    int x = at[0] + dist[j][0];
                    int y = at[1] + dist[j][1];
                    if (x < 0 || x >= N || y < 0 || y >= M || flag[x][y])
                        continue;
                    matrix[x][y] = r;
                    flag[x][y]=true;
                    q.add(new Integer[]{x, y});
                }
            }
        }
        return matrix;
    }

    // 面试题13. 机器人的运动范围
    // 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
    // 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、
    // 上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的
    // 数位之和大于k的格子。例如，当k为18时，机器人能够进入方格
    // [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，
    // 因为3+5+3+8=19。请问该机器人能够到达多少个格子？
    // 链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/
    public static int movingCount(int m, int n, int k) {
        int count = 0;
        if (m * n <= 0) return count;
        if (k == 0) return 1;
        int[][] val = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                val[i][j] = calc(i) + calc(j);
            }
        }
        boolean[][] flag = new boolean[m][n];
        Queue<Integer[]> queue = new LinkedList<>();
        queue.add(new Integer[]{0, 0});
        flag[0][0] = true;
        count++;
        Integer[] tmp;
        int[][] dict = new int[][]{{1, 0}, {0, 1}};
        while (!queue.isEmpty()) {
            tmp = queue.poll();
            if (tmp == null) continue;
            for (int i = 0; i < 2; i++) {
                int x = tmp[0] + dict[i][0];
                int y = tmp[1] + dict[i][1];
                if (x < 0 || x >= m || y < 0 || y >= n) continue;
                if (val[x][y] <= k && !flag[x][y]) {
                    flag[x][y] = true;
                    count++;
                    queue.add(new Integer[]{x, y});
                }
            }
        }
        return count;
    }

    public static int calc(int x) {
        int sum = 0;
        while (x != 0) {
            sum += x % 10;
            x /= 10;
        }
        return sum;
    }

}
