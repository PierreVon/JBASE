package design.creational;

public class Singleton {
    // 指一个类只有一个实例，且该类能自行创建这个实例的一种模式
    private static volatile String s;

    public static String getInstance() {
        if (s == null) {
            synchronized (Singleton.class) {
                s = "";
            }
        }
        return s;
    }
}
