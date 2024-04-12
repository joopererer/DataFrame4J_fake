package dataframe4j;


import dataframe4j.column.Column;
import dataframe4j.column.ColumnGenerator;

import java.util.*;

public class DataFrame implements IDataFrame {

    static final String VAL_NAN = "NaN";

    private List<Column<?>> data;
    private List<Object> index;
    private List<String> columns;
    private List<Class<?>> types = new ArrayList<>();
    private int rows;

    public DataFrame(Object[][] data, String[] columns) {
        this(data, null, columns);
    }

    private DataFrame(Object[][] data, Object[] index, String[] columns) {
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

                Column<?> list = ColumnGenerator.createColumn(col_data, type);//new Column(col_data, type);
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

    @Override
    public DataFrame selectRows(int[] index) {
        if(index==null || index.length==0){
            return null;
        }
        DataFrame df = new DataFrame(null, columns.toArray(new String[]{}));
        for (int rowId : index) {
            for (int j = 0; j < getColumnSize(); j++) {
                if (rowId >= data.get(j).size()) {
                    continue;
                }
                df.addColumn(columns.get(j), data.get(j).get(rowId));
            }
        }
        return df;
    }

    @Override
    public DataFrame selectColumns(String[] labels) {
        if(labels==null || labels.length==0){
            return null;
        }
        DataFrame df = new DataFrame(null, labels);
        for (String label : labels) {
            int colId = columns.indexOf(label);
            if(colId!=-1){
                for(int i=0; i<data.get(colId).size(); i++) {
                    df.addColumn(label, data.get(colId).get(i));
                }
            }
        }
        return df;
    }

    @Override
    public DataFrame selectRows(IndexFilter filter) {
        DataFrame df = new DataFrame(null, columns.toArray(new String[]{}));
        for(int i=0; i<getRowSize(); i++){
            if(filter!=null && !filter.filter(i)){
                continue;
            }
            for(int j=0; j<getColumnSize(); j++) {
                if(i>=data.get(j).size()) {
                    continue;
                }
                df.addColumn(columns.get(j), data.get(j).get(i));
            }
        }
        return df;
    }

    private void addColumn(String label, Object item) {
        int index = columns.indexOf(label);
        if(index<0 || index>=columns.size()){
            // label not found
            return;
        }
        if(item!=null){
            if(index>=types.size()){
                types.add(item.getClass());
            }
            if(item.getClass()!=types.get(index)){
                // wrong type
                return;
            }
            if(data==null){
                data = new ArrayList<>(columns.size());
            }
            if(index>=data.size()) {
                data.add(new Column<>(item.getClass()));
            }
            ((Column)data.get(index)).getData().add(item);
            rows = Math.max(data.get(index).size(), rows);
        }
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

}