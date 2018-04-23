package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

public class tests {
    private static void assertFileContent(String name1, String name2) {
        StringBuilder content1 = new StringBuilder();
        StringBuilder content2 = new StringBuilder();
        try {
            BufferedReader br1 = new BufferedReader(new FileReader(name1));
            String curr1;
            while ((curr1 = br1.readLine()) != null) {
                content1.append(curr1);
                content1.append("\n");
            }
            BufferedReader br2 = new BufferedReader(new FileReader(name1));
            String curr2;
            while ((curr2 = br2.readLine()) != null) {
                content2.append(curr2);
                content2.append("\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        int length1 = content1.length();
        if (length1 > 0) content1.deleteCharAt(length1 - 1);
        int length2 = content2.length();
        if (length2 > 0) content2.deleteCharAt(length2 - 1);
        assertEquals(content1.toString(), content2.toString());
    }

    @Test
    public void weld() throws IOException {
        Parser.main(new String[]{"-out", "example.txt", "files/books.txt", "files/list for supermarket.txt"});
        assertFileContent("example.txt","test.txt");
    }
    @Test
    public void unWeld() throws IOException {
        Parser.main(new String[]{"-u", "files/example.txt"});
        assertFileContent("tests/test2.txt","books.txt");
        assertFileContent("tests/test3.txt","list for supermarket.txt");
    }
}
