package design.creational;

public class Prototype {
    // 用一个已经创建的实例作为原型，通过复制该原型对象来创建一个和原型相同或相似的新对象
    static class Person implements Cloneable {
        public String name;
        public int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Person pie = new Person("pie", 23);
        System.out.println(pie.toString());
        Person pie_clone = (Person) pie.clone();
        System.out.println(pie_clone.toString());
        System.out.println(pie == pie_clone);
        System.out.println(pie.name == pie_clone.name);
    }


}
