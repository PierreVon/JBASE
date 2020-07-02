package threading;

public class subThread {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            subFunc();
        }
        System.out.println("shut");
    }

    public static void subFunc() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + "==>" + i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
