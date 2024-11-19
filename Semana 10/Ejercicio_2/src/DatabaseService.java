public class DatabaseService {
    private String version;

    public DatabaseService(String version) {
        this.version = version;
    }

    public void connect()
    {
        System.out.println("Conectando con la clase padre");
    }

    String getVersion()
    {
        return version;
    }
}