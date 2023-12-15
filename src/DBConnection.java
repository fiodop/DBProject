import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DBConnection {
    public static final String DRIVER_NAME = "org.sqlite.JDBC";
    public static final String DB_FULL_NAME = "jdbc:sqlite:sqlite_users:db";
    public static final String DB_NAME = "users";
    public static final String CREATE_TABLE = "CREATE TABLE '" + DB_NAME + "' (id INTEGER PRIMARY KEY,  name VARCHAR(20), phone_number VARCHAR(11))";
    public static final String READ_FROM_TABLE = "SELECT * FROM \'" + DB_NAME + "\'  ; ";
    public static String name;
    public static String phoneNumber;
    public static final String INSERT_INTO_TABLE = "INSERT INTO '" + DB_NAME + "' (name, phone_number) VALUES ('\" + '" + name + "'\"','\" + '" + phoneNumber + "' \")";
    public static final String DELETE_FOM_TABLE = "DELETE FROM '" + DB_NAME + "'WHERE id = ";
    static Connection connection;
    static Statement statement;
    public static void connectToDB(){
        try {
            Class.forName(DRIVER_NAME);
            connection = DriverManager.getConnection(DB_FULL_NAME);
            System.out.println("DB connected");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    public static void disconnect(){
        try {
            connection.close();
            System.out.println("DB disconnected");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    public static void insertToTable(){
        Scanner scanner = new Scanner(System.in);
            System.out.println("Insert name:");
            name = scanner.nextLine();
            System.out.println("Insert phone number:");
            phoneNumber = scanner.nextLine();
            try {
                statement = connection.createStatement();
                statement.execute(INSERT_INTO_TABLE);
                System.out.println("NEW user added to db");
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
    }
    public static void createTable(){
        try {
            statement = connection.createStatement();
            statement.execute(CREATE_TABLE);
            System.out.println("Table created");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static List readFromTable(){
        ArrayList arr = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(READ_FROM_TABLE);
            while (rs.next()){
                String name = rs.getString("name");
                String phoneNumber = rs.getString("phone_number");
                Collections.addAll(arr, name, phoneNumber);
                rs.close();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return arr;
    }
    public static void deleteFromTable(){
        try {
            statement = connection.createStatement();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Insert id");
            String delete = scanner.nextLine();
            statement.execute(DELETE_FOM_TABLE + delete);
            System.out.println("Row, where id = '" + delete + "'deleted");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
