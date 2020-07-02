package basic;

public class SynMethodTest {

    public static void normal(){
        A a = new A();
        A b = new A();
        new Thread(new Runnable() {
            @Override
            public void run() {
                a.normalFunc();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                b.normalFunc();
            }
        }).start();
    }

    public static void main(String[] args) {
        A a = new A();
        A b = new A();
        new Thread(new Runnable() {
            @Override
            public void run() {
                a.staticFunc();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                b.staticFunc();
            }
        }).start();
    }
}
