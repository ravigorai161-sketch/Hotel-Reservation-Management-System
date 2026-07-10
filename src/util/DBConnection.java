package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/hotel_management";

    private static final String USER = "root";

    // Replace with your MySQL password
    private static final String PASSWORD = "ravi@120";

    public static Connection getConnection() {

        try {

            Connection con = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

            System.out.println("Database Connected Successfully!");

            return con;

        } catch (SQLException e) {

            System.out.println("Connection Failed!");
            e.printStackTrace();
        }

        return null;
    }
}

