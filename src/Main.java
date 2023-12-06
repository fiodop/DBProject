import java.sql.Connection;
import java.sql.DriverManager;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        DBConnection.connectToDB();
        DBConnection.createTable();
        DBConnection.insertToTable();
        DBConnection.readFromTable();

//        try{
//            Class.forName("org.sqlite.JDBC");
//            Connection connection = DriverManager.getConnection("jdbc:sqlite:sqlite.db");
//            System.out.println("DB connected");
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
    }
}