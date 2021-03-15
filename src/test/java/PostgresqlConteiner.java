import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresqlConteiner extends PostgreSQLContainer<PostgresqlConteiner> {

    private static final String IMAGE_VERSION = "postgres:11.1";
    private static PostgresqlConteiner conteiner;

    private PostgresqlConteiner() {
        super(IMAGE_VERSION);
    }

    public static PostgresqlConteiner getInstance() {
        if (conteiner == null) {
            conteiner = new PostgresqlConteiner();
        }
        return conteiner;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", conteiner.getJdbcUrl());
        System.setProperty("DB_USERNAME", conteiner.getUsername());
        System.setProperty("DB_PASSWORD", conteiner.getPassword());

    }

    @Override
    public void stop() {

    }
}
