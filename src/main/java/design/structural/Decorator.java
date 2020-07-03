package design.structural;

import java.io.BufferedReader;
import java.io.FileReader;

public class Decorator {
    // 指在不改变现有对象结构的情况下，动态地给该对象增加一些职责（即增加其额外功能）的模式

    interface Actor {
        String show();
    }

    static class Girl implements Actor {

        @Override
        public String show() {
            return "a girl";
        }
    }

    static class CuteGirl extends Girl {
        Girl mGirl;

        CuteGirl(Girl girl) {
            mGirl = girl;
        }

        public String show() {
            return mGirl.show() + " who is cute";
        }
    }

    public static void main(String[] args) {
        Girl g = new Girl();
        CuteGirl cg = new CuteGirl(g);
        System.out.println(cg.show());
    }
}
