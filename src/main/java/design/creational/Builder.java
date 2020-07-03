package design.creational;

public class Builder {
    // 指将一个复杂对象的构造与它的表示分离，使同样的构建过程可以创建不同的表示，这样的设计模式被称为建造者模式

    static class House {
        public void setSittingRoom(int sittingRoom) {
            SittingRoom = sittingRoom;
        }

        public void setLivingRoom(int livingRoom) {
            LivingRoom = livingRoom;
        }

        public void setDiningRoom(int diningRoom) {
            DiningRoom = diningRoom;
        }

        public void setToilet(int toilet) {
            Toilet = toilet;
        }

        public void setBalcony(int balcony) {
            Balcony = balcony;
        }

        int SittingRoom;
        int LivingRoom;
        int DiningRoom;
        int Toilet;
        int Balcony;

        @Override
        public String toString() {
            return "House{" +
                    "SittingRoom=" + SittingRoom +
                    ", LivingRoom=" + LivingRoom +
                    ", DiningRoom=" + DiningRoom +
                    ", Toilet=" + Toilet +
                    ", Balcony=" + Balcony +
                    '}';
        }
    }

    static class HouseBuilder {
        protected House mHouse = new House();

        public HouseBuilder buildSittingRoom(int amount) {
            mHouse.setSittingRoom(amount);
            return this;
        }

        public HouseBuilder buildLivingRoom(int amount) {
            mHouse.setLivingRoom(amount);
            return this;
        }

        public HouseBuilder buildDiningRoom(int amount) {
            mHouse.setDiningRoom(amount);
            return this;
        }

        public HouseBuilder buildToilet(int amount) {
            mHouse.setToilet(amount);
            return this;
        }

        public HouseBuilder buildBalcony(int amount) {
            mHouse.setBalcony(amount);
            return this;
        }

        public House build() {
            return mHouse;
        }
    }

    public static void main(String[] args) {
        HouseBuilder builder = new HouseBuilder();
        House house = builder.buildLivingRoom(3)
                .buildSittingRoom(1)
                .buildToilet(1)
                .buildBalcony(1)
                .buildDiningRoom(1)
                .build();
        System.out.println(house.toString());
    }
}
