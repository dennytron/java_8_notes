package x.x;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class App {
    public static void main(String[] args) throws Exception {
        String sql = "SELECT * FROM neighborhoods.maponics_nbrs_20 LIMIT 1;";
        String connString = "jdbc:postgresql://host:5432/database?ApplicationName=app&user=denny&password=denny";

        try (Connection connect = DriverManager.getConnection(connString);
             PreparedStatement statement = connect.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            
            while (rs.next()) {
                Object name = rs.getObject(1);
                System.out.println("Name: " + name);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
