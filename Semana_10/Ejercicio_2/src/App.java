import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        ArrayList<DatabaseService> listaBBDD = new ArrayList<>();

        listaBBDD.add(new FakeDatabaseService("Fake"));
        listaBBDD.add(new MySQLDatabaseService("Mysql"));
        DatabaseService db = new SQLiteDatabaseService("SQLite");
        listaBBDD.add(db);

        for (DatabaseService databaseService : listaBBDD) {
            // Codigo complejo
            databaseService.connect();
            System.out.println();
        }
    }
}
