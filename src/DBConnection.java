import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DBConnection {
    public static final String DRIVER_NAME = "org.sqlite.JDBC";
    public static final String DB_FULL_NAME = "jdbc:sqlite:sqlite_users:db";
    public static final String DB_NAME = "sqlite_users";
    public static final String TABLE_NAME = "users";

    public static final String CREATE_TABLE = "CREATE TABLE '" + TABLE_NAME + "' (id INTEGER PRIMARY KEY,  name VARCHAR(20), phone VARCHAR(11));";
    private static final String SELECT_FROM_TABLE = "SELECT * FROM \'" + TABLE_NAME + "\'  ; ";
    public static final String INSERT_INTO_TABLE = "INSERT INTO '" + TABLE_NAME + "'(name, phone) VALUES (?,?);";
    public static final String DELETE_FOM_TABLE = "DELETE FROM '" + TABLE_NAME + "'WHERE id = ";
    public static String DROP_TABLE = "DROP TABLE '" + TABLE_NAME + "';";

    static Connection connection;
    static Statement statement;
    static PreparedStatement preparedStatement;
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
    public static <user> void insertIntoTable(User u){
        try{
            preparedStatement = connection.prepareStatement(INSERT_INTO_TABLE);
            preparedStatement.setString(1, u.getName());
            preparedStatement.setString(2, u.getPhone());
            preparedStatement.executeUpdate();
            System.out.println("Inserted user is: " + u.getName() + " " + u.getPhone());
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
    public static ArrayList<User> readFromTable(){
        ArrayList arr = new ArrayList();
        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_TABLE);
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                User u = new User(name, phone);
                Collections.addAll(arr, id,  u.getName(), u.getPhone());
            }
            System.out.println(arr.toString());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return arr;
    }

    public static void deleteFromTable(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert id");
        String delete = scanner.nextLine();
        try {
            statement = connection.createStatement();
            statement.executeUpdate(DELETE_FOM_TABLE + delete + " ;");
            System.out.println("Row, where id = '" + delete + "'deleted");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void dropTable(){
        try{
            statement = connection.createStatement();
            statement.execute(DROP_TABLE);
            System.out.println("Table '" + TABLE_NAME + "' dropped");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
