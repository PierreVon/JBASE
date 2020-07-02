package threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class task {
    public static ExecutorService pool = Executors.newFixedThreadPool(10);
    public void func(){
        for(int i=0;i<10;i++){
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    pool.submit(new Runnable() {
                        @Override
                        public void run() {
                            subFunc();
                        }
                    });
                    System.out.println(Thread.currentThread().getName()+"exited");
                }
            });
        }
    }

    private void subFunc(){
        for(int i=0;i<10;i++){
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    for(int i=0;i<10;i++){
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName()+"==>finished");
                }
            });
        }
    }
}
