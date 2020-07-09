package basic;

public class bitopt {
    static final int HASH_BITS = 0x7fffffff;

    static final int spread(int h) {
        return (h ^ (h >>> 16)) & HASH_BITS;
    }

    static final int test(int n) {
        return n - (n >>> 2);
    }

    public static void main(String[] args) {
        System.out.println(spread(122));
        System.out.println(spread(11233));
        System.out.println(spread(1111111233));
        System.out.println(spread(-1111111233));
        System.out.println(test(8));
        System.out.println(test(18));
    }
}
