package design.structural;

public class Bridge {
    // 将抽象与实现分离，使它们可以独立变化。
    // 它是用组合关系代替继承关系来实现，从而降低了抽象和实现这两个可变维度的耦合度

    interface Color {
        String getColor();
    }

    static class Blue implements Color {

        @Override
        public String getColor() {
            return "blue";
        }
    }

    static class Yellow implements Color {

        @Override
        public String getColor() {
            return "yellow";
        }
    }

    static class DefaultColor implements Color {

        @Override
        public String getColor() {
            return "black";
        }
    }

    interface Bag {
        String getType();
    }

    static class HandBag implements Bag {
        Color color = new DefaultColor();

        public void setColor(Color c) {
            if (c != null) {
                color = c;
            }
        }

        @Override
        public String getType() {
            return color.getColor() + " HandBag";
        }
    }

    static class Wallet implements Bag {
        Color color = new DefaultColor();

        public void setColor(Color c) {
            if (c != null) {
                color = c;
            }
        }

        @Override
        public String getType() {
            return color.getColor() + " Wallet";
        }
    }

    public static void main(String[] args) {
        Wallet bag = new Wallet();
        bag.setColor(new Blue());
        System.out.println(bag.getType());
    }
}
