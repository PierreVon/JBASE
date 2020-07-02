package basic;

import java.io.Serializable;
import java.util.Objects;

public class A implements Serializable {
    public A(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public A() {
    }

    private int a;
    public int b;
    protected transient int c;

    static int s = 0;

    final public void hello() {

    }

    private void helloPrivate(){
        System.out.println("private hello");
    }

    protected void helloProtected(){
        System.out.println("protected hello");
    }

    synchronized static public void staticFunc() {
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1_000);
                System.out.println(i + "==>" + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public void normalFunc() {
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1_000);
                System.out.println(i + "==>" + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void exceptionTest() throws MyException{
        throw new MyException("great!");
    }

    public <T> void print(T[] arr){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        A a1 = (A) o;
        return a == a1.a &&
                b == a1.b &&
                c == a1.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }
}
