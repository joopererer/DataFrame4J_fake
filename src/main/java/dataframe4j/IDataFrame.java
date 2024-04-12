package dataframe4j;

public interface IDataFrame {

    interface IndexFilter {
        boolean filter(int index);
    }

    /**************************
     *        affichage
     *************************/
    public void print();    // afficher tout
    public void printHead(int n);   // afficher que les n premières lignes
    public void printTail(int n);     // afficher que les n dernières lignes

    /**************************
     *        slection
     *************************/
    /**
     * Obtenier un sous-ensemble de lignes à partir de leur index
     * @param index array index
     * @return
     */
    public DataFrame selectRows(int[] index);

    /**
     * Obtenier un sous-ensemble de colonnes en utilisant les labels
     * @param labels array labels
     * @return
     */
    public DataFrame selectColumns(String[] labels);

    public DataFrame selectRows(IndexFilter filter);

}
