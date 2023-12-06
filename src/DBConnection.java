import java.sql.*;
import java.util.Scanner;

public class DBConnection {
    static Connection connection;
    static Statement statement;
    public static void connectToDB(){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:sqlite:db");
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
                System.out.print("NEW user added to db");
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
    }
    public static void createTable(){
        try {
            statement = connection.createStatement();
            statement.execute("CREATE TABLE users (id INTEGER PRIMARY KEY,  name VARCHAR(20), phone_number VARCHAR(11))");
            System.out.println("Table created");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void readFromTable(){
        try {
            statement = connection.createStatement();
            String query = "SELECT id, name, phone_number FROM users";
            ResultSet rs = statement.executeQuery(query);
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
    public static void deleteTable(){
        try {
            statement = connection.createStatement();
            String query = "DELETE FROM users";
            statement.execute(query);
            System.out.println("DB deleted");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
