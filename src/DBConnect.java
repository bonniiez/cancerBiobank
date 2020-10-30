import java.sql.*;

public class DBConnect {
    public static void main (String[] args) throws Exception{
        getConnection();
        createTable();
    }

//
//    private static final String SQL_CREATE = "CREATE TABLE Authors"
//            + " au_id char(11) not null,"
//            + " au_lname varchar(40) not null,"
//            + " au_fname varchar(20) not null,"
//            + " primary key (au_id"
//            + ")";

    // test create table
    public static void createTable() throws Exception{

        try{
            Connection connect = getConnection();

//            PreparedStatement ps = connect.prepareStatement("CREATE TABLE Author" + "au_id CHAR(20) NOT NULL");
//
//
//            ps.execute();



            // execute select all statement
            Statement selectQuery = connect.createStatement();
            ResultSet rSet = selectQuery.executeQuery("SELECT * FROM authors");
            while(rSet.next()){
                String lname = rSet.getString("au_lname");
                String fname = rSet.getString("au_fname");
                System.out.print(lname + ", " + fname + "\n ");
            }





        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // returns a connection
    public static Connection getConnection() throws Exception{
        try{

            //connect to the db: https://www.javatpoint.com/example-to-connect-to-the-mysql-database
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/cancerBiobank?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String username = "root";
            String password = "pwdlol";
            Class.forName(driver);

            Connection connect = DriverManager.getConnection(
                    url, username, password
            );



            System.out.println("database connected");

            return connect;


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;


    };


}
