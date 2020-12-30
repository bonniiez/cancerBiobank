package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;


public class DBConnect {
    private static final String EXCEPTION_TAG = "[EXCEPTION]";

    private Connection connection = null;
    private String mySqlDriver = "com.mysql.cj.jdbc.Driver";
    private String mySqlUrl = "jdbc:mysql://localhost:3306/cancerBiobank?useUnicode=" +
            "true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";


    public DBConnect(){
        try{
//            getConnection();
            //load the JDBC driver, note that the path could change for new drivers

            Class.forName(mySqlDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // test create table
    public static void createTable() throws Exception{
        String pathToSQL = "src/sql/cancerBiobank.sql";

        try{
            Connection connect = getConnection();

            // execute select all statement
            Statement selectQuery = connect.createStatement();

            // execute SQL statements
            executeFile(connect, new File(pathToSQL));


            //insert statement
//            selectQuery.executeUpdate("INSERT INTO authors VALUES" + "('409-56-9203', 'John', 'Doe',\n" + "'415 234-2345', '9323 Doe St.', 'Dou', 'CA', '25123')");

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

    public boolean login(String username, String password){
        try{
            if (connection != null){
                connection.close();
            }

            connection = DriverManager.getConnection(mySqlUrl, username, password);
           connection.setAutoCommit(false);

            System.out.println("\n Connected to mySql");

            return true;

        }catch (SQLException e){
            System.out.print(EXCEPTION_TAG);
            return false;
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

    // executes statements from a file

    public static void executeFile(Connection connection, File file) throws IOException, SQLException {
        String fileName = file.getName();
        int statementCount = 0;
        int failureCount = 0;
        String failures="";

        System.out.printf("Executing file %s.\n", fileName);
        try (FileReader fReader = new FileReader(file);
             BufferedReader reader = new BufferedReader(fReader)) {
            StringBuilder buf = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                buf.append(line);
            }

            String[] statementStrings = buf.toString().split(";");


            for (String statementString : statementStrings) {
                String trimString = statementString.trim();
                if (trimString.isEmpty()) {
                    continue; // Skip empty lines
                }

                Statement statement = connection.createStatement();
                try {
                    statement.execute(trimString);
                    statementCount++;
                }catch (SQLException e){
                    failures += "\n On line:"+trimString+e.getMessage()+"\n";
                    failureCount++;
                }
            }

            System.out.printf("%d statements executed, %d exceptions from file %s.\n", statementCount,failureCount, fileName);
        } catch (SQLException e) {
            System.out.printf("%d statements executed from file %s before exception.\n", statementCount, fileName);
            throw e;
        }
        if(!failures.equals("")){
            failures = "Encountered "+failureCount+" exception(s) during execution. \n"+failures;
            throw new SQLException(failures);
        }
    }






}
