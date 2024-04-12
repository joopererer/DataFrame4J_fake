import dataframe4j.DataFrame;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello, ici est un demo de DataFrame4J.");

        DataFrame df = new DataFrame(
                new Object[][]{
                        {"Kate", "Sam", "Tom", "Fenny"},
                        {18, 22, 20, 19},
                        {'M', 'F', 'M', 'F'},
                },  // data
                new String[]{"name", "age", "gen"} // columns
        );
        df.print();
    }

}
