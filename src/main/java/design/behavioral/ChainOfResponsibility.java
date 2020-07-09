package design.behavioral;

public class ChainOfResponsibility {
    // 为了避免请求发送者与多个请求处理者耦合在一起，
    // 将所有请求的处理者通过前一对象记住其下一个对象的引用而连成一条链；
    // 当有请求发生时，可将请求沿着这条链传递，直到有对象处理它为止
    abstract static class Handler {
        private Handler handler;

        public void setHandler(Handler handler) {
            this.handler = handler;
        }

        public Handler getHandler() {
            return handler;
        }

        public abstract void handle(int i);
    }

    static class EvenHandler extends Handler {

        @Override
        public void handle(int i) {
            if (i % 2 == 0) {
                System.out.println(i + " is an even number.");
            } else {
                System.out.println(i + " is an odd number.");
            }
            if (getHandler() != null) {
                getHandler().handle(i);
            }
        }
    }

    static class NegativeHandler extends Handler {

        @Override
        public void handle(int i) {
            if (i < 0) {
                System.out.println(i + " is a negative number.");
            }else{
                System.out.println(i + " is a positive number.");
            }
            if (getHandler() != null) {
                getHandler().handle(i);
            }
        }
    }

    public static void main(String[] args) {
        Handler handler = new EvenHandler();
        handler.setHandler(new NegativeHandler());
        handler.handle(3);
    }
}
