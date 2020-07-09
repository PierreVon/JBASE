package design.behavioral;

public class Strategy {
    // 该模式定义了一系列算法，并将每个算法封装起来，
    // 使它们可以相互替换，且算法的变化不会影响使用算法的客户
    // 策略模式属于对象行为模式

    interface Seasoner {
        void add();
    }

    static class Sugar implements Seasoner {

        @Override
        public void add() {
            System.out.println("adding some sugar");
        }
    }

    static class Salt implements Seasoner {

        @Override
        public void add() {
            System.out.println("adding some salt");
        }
    }

    static class Cooking {
        private Seasoner seasoner;

        public void setSeasoner(Seasoner seasoner) {
            this.seasoner = seasoner;
        }

        public void add(){
            seasoner.add();
        }
    }

    public static void main(String[] args) {
        Cooking c = new Cooking();
        c.setSeasoner(new Sugar());
        c.add();
    }
}
