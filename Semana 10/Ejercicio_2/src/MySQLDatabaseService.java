public class MySQLDatabaseService extends DatabaseService{
    public MySQLDatabaseService(String version)
    {
        super("Mysql");
        //MAs cosas de esta clase especifica
    }

    @Override
    public void connect() {
        super.connect();
        System.out.println("Conectando a MySQL");
    }

    
}
