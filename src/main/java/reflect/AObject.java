package reflect;

public class AObject {
    public static int count;
    public String msg;
    private int secret;

    public int getSecret() {
        return secret;
    }

    public void hello() {
        System.out.println("hello");
    }
    private void hidden() {
        System.out.println("hidden function");
    }
}
