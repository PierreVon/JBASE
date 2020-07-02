package threading;

public class poolTest2 {
    public static void main(String[] args) {
        task t=new task();
        for(int i=0;i<100;i++){
            t.func();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("loop"+i);
        }
    }
}
