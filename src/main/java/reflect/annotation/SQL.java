package reflect.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// a simple example
public class SQL {
    public static String query(GameTable game) {
        StringBuilder sb = new StringBuilder();
        Class<?> clazz = game.getClass();
        boolean hasTable = clazz.isAnnotationPresent(Table.class);
        if (hasTable) {
            Table table = (Table) clazz.getAnnotation(Table.class);
            String tableName = table.value();
            sb.append("select * from ").append(tableName).append(" where 1=1 ");
            Field[] fs = clazz.getDeclaredFields();
            for (Field f : fs) {
                boolean fexist = f.isAnnotationPresent(Column.class);
                if (fexist) {
                    Column column = f.getAnnotation(Column.class);
                    String columnName = column.value();
                    String fieldName = f.getName();
                    Object fieldValue = null;
                    String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    try {
                        Method method = clazz.getMethod(getMethodName);
                        fieldValue = method.invoke(game);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    sb.append("and ").append(columnName).append("=\"").append(fieldValue).append("\" ");
                }
            }
            return sb.toString();
        }
        return null;
    }

    public static void main(String[] args) {
        GameTable game = new GameTable();
        game.setName("gun fight");
        game.setType("fighting");
        System.out.println(SQL.query(game));
    }
}
