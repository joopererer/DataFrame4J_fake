package dataframe4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    static final String SEPARATEUR = ",";
    private final String csvFile;
    private String[] columns;
    private List<List> data;
    private List<Class<?>> types;

    public CSVReader(String file) {
        csvFile = file;
    }

    public int load() {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            int count = 0;
            types = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                if(line.trim().isEmpty()){
                    continue;
                }
                //System.out.println(line);
                String[] rowData = line.split(SEPARATEUR);
                if(count==0) {
                    columns = rowData;
                } else {
                    if(this.data==null){
                        this.data = new ArrayList<>();
                        for(int i=0; i<this.columns.length; i++){
                            types.add(typeGuessing(rowData[i]));
                        }
                    }
                    addRowData(data, rowData, types);
                }
                count++;
            }
            return count;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public String[] getColumns() {
        return columns==null?null:columns.clone();
    }

    public List<List> getData() {
        return data==null?null:data.stream().toList();
    }

    public List<Class<?>> getTypes() {
        return types==null?null:types.stream().toList();
    }

    private Class<?> typeGuessing(String data) {
        if(data.matches("\\d+")) { // is Integer
            return Integer.class;
        }
        if(data.matches("\\d+\\.\\d?")) {  // is Float
            return Float.class;
        }
        if(data.toLowerCase().matches("^(?i)true|false$")){  // is Boolean
            return Boolean.class;
        }
        return String.class;  // else String
    }

    private void addRowData(List<List> data, String[] rowData, List<Class<?>> types) {
        for(int i=0; i<types.size(); i++) {
            if(i>=data.size()) {
                if(types.get(i) == Integer.class) {
                    data.add(new ArrayList<Integer>());
                }else
                if(types.get(i) == Float.class) {
                    data.add(new ArrayList<Float>());
                }else
                if(types.get(i) == Boolean.class) {
                    data.add(new ArrayList<Boolean>());
                }else
                if(types.get(i) == String.class) {
                    data.add(new ArrayList<String>());
                }
            }
            if(i>=rowData.length || rowData[i]==null || rowData[i].isEmpty()){
                data.get(i).add(null);
                continue;
            }
            try {
                if(types.get(i) == Integer.class) {
                    ((List<Integer>) data.get(i)).add(Integer.valueOf(rowData[i]));
                }else
                if(types.get(i) == Float.class) {
                    ((List<Float>)data.get(i)).add(Float.valueOf(rowData[i]));
                }else
                if(types.get(i) == Boolean.class) {
                    ((List<Boolean>)data.get(i)).add(Boolean.valueOf(rowData[i]));
                }else
                if(types.get(i) == String.class) {
                    ((List<String>)data.get(i)).add(rowData[i]);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

}
