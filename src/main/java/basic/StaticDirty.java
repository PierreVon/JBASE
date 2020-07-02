package basic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StaticDirty {
    public static void main(String[] args) {
        final A a=new A(1,2,3);
        final A b=new A(1,2,3);
        final A c=new A(1,2,3);
        final int amount=100000;
        CountDownLatch latch= new CountDownLatch(3);
        ExecutorService pool = Executors.newCachedThreadPool();
        pool.submit(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<amount;i++){
                    a.s+=1;
                }
                latch.countDown();
            }
        });
        pool.submit(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<amount;i++){
                    b.s+=1;
                }
                latch.countDown();
            }
        });
        pool.submit(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<amount;i++){
                    c.s+=1;
                }
                latch.countDown();
            }
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(A.s);
    }
}
