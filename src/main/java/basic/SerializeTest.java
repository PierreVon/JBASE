package basic;

import java.io.*;

public class SerializeTest {
    public static void main(String[] args) {
        try {
            FileOutputStream fo = new FileOutputStream("./a.pk");
            ObjectOutputStream oo = new ObjectOutputStream(fo);
            A a = new A(1,2,3);
            oo.writeObject(a);
            oo.close();
            FileInputStream fi = new FileInputStream("./a.pk");
            ObjectInputStream oi = new ObjectInputStream(fi);
            A b = (A) oi.readObject();
            oi.close();
            System.out.println(b.b);
            System.out.println(b.c);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
