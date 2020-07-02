package threading.control;

import java.util.concurrent.CountDownLatch;

public class countDown {
    public static void main(String[] args) {
        for(int i=0;i<3;i++){
            Driver mainDriver = new Driver(i);
            mainDriver.drive();
        }
    }

    public static class Driver {
        int version;

        Driver(int version) {
            this.version = version;
        }

        public void drive() {
            CountDownLatch startLatch = new CountDownLatch(1);
            CountDownLatch stopLatch = new CountDownLatch(10);
            for (int i = 0; i < 10; i++)
                new Thread(new Worker(startLatch, stopLatch, version)).start();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("Drive %s starts invoking workers\n", version);
            startLatch.countDown();
            try {
                stopLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("all tasks done!");
        }
    }

    public static class Worker implements Runnable {
        CountDownLatch startLatch;
        CountDownLatch stopLatch;
        int parent;

        Worker() {
        }

        Worker(CountDownLatch startLatch, CountDownLatch stopLatch, int parent) {
            this.startLatch = startLatch;
            this.stopLatch = stopLatch;
            this.parent = parent;
        }

        @Override
        public void run() {
            try {
                startLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            doWork();
            stopLatch.countDown();
        }

        private void doWork() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "==>" + "finished, parent is " + parent);
        }
    }
}
