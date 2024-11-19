public class SQLiteDatabaseService extends MySQLDatabaseService{
    

    public SQLiteDatabaseService(String version)
    {
        super("SQLite");
        //MAs cosas de esta clase especifica
    }

    @Override
    public void connect() {
        super.connect();
        System.out.println("Conectando a MySQLite version");
    }
}
