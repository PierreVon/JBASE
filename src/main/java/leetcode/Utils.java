package leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class Utils {

    private static <T> void travel(T[][] t) {
        for (T[] sub : t) {
            for (T fin : sub) {
                System.out.printf("%s, ", fin);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Integer[][] in = new Integer[][]{
                {1,3},
                {4,9},
                {2,6}
        };
        Arrays.sort(in, new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return o1[0]-o2[0];
            }
        });
        travel(in);
    }
}
