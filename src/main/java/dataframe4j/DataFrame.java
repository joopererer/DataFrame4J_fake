package dataframe4j;


import java.util.*;
import java.util.stream.Collectors;

public class DataFrame implements IDataFrame {

    static final String VAL_NAN = "NaN";

    private List<List<?>> data;
    private List<Object> index;
    private List<String> columns;
    private List<Class<?>> types = new ArrayList<>();
    private int rows;

    public DataFrame(Object[][] data, String[] columns) {
        this(data, null, columns);
    }

    public DataFrame(Object[][] data, Object[] index, String[] columns) {
        if(data!=null) {
            this.data = new ArrayList<>(data.length);
            for(Object[] col_data : data) {
                Class<?> type = null;
                // get type
                if(col_data.getClass() == Object[].class){
                    type = checkType(col_data);
                }else{
                    if(col_data.length!=0) {
                        type = col_data.getClass().getComponentType();
                    }
                }
                if(type==null) {
                    throw new RuntimeException("Il existe de différents types de données dans un même colonne !");
                }
                List<?> list = Arrays.stream(col_data).toList();
                rows = Math.max(list.size(), rows);
                this.data.add(list);
                this.types.add(type);
            }
        }

        setIndex(index);
        setColumns(columns);
    }

    public DataFrame(String CSVFile) {
        CSVReader reader = new CSVReader(CSVFile);
        if((rows=reader.load())!=-1){
            this.data = reader.getData();
            this.types = reader.getTypes();
            setColumns(reader.getColumns());
            setIndex(null);
        }
    }

    public void setColumns(String[] labels) {
        if(labels!=null) {
            // check duplicate
            if(checkDuplicate(labels)){
                throw new RuntimeException("Contenus de tableau 'columns' ne peuvent pas être dupliqués");
            }
            this.columns = Arrays.stream(labels).toList();
        }else{
            this.columns = null;
        }
    }

    private void setIndex(Object[] index) {
        if(index==null){
            this.index = null;
        }else{
            // check duplicate
            if(checkDuplicate(index)){
                throw new RuntimeException("Contenus de tableau 'index' ne peuvent pas être dupliqués");
            }
            this.index = Arrays.stream(index).toList();
        }
    }

    public void print() {
        print(0, getRowSize());
    }

    public void printHead(int n) {
        print(0, n);
    }

    public void printTail(int n) {
        print(getRowSize()-n, getRowSize());
    }

    public int getRowSize(){
        return index!=null?index.size():rows;
    }

    public int getColumnSize(){
        return columns!=null?columns.size():0;
    }

    public Class<?> getColumnTypeByIndex(int index) {
        if(index<0 || index>=types.size()){
            return null;
        }
        return types.get(index);
    }

    public Class<?> getColumnTypeByLabel(String label) {
        if(columns==null)
            return null;
        int index = columns.indexOf(label);
        return getColumnTypeByIndex(index);
    }

    private void print(int startRow, int endRow){
        if(columns==null){
            // cas special, manque index ou columns
            System.out.println("Empty DataFrame");
            System.out.println("Columns: "+ (columns==null?"[]":columns.toString()));
            System.out.println("Index: "+ (index ==null?"[]": index.toString()));
            return;
        }

        startRow = Math.max(0, startRow);
        endRow = Math.min(getRowSize(), endRow);

        // afficher le nom des colonnes
        if(columns!=null) {
            for(Object label : columns){
                System.out.print("\t\t"+label);
            }
            System.out.println();
        }
        // afficher chaque ligne
        int cols = getColumnSize();
        for (int i=startRow; i<endRow; i++){
            System.out.print(index!=null?index.get(i):i);
            for(int j=0; j<cols; j++){
                String value = VAL_NAN;
                if(data !=null && j< data.size() && i < data.get(j).size()){
                    Object obj = data.get(j).get(i);
                    value = obj!=null?obj.toString():VAL_NAN;
                }
                System.out.print("\t\t"+value);
            }
            System.out.println();
        }
    }

    private boolean checkDuplicate(Object[] data) {
        Set<Object> set = new HashSet<>(Arrays.asList(data));
        return set.size()!=data.length;
    }

    private Class<?> checkType(Object[] data) {
        Class<?> type = null;
        for (Object item : data) {
            if(item==null)
                continue;
            if(type==null) {
                type = item.getClass();
            }else{
                if(item.getClass()!=type){
                    return null;
                }
            }
        }
        return type;
    }

//    private static List<?> createColumn(Object[] colData, Class<?> type) {
//        if(type == Short.class) {
//            return Arrays.stream(colData).map(obj -> (Short) obj).collect(Collectors.toList());
//        }
//        if(type == Integer.class) {
//            return Arrays.stream(colData).map(obj -> (Integer) obj).collect(Collectors.toList());
//        }
//        if(type == Float.class) {
//            return Arrays.stream(colData).map(obj -> (Float) obj).collect(Collectors.toList());
//        }
//        if(type == Double.class) {
//            return Arrays.stream(colData).map(obj -> (Double) obj).collect(Collectors.toList());
//        }
//        if(type == Long.class) {
//            return Arrays.stream(colData).map(obj -> (Long) obj).collect(Collectors.toList());
//        }
//        if(type == Boolean.class) {
//            return Arrays.stream(colData).map(obj -> (Boolean) obj).collect(Collectors.toList());
//        }
//        if(type == Character.class) {
//            return Arrays.stream(colData).map(obj -> (Character) obj).collect(Collectors.toList());
//        }
//        if(type == String.class) {
//            return Arrays.stream(colData).map(obj -> (String) obj).collect(Collectors.toList());
//        }
//        return Arrays.stream(colData).toList();
//    }

}