package dataframe4j.column;//package dataframe4j.column;

import java.util.Arrays;

/**
 * pour générer les différents types de colonnes
 */
public class ColumnGenerator {

    public static Column<?> createColumn(Object[] colData, Class<?> type) {
        if(type == Integer.class) {
            return new Column<>(Arrays.stream(colData).map(obj -> (Integer) obj).toArray(Integer[]::new), Integer.class);
        }
        if(type == Short.class) {
            return new Column<>(Arrays.stream(colData).map(obj -> (Short) obj).toArray(Short[]::new), Short.class);
        }
        if(type == Float.class) {
            return new Column<>(Arrays.stream(colData).map(obj -> (Float) obj).toArray(Float[]::new), Float.class);
        }
        if(type == Long.class) {
            return new Column<>(Arrays.stream(colData).map(obj -> (Long) obj).toArray(Long[]::new), Long.class);
        }
        if(type == Boolean.class) {
            return new Column<>(Arrays.stream(colData).map(obj -> (Boolean) obj).toArray(Boolean[]::new), Boolean.class);
        }
        if(type == Character.class) {
            return new Column<>(Arrays.stream(colData).map(obj -> (Character) obj).toArray(Character[]::new), Character.class);
        }
        if(type == String.class) {
            return new Column<>(Arrays.stream(colData).map(obj -> (String) obj).toArray(String[]::new), String.class);
        }
        return new Column<>(colData, Object.class);
    }

}
