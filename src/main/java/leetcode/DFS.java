package leetcode;

public class DFS {
    // 200. 岛屿数量
    // 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
    // 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
    // 此外，你可以假设该网格的四条边均被水包围。
    // 链接：https://leetcode-cn.com/problems/number-of-islands/
    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        final int N = grid.length, M = grid[0].length;
        boolean[][] flag = new boolean[N][M];
        int num = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!flag[i][j]) {
                    if (grid[i][j] == '1') {
                        num++;
                        numIslandsDFS(grid, flag, i, j, N, M);
                    } else flag[i][j] = true;
                }
            }
        }
        return num;
    }

    private static void numIslandsDFS(char[][] grid, boolean[][] flag, int i, int j, int N, int M) {
        if (i < 0 || i >= N || j < 0 || j >= M)
            return;
        if (grid[i][j] == '1' && !flag[i][j]) {
            flag[i][j] = true;
            numIslandsDFS(grid, flag, i + 1, j, N, M);
            numIslandsDFS(grid, flag, i, j + 1, N, M);
            numIslandsDFS(grid, flag, i - 1, j, N, M);
            numIslandsDFS(grid, flag, i, j - 1, N, M);
        }
    }

    // 面试题13. 机器人的运动范围
    // 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
    // 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、
    // 上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的
    // 数位之和大于k的格子。例如，当k为18时，机器人能够进入方格
    // [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，
    // 因为3+5+3+8=19。请问该机器人能够到达多少个格子？
    // 链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/
    public int movingCount(int m, int n, int k) {
        return dfs(0, 0, 0, 0, m, n, k, new boolean[m][n]);
    }

    private int dfs(int i, int j, int si, int sj, int m, int n, int k, boolean[][] visited) {
        if (i >= m || j >= n || si + sj > k || visited[i][j])
            return 0;
        visited[i][j] = true;

        return 1 + dfs(i + 1, j, (i + 1) % 10 == 0 ? si - 8 : si + 1, sj, m, n, k, visited)
                + dfs(i, j + 1, si, (j + 1) % 10 == 0 ? sj - 8 : sj + 1, m, n, k, visited);
    }

    // 695. 岛屿的最大面积
    // 给定一个包含了一些 0 和 1 的非空二维数组 grid 。
    // 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的
    // 「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假
    // 设 grid 的四个边缘都被 0（代表水）包围着。
    // 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
    // 链接：https://leetcode-cn.com/problems/max-area-of-island/
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int N = grid.length;
        int M = grid[0].length;
        boolean[][] flag = new boolean[N][M];
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!flag[i][j] && grid[i][j] == 1) max = Math.max(max, area(grid, flag, i, j));
                else flag[i][j] = true;
            }
        }
        return max;
    }

    public int area(int[][] grid, boolean[][] flag, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return 0;
        if (!flag[i][j] && grid[i][j] == 1) {
            flag[i][j] = true;
            int num = 1;
            num += area(grid, flag, i + 1, j);
            num += area(grid, flag, i - 1, j);
            num += area(grid, flag, i, j - 1);
            num += area(grid, flag, i, j + 1);
            return num;
        } else {
            flag[i][j] = true;
            return 0;
        }
    }

    public int translateNum(int num) {
        String str = String.valueOf(num);
        return deepTravel(str);
    }

    public int deepTravel(String str) {
        if (str.length() <= 1) return 1;
        else {
            if (!str.startsWith("0") && Integer.parseInt(str.substring(0, 2)) < 26) {
                return deepTravel(str.substring(1)) + deepTravel(str.substring(2));
            } else return deepTravel(str.substring(1));
        }
    }

    public static void main(String[] args) {
        System.out.println(numIslands(new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '0'}

        }));

        System.out.println(numIslands(null));
        System.out.println(numIslands(new char[][]{}));
    }
}
