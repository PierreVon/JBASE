package design.structural;

public class Facade {
    // 是一种通过为多个复杂的子系统提供一个一致的接口，而使这些子系统更加容易被访问的模式

    static class System1{
        void show(){
            System.out.println("hello");
        }
    }

    static class System2{
        void show(){
            System.out.println("world");
        }
    }

    static class FacadeX{
        static void function(){
            new System1().show();
            new System2().show();
        }
    }

    public static void main(String[] args) {
        FacadeX.function();
    }
}
