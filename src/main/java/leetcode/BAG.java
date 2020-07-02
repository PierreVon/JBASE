package leetcode;

public class BAG {

    public static int oneZeroBag(int N, int V, int[] weights, int[] volumns) {
        System.out.println("========oneZeroBag=========");
        int[][] costs = new int[N + 1][V + 1];
        for (int i = 1; i <= N; i++) {
            for (int v = 1; v <= V; v++) {
                if (v < volumns[i - 1])
                    costs[i][v] = costs[i - 1][v];
                else
                    costs[i][v] = Math.max(costs[i - 1][v], costs[i - 1][v - volumns[i - 1]] + weights[i - 1]);
            }
        }
        for (int[] x : costs) {
            for (int y : x)
                System.out.printf("%d, ", y);
            System.out.println();
        }
        return costs[N][V];
    }

    public static int oneZeroBagLess(int N, int V, int[] weights, int[] volumns) {
        System.out.println("========oneZeroBagLess=========");
        int[] costs = new int[V + 1];
        for (int i = 1; i <= N; i++) {
            for (int v = V; v >= volumns[i - 1]; v--) {
                costs[v] = Math.max(costs[v], costs[v - volumns[i - 1]] + weights[i - 1]);
            }
            for (int y : costs)
                System.out.printf("%d, ", y);
            System.out.println();
        }
        return costs[V];
    }

    public static int completeBagLess(int N, int V, int[] weights, int[] volumns) {
        System.out.println("========completeBagLess=========");
        int[] items = new int[N];
        for (int i = 0; i < N; i++)
            items[i] = V / volumns[i];
        int[] costs = new int[V + 1];
        for (int i = 1; i <= N; i++) {
            while (items[i - 1]-- > 0) {
                for (int v = V; v > 0; v--) {
                    if (v >= volumns[i - 1])
                        costs[v] = Math.max(costs[v], costs[v - volumns[i - 1]] + weights[i - 1]);
                }
                for (int y : costs)
                    System.out.printf("%d, ", y);
                System.out.println();
            }
        }
        return costs[V];
    }

    public static int multipleBagLess(int N, int V, int[] weights, int[] volumns, int[] items) {
        System.out.println("========completeBagLess=========");
        int[] costs = new int[V + 1];
        for (int i = 1; i <= N; i++) {
            while (items[i - 1]-- > 0) {
                for (int v = V; v > 0; v--) {
                    if (v >= volumns[i - 1])
                        costs[v] = Math.max(costs[v], costs[v - volumns[i - 1]] + weights[i - 1]);
                }
                for (int y : costs)
                    System.out.printf("%d, ", y);
                System.out.println();
            }
        }
        return costs[V];
    }

    public static void main(String[] args) {
        System.out.println(oneZeroBag(5, 10, new int[]{6, 3, 5, 4, 6}, new int[]{2, 2, 6, 5, 4}));
        System.out.println(oneZeroBag(3, 10, new int[]{5, 6, 6}, new int[]{6, 5, 4}));
        System.out.println(oneZeroBag(4, 8, new int[]{2, 3, 4, 5}, new int[]{3, 4, 5, 6}));
        System.out.println(oneZeroBag(5, 8, new int[]{48, 7, 40, 12, 8}, new int[]{6, 1, 5, 2, 1}));

        System.out.println(oneZeroBagLess(4, 8, new int[]{2, 3, 4, 5}, new int[]{3, 4, 5, 6}));
        System.out.println(oneZeroBagLess(5, 8, new int[]{48, 7, 40, 12, 8}, new int[]{6, 1, 5, 2, 1}));

        System.out.println(completeBagLess(4, 8, new int[]{2, 3, 4, 5}, new int[]{3, 4, 5, 6}));
        System.out.println(completeBagLess(5, 8, new int[]{48, 7, 40, 12, 8}, new int[]{6, 1, 5, 2, 1}));

        // compare 1-0 to complete
        System.out.println(oneZeroBag(3, 10, new int[]{5, 6, 6}, new int[]{6, 3, 2}));
        System.out.println(completeBagLess(3, 10, new int[]{5, 6, 6}, new int[]{6, 3, 2}));

    }
}
