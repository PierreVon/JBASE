package design.creational;

public class AbstractFactory {
    // 是一种为访问类提供一个创建一组相关或相互依赖对象的接口，
    // 且访问类无须指定所要产品的具体类就能得到同族的不同等级的产品的模式结构。

    // 抽象工厂就像一个厨房，包括锅碗瓢盆等,输出一种风格
    // 工厂就是生产单个碗

    interface KitchenFactory {
        Dish newDish();

        Pen newPen();
    }

    public static class ChineseKitchenStyle implements KitchenFactory {

        @Override
        public Dish newDish() {
            return new ChineseDish();
        }

        @Override
        public Pen newPen() {
            return new ChinesePen();
        }
    }

    public static class JapaneseKitchenStyle implements KitchenFactory {

        @Override
        public Dish newDish() {
            return new JapaneseDish();
        }

        @Override
        public Pen newPen() {
            return new JapanesePen();
        }
    }

    interface Thing {
        void showType();
    }

    interface Dish extends Thing {
    }

    interface Pen extends Thing {
    }

    public static class ChineseDish implements Dish {

        @Override
        public void showType() {
            System.out.println("Chinese styled dish");
        }
    }

    public static class JapaneseDish implements Dish {

        @Override
        public void showType() {
            System.out.println("Japanese styled dish");
        }
    }

    public static class ChinesePen implements Pen {

        @Override
        public void showType() {
            System.out.println("Chinese styled pen");
        }
    }

    public static class JapanesePen implements Pen {

        @Override
        public void showType() {
            System.out.println("Japanese styled pen");
        }
    }

    public static void main(String[] args) {
        KitchenFactory kf = new ChineseKitchenStyle();
        Dish d = kf.newDish();
        d.showType();
        Pen p = kf.newPen();
        p.showType();
    }
}
