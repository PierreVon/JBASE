package design.structural;

public class Proxy {
    // 由于某些原因需要给某对象提供一个代理以控制对该对象的访问，

    private static class RealAction {
        int doubleIt(int d) {
            return 2 * d;
        }
    }

    static class GatewayException extends Exception {
        GatewayException(String msg) {
            super(msg);
        }
    }

    private static class ActionProxy {
        RealAction action = new RealAction();

        public int doAction(int d) throws GatewayException {
            if (d > 10 || d < 0) {
                throw new GatewayException("input should beyond 0 and within 11");
            } else {
                return action.doubleIt(d);
            }
        }
    }

    public static void main(String[] args) {
        ActionProxy ap = new ActionProxy();
        try {
            System.out.println(ap.doAction(3));
            System.out.println(ap.doAction(11));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
