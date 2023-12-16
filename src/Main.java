import org.sqlite.core.DB;

import java.sql.Connection;
import java.sql.DriverManager;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        User user1 = new User("Artem", "89172506515");
        User user2 = new User("Kamin", "8917274629");



        DBConnection.connectToDB();
        DBConnection.createTable();
        DBConnection.insertIntoTable(user1);
        DBConnection.insertIntoTable(user2);
        DBConnection.readFromTable();
        DBConnection.deleteFromTable();
        DBConnection.readFromTable();
        DBConnection.dropTable();

    }
}