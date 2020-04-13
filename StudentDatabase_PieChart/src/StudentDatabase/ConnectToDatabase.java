package StudentDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToDatabase {

    public static Connection getConnection() {
        try {
            Connection c = null;
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/student?useSSL=false";
            String user = "root";
            String password = "yankees";
//			I love the Yankees.
            c = DriverManager.getConnection(url, user, password);

            if (c != null) {
//                System.out.println("!WE HAVE ESTABLISHED A CONNECTION!");
            }
            return c;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();

        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;

    }
}
