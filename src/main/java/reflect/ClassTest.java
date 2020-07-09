package reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class ClassTest {

    public static void main(String[] args) throws
            ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException,
            NoSuchMethodException, InvocationTargetException {
        Class<?> clazz = Class.forName("reflect.AObject");
        getClassInfo(new ClassTest());
        AObject obj = (AObject) clazz.newInstance();
        obj.hello();

        Field f = clazz.getDeclaredField("secret");
        // 暂时赋予访问私有*的权限
        f.setAccessible(true);
        f.set(obj, 3);
        System.out.println(obj.getSecret());

        Method m1 = clazz.getMethod("hello");
        m1.invoke(obj);
        Method m2 = clazz.getDeclaredMethod("hidden");
        m2.setAccessible(true);
        m2.invoke(obj);
    }

    public static void getClassInfo(Object obj) {
        System.out.println("============================");
        System.out.println(obj.getClass().getName());
        for (Method m : obj.getClass().getMethods()) {
            System.out.printf("Method: %s, Params: ", m.getName());
            for (Type t : m.getParameterTypes()) {
                System.out.printf("%s, ", t.getTypeName());
            }
            System.out.println();
        }
        for (Field f : obj.getClass().getFields()) {
            System.out.println("Field: " + f.getName());
        }
        System.out.println("SuperClass: " + obj.getClass().getSuperclass().getName());
        System.out.println("============================");
    }


}
