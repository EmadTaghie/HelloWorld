package ir.ac.kntu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class TestHelloWorld {

    private static StringBuilder str;

    @BeforeEach
    public void prepare() throws FileNotFoundException, MalformedURLException {
        URL url =
                new File("src/main/java/ir/ac/kntu/Main.java").toURI().toURL();
        File file = new File(url.getPath());
        Assertions.assertTrue(file.exists());
        Scanner scanner = new Scanner(file);
        str = new StringBuilder();
        while (scanner.hasNext()) {
            str.append(scanner.nextLine());
        }
        scanner.close();
    }

    @Test
    public void testHelloWorld() {
        System.err.println("Testing printed value");
        int index = str.toString().indexOf("(\"");
        int indexEnd = str.toString().indexOf("\")");
        Assertions.assertTrue(index > 0 && indexEnd > index);
        String content = str.substring(index + 2, indexEnd);
        Assertions.assertEquals("Hello World", content, "Failed");
        System.err.println("Passed!\n");
    }

    @Test
    public void testPackage() {
        System.err.println("Testing package");
        Assertions.assertTrue(str.toString().contains("ir.ac.kntu"), "Failed");
        System.err.println("Passed!\n");
    }
}
