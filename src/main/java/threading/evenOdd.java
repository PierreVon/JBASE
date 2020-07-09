package threading;

public class evenOdd {
    private static volatile int i = 1;

    static class Counter implements Runnable {
        private int type;

        public Counter(int type) {
            this.type = type;
        }

        @Override
        public void run() {
            while (i < 100) {
                if ((i & 1) == type) {
                    System.out.println(Thread.currentThread().getName() + " ==> " + i);
                    i++;
                }
            }
        }
    }

    private static void Method2() {
        new Thread(new Counter(1)).start();
        new Thread(new Counter(0)).start();
    }

    private static void Method1() {
        Object lock = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    for (int i = 1; i < 100; i += 2) {
                        System.out.println(Thread.currentThread().getName() + " ==> " + i);
                        lock.notify();
                        try {
                            lock.wait(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    for (int i = 2; i < 100; i += 2) {
                        System.out.println(Thread.currentThread().getName() + " ==> " + i);
                        lock.notify();
                        try {
                            lock.wait(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        Method2();
    }
}
