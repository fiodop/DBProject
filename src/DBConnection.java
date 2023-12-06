import java.sql.*;
import java.util.Scanner;

public class DBConnection {
    public static final String DRIVER_NAME = "org.sqlite.JDBC";
    public static final String DB_FULL_NAME = "jdbc:sqlite:sqlite_users:db";
    public static final String DB_NAME = "users";
    public static final String CREATE_TABLE = "CREATE TABLE '" + DB_NAME + "' (id INTEGER PRIMARY KEY,  name VARCHAR(20), phone_number VARCHAR(11))";
    public static final String READ_FROM_TABLE = "SELECT * FROM \'" + DB_NAME + "\'  ; ";
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
            String name = scanner.nextLine();
            System.out.println("Insert phone number:");
            String phoneNumber = scanner.nextLine();
            String query = "INSERT INTO users (name, phone_number) VALUES ('" + name + "','" + phoneNumber + "')";
            try {
                statement = connection.createStatement();
                statement.execute(query);
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
    public static void readFromTable(){
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(READ_FROM_TABLE);
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String phoneNumber = rs.getString("phone_number");
                System.out.println(id + " " + name + " " + phoneNumber);
                rs.close();
                statement.close();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    /**
    *пока раздумываю как писать удаление из дб
     */
//    public static void deleteTable(){
//        try {
//            statement = connection.createStatement();
//            String query = "DELETE FROM users";
//            statement.execute(query);
//            System.out.println("DB deleted");
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
}
