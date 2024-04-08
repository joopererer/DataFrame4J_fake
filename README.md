# DataFrame4J

[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fjoopererer%2FDataFrame4J_fake.svg?type=shield&issueType=license)](https://app.fossa.com/projects/git%2Bgithub.com%2Fjoopererer%2FDataFrame4J_fake?ref=badge_shield&issueType=license)
[![codecov](https://codecov.io/gh/joopererer/DataFrame4J_fake/graph/badge.svg?token=K2IAPA54RE)](https://codecov.io/gh/joopererer/DataFrame4J_fake)

C'est un projet Java visant à fournir des fonctionnalités similaires à celles du DataFrame en Python.

_________________________________________
**Version 0.0.1**
1. Construction de DataFrame avec un table et CVS file;
    ```
    DataFrame df = new DataFrame(
            new Object[][]{
                {"Kate", "Sam", "Tom", "Fenny"},
                {18, 22, 20, 19},
                {'M', 'F', 'M', 'F'},
            },  // data
            new String[]{"name", "age", "gen"} // columns
       );
    ```

2. Affichage de données
   ```
   df.print();        // afficher tout
   df.printHead(2);   // afficher que les 2 permières lignes
   df.pirntTail(5);   // afficher que les 5 dernières lignes
    ```
