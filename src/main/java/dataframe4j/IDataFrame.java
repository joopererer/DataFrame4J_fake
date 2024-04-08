package dataframe4j;

public interface IDataFrame {

    /**************************
     *        affichage
     *************************/
    public void print();    // afficher tout
    public void printHead(int n);   // afficher que les n premières lignes
    public void printTail(int n);     // afficher que les n dernières lignes

}
