package x.x;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class App {
    public static void main(String[] args) throws Exception {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get("c:/users/x/Desktop/some_test.txt"),
                                                         Charset.forName("UTF-8"))) {
            bw.write("nobody");
            bw.newLine();
            bw.write("does");
            bw.newLine();
            bw.write("it");
            bw.newLine();
            bw.write("better");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
