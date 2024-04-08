import dataframe4j.DataFrame;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.*;

public class DataFrameTest {

//    PrintStream console = null;
//    ByteArrayOutputStream bytes = null;
//
//    @org.junit.Before
//    public void beforetest() throws Exception{
//        bytes = new ByteArrayOutputStream();
//        console = System.out;
//        System.setOut(new PrintStream(bytes));
//    }
//
//    @org.junit.After
//    public void aftertest() throws Exception {
//        System.setOut(console);
//    }

    @Test
    public void testConstructeurWithTable() {
        DataFrame df = new DataFrame(
                new Object[][]{
                        new String[]{"Kate", "Tom", "Sam", "Fanny"},
                        new Integer[]{18, 22, 20, 19},
                        new Character[]{'M', 'F', 'M', 'F'}
                },
                new String[]{"name", "age", "gen"}
        );
        assertNotNull("creat a DataFrame", df);
    }

    @Test(expected = RuntimeException.class)
    public void testConstructeurWithTable2() {
        DataFrame df = new DataFrame(
                new Object[][]{
                        new String[]{"Kate", "Tom", "Sam", "Fanny"},
                        new Object[]{18, 22, 20, "19"},  // different type dans même colonne
                        new Character[]{'M', 'F', 'M', 'F'}
                },
                new String[]{"name", "age", "gen"}
        );
        assertNull("creat a DataFrame", df);
    }

    @Test
    public void testConstructeurWithTableEmpty() {
        DataFrame df = new DataFrame(
                new Object[][]{},
                new String[]{"name", "age", "gen"}
        );
        assertNotNull("creat a DataFrame", df);
    }

    @Test
    public void testConstructeurWithTableNull() {
        DataFrame df = new DataFrame(
                null,
                new String[]{"name", "age", "gen"}
        );
        assertNotNull("creat a DataFrame", df);
    }

    @Test
    public void testConstructeurWithColumnsEmpty() {
        DataFrame df = new DataFrame(
                new Object[][]{
                        new String[]{"Kate", "Tom", "Sam", "Fanny"},
                        new Object[]{18, 22, 20, 19},  // different type dans même colonne
                        new Character[]{'M', 'F', 'M', 'F'}
                },
                new String[]{}
        );
        assertNotNull("creat a DataFrame", df);
    }

    @Test
    public void testConstructeurWithColumnsNull() {
        DataFrame df = new DataFrame(
                new Object[][]{
                        new String[]{"Kate", "Tom", "Sam", "Fanny"},
                        new Object[]{18, 22, 20, 19},  // different type dans même colonne
                        new Character[]{'M', 'F', 'M', 'F'}
                },
                null
        );
        assertNotNull("creat a DataFrame", df);
    }

    @Test(expected = RuntimeException.class)
    public void testConstructeurWithColumnsDuplicate() {
        DataFrame df = new DataFrame(
                new Object[][]{
                        new String[]{"Kate", "Tom", "Sam", "Fanny"},
                        new Object[]{18, 22, 20, 19},  // different type dans même colonne
                        new Character[]{'M', 'F', 'M', 'F'}
                },
                new String[]{"name", "age", "name"}
        );
        assertNull("creat a DataFrame", df);
    }

//    @Test
//    public void testAffichageHead() {
//        DataFrame df = new DataFrame(
//                new Object[][]{
//                        new String[]{"Kate", "Tom", "Sam", "Fanny"},
//                        new Integer[]{18, 22, 20, 19},
//                        new Character[]{'M', 'F', 'M', 'F'}
//                },
//                new String[]{"name", "age", "gen"}
//        );
//        df.printHead(1);
//        String s = "name\t\tage\t\tgen\r\n0\t\tKate\t\t18\t\tM";
//        assertEquals(s, bytes.toString().trim());
//    }
//
//    @Test
//    public void testAffichageHead2() {
//        DataFrame df = new DataFrame(
//                new Object[][]{
//                        new String[]{"Kate", "Tom", "Sam", "Fanny"},
//                        new Integer[]{18, 22, 20, 19},
//                        new Character[]{'M', 'F', 'M', 'F'}
//                },
//                new String[]{"name", "age", "gen"}
//        );
//        df.printHead(0);
//        String s = "name\t\tage\t\tgen";
//        assertEquals(s, bytes.toString().trim());
//    }
//
//    @Test
//    public void testAffichageHead3() {
//        DataFrame df = new DataFrame(
//                new Object[][]{
//                        new String[]{"Kate", "Tom", "Sam", "Fanny"},
//                        new Integer[]{18, 22, 20, 19},
//                        new Character[]{'M', 'F', 'M', 'F'}
//                },
//                new String[]{"name", "age", "gen"}
//        );
//        df.printHead(-1);
//        String s = "name\t\tage\t\tgen";
//        assertEquals(s, bytes.toString().trim());
//    }
//
//    @Test
//    public void testAffichageTail() {
//        DataFrame df = new DataFrame(
//                new Object[][]{
//                        new String[]{"Kate", "Tom", "Sam", "Fanny"},
//                        new Integer[]{18, 22, 20, 19},
//                        new Character[]{'M', 'F', 'M', 'F'}
//                },
//                new String[]{"name", "age", "gen"}
//        );
//        df.printTail(1);
//        String s = "name\t\tage\t\tgen\r\n3\t\tFanny\t\t19\t\tF";
//        assertEquals(s, bytes.toString().trim());
//    }
//
//    @Test
//    public void testAffichageTail2() {
//        DataFrame df = new DataFrame(
//                new Object[][]{
//                        new String[]{"Kate", "Tom", "Sam", "Fanny"},
//                        new Integer[]{18, 22, 20, 19},
//                        new Character[]{'M', 'F', 'M', 'F'}
//                },
//                new String[]{"name", "age", "gen"}
//        );
//        df.printTail(0);
//        String s = "name\t\tage\t\tgen";
//        assertEquals(s, bytes.toString().trim());
//    }

}
