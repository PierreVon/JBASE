package threading;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Arrays;

public class unsafe {
    private static int intArrayBaseOffset;

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe UNSAFE = (Unsafe) theUnsafe.get(null);
        System.out.println(UNSAFE);

        int[] data = new int[10];
        System.out.println(Arrays.toString(data));
        intArrayBaseOffset = UNSAFE.arrayBaseOffset(int[].class);
        int scale = UNSAFE.arrayIndexScale(int[].class);

        System.out.println(intArrayBaseOffset);
        System.out.println(scale);
        UNSAFE.compareAndSwapInt(data, intArrayBaseOffset, 0, 3);
        UNSAFE.compareAndSwapInt(data, intArrayBaseOffset + 4 * scale, 0, 3);
        System.out.println(Arrays.toString(data));
    }
}
