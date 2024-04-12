import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.*;

public class MainTest {

    @Test
    public void testConstructeur() {
        Main main = new Main();
        assertNotNull(main);
    }

    @Test
    public void testMain() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String[] args = {"arg1", "arg2"};
        Main.main(args);

        String expectedOutput = "Hello, ici est un demo des utilisations sur DataFrame4J :\n" +
                ">>";
        assertEquals(expectedOutput, outContent.toString().trim());
    }

}
