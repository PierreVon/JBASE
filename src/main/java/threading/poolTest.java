package threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class poolTest {
    public static void main(String[] args) {
        func();
        System.out.println("shout");
        func();
        System.out.println("shout");
    }

    public static void func(){
        ExecutorService pool = Executors.newFixedThreadPool(2);
        for(int i=0;i<3;i++){
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    for(int i=0;i<10;i++){
                        System.out.println(Thread.currentThread().getName()+"==>"+i);
                    }
                }
            });
        }
    }

}
