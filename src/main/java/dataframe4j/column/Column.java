package dataframe4j.column;//package dataframe4j.column;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Column<T> {

    private Class<T> type;
    private List<T> data;

    public Column(Class<T> type) {
        this(null, type);
    }

    public Column(T[] data, Class<T> type) {
        this.type = type;
        setData(data);
    }

    public List<T> getData() {
        return data;
    }

    public int size(){
        return data.size();
    }

    public T get(int i) {
        return data.get(i);
    }

    public void setData(T[] data) {
        if(data!=null) {
            this.data = new ArrayList<>(data.length);
            Collections.addAll(this.data, data);
        }else{
            this.data = new ArrayList<>();
        }
    }

    public Class<T> getType(){
        return this.type;
    }

}
