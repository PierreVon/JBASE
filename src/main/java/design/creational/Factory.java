package design.creational;

public class Factory {
    // 定义一个创建产品对象的工厂接口，将产品对象的实际创建工作推迟到具体子工厂类当中。
    public interface ABFactory {
        Product produce();
    }

    public static class FactoryA implements ABFactory {

        @Override
        public Product produce() {
            return new ProductA();
        }
    }

    public static class FactoryB implements ABFactory {

        @Override
        public Product produce() {
            return new ProductB();
        }
    }

    public interface Product {
        void tag();
    }

    public static class ProductA implements Product {
        @Override
        public void tag() {
            System.out.println("new ProductA");
        }
    }

    public static class ProductB implements Product {
        @Override
        public void tag() {
            System.out.println("new ProductB");
        }
    }

    public static void main(String[] args) {
        ABFactory f;
        f = new FactoryA();
        Product a = f.produce();
        a.tag();
    }
}
