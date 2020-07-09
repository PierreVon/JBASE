package design.behavioral;

public class Template {
    // 定义一个操作中的算法骨架，而将算法的一些步骤延迟到子类中，
    // 使得子类可以不改变该算法结构的情况下重定义该算法的某些特定步骤。
    // 它是一种类行为型模式

    static abstract class Cooking {
        public void run() {
            prepare();
            fry();
            finished();
        }

        abstract protected void prepare();

        private void fry() {
            System.out.println("frying");
        }

        private void finished() {
            System.out.println("finished");
        }
    }

    static class MuttonPepper extends Cooking {

        @Override
        protected void prepare() {
            System.out.println("preparing mutton, pepper, salt and oil");
        }
    }

    static class FriedChinesePancake extends Cooking {

        @Override
        protected void prepare() {
            System.out.println("preparing chinese pancake, cabbage, soy sauce and oil");
        }
    }

    public static void main(String[] args) {
        Cooking c = new MuttonPepper();
        c.run();
        c = new FriedChinesePancake();
        c.run();
    }

}
