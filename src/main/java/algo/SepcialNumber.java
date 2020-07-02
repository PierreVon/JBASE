package algo;

public class SepcialNumber {
    /**
     * 最大公约数，辗转相除
     *
     * @param a
     * @param b
     * @return
     */
    public static int gcd(int a, int b) {
        if (b == 0) return a;
        return a % b == 0 ? b : gcd(b, a % b);
    }

    /**
     * 最小公倍数
     *
     * @param a
     * @param b
     * @return
     */
    public static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    /**
     * 判断素数（质数）:2,3,5,7...
     *
     * @param a
     * @return
     */
    public static boolean isPrime(int a) {
        if (a < 2 || a % 2 == 0 && a != 2) {
            return false;
        }
        for (int i = 3; i < Math.sqrt(a); i += 2) {
            if (a % i == 0) {
                return false;
            }
        }
        return true;
    }

    class NegativeIntegerException extends Exception {
        NegativeIntegerException() {
        }

        NegativeIntegerException(String str) {
            super(str);
        }
    }

    /**
     * 斐波那契数列: 1,1,2,3,5...
     * f(0)=0, f(1)=1, f(n)=f(n-1)+f(n-2)
     * @param n
     * @return
     * @throws NegativeIntegerException
     */
    public int fibonacci(int n) throws NegativeIntegerException {
        if (n < 0) throw new NegativeIntegerException("Not negative value！");
        if (n == 0) return 0;
        int a = 0, b = 1, tmp;
        for (int i = 2; i <= n; i++) {
            tmp = a;
            a = b;
            b = tmp + b;
        }
        return b;
    }

    public static void main(String[] args) {
        System.out.println(gcd(4, 3));
        System.out.println(gcd(3, 4));
        System.out.println(gcd(3, 6));
        System.out.println(lcm(3, 6));
        System.out.println(isPrime(2));
        System.out.println(isPrime(27));
        SepcialNumber sn = new SepcialNumber();
        try {
            System.out.println(sn.fibonacci(-1));
        } catch (NegativeIntegerException e) {
            e.printStackTrace();
        }
    }
}
