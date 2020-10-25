import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    public static void main (String[] args) throws Exception{
        DBConnect connection = new DBConnect();
        connection.createConnection();
    }

    // method to connect to db
    private void createConnection() {
        try{
            // connect to db: https://www.javatpoint.com/example-to-connect-to-the-mysql-database
            Class.forName("com.mysql.cj.jdbc.Driver"); // driver class
            Connection connect = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/cancerBiobank?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root","pwdlol"
            ); // connection url

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("database connected");
    }
}
