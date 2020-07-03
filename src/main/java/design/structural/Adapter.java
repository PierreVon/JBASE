package design.structural;

public class Adapter {
    // 将一个类的接口转换成客户希望的另外一个接口，使得原本由于接口不兼容而不能一起工作的那些类能一起工作

    static class Car {
        void drive() {
            System.out.println("Moving");
        }
    }

    static class Plane {
        void fly() {
            System.out.println("flying");
        }
    }

    interface Machine {
        void turnOn();
    }

    static class CarAdapter extends Car implements Machine {

        @Override
        public void turnOn() {
            drive();
        }
    }

    static class PlaneAdapter extends Plane implements Machine {

        @Override
        public void turnOn() {
            fly();
        }
    }

    public static void main(String[] args) {
        Machine m;
        m = new CarAdapter();
        m.turnOn();
        m = new PlaneAdapter();
        m.turnOn();
    }

}
