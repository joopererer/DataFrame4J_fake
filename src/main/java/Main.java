import dataframe4j.DataFrame;
import dataframe4j.IDataFrame;

/**
 * un demo pour Docker
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello, ici est un demo des utilisations sur DataFrame4J :");
        System.out.println(">>");
        System.out.println("#1, creer via un array et afficher tout");
        DataFrame df = new DataFrame(
                new Object[][]{
                        {"Kate", "Sam", "Tom", "Fenny"},
                        {18, 22, 20, 19},
                        {'M', 'F', 'M', 'F'},
                },  // data
                new String[]{"name", "age", "gen"} // columns
        );
        df.print();
//
//        System.out.println("\n#2, afficher les deux premières lignes");
//        df.printHead(2);
//
//        System.out.println("\n#3, afficher les deux dernières lignes");
//        df.printTail(2);
//
//        System.out.println("\n#4, selection les lignes paires (via IndexFilter)");
//        DataFrame newDf = df.selectRows(new IDataFrame.IndexFilter() {
//            @Override
//            public boolean filter(int index) {
//                return index%2==0;
//            }
//        });
//        newDf.print();

//        System.out.println("\n#5, calculer la moyenne sur age");
//        float moyenne = df.mean("age");
//        System.out.println("age moyenne:"+moyenne);
    }

}
