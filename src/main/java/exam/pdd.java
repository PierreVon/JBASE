package exam;

import java.util.*;

public class pdd {
    public static void main(String[] args) {
        func2();
    }

    public void func4(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int max = -1;
        for (int i = 0; i < nums.length; i++) {
            int less = gcd(nums[i], nums[i]);
            max = Math.max(max, less);
            for (int j = i + 1; j < nums.length; j++) {
                less = gcd(less, nums[j]);
                max = Math.max(max, less * (j - i + 1));
            }
        }
        System.out.println(max);
    }

    public static int gcd(int a, int b) {
        if (a < 2 || b < 2) {
            return 1;
        } else if (a == b) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    public static String help(int a, int b, int n) {
        if (n == 0) {
            if (a % 3 == 0) return "YES";
            else return "NO";
        }
        if (n == 1) {
            if (b % 3 == 0) return "YES";
            else return "NO";
        }
        int threshold = 10000;
        for (int i = 2; i <= n; i++) {
            int tmp = a;
            a = b;
            b += tmp;
            if (a > threshold) {
                a %= 3;
                b %= 3;
            }
        }
        if (b % 3 == 0) return "YES";
        else return "NO";
    }

    public static void func2() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n-- != 0) {
            int all = sc.nextInt();
            int[] nums = new int[all];
            for (int i = 0; i < all; i++) {
                nums[i] = sc.nextInt();
            }
            System.out.println(isSquare(all, nums));
        }
    }

    public static String isSquare(int all, int[] match) {
        int sum = 0;
        for (int i = 0; i < all; i++) {
            sum += match[i];
        }
        int width = sum / 4;
        int count = 0;
        Arrays.sort(match);
        int i = 0, j = match.length - 1;
        while (i <= j) {
            if (match[j] > width)
                return "NO";
            else if (match[j] == width) {
                count++;
                j--;
                continue;
            } else {
                int tmp = match[j];
                j--;
                while (i <= j && tmp + match[i] <= width) {
                    tmp += match[i];
                    i++;
                }
                if (tmp == width) {
                    count++;
                } else {
                    return "NO";
                }
            }
        }
        if (count == 4) {
            return "YES";
        } else {
            return "NO";
        }
    }

    public void func1() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        Set<Integer> set = new HashSet<>();
        int add = 0;
        for (int i : nums) {
            while (set.contains(i)) {
                i++;
                add++;
            }
            set.add(i);
        }
        System.out.println(add);
    }

    public static void func11() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n < 2) {
            System.out.println(0);
            return;
        }
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        Arrays.sort(nums);
        int max = nums[0] + 1;
        int add = 0;
        for (int i = 1; i < n; i++) {
            if (max > nums[i]) {
                add += max - nums[i];
                max += 1;
            } else {
                max = nums[i] + 1;
            }
        }
        System.out.println(add);
    }
}
