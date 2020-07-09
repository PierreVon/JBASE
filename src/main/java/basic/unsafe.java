package basic;

import sun.misc.Unsafe;

import java.util.ArrayList;

public class unsafe {
    public static void main(String[] args) {
        Integer[] table = new Integer[2];
        Unsafe.getUnsafe().compareAndSwapInt(table, 0, 0, -1);
        for(Integer i: table){
            System.out.println(i);
        }
    }
}
