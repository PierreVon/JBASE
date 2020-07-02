package basic;

import java.io.*;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UseA {
    public static void main(String[] args) {
        A a = new A(1, 2, 3);
        A b = new A(1, 2, 3);
        System.out.println(a.equals(b));
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        ExtendA ea=new ExtendA();
        ea.helloProtected();
    }
}