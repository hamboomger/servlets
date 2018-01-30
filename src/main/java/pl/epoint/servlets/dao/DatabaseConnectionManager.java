package pl.epoint.servlets.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DatabaseConnectionManager {

    private static final String DATASOURCE_JNDI_NAME = "java:jboss/datasources/Servlets";

    private static DatabaseConnectionManager singleton;

    private final JdbcTemplate jdbcTemplate;

    public static DatabaseConnectionManager get() {
        if(singleton == null) {
            singleton = new DatabaseConnectionManager();
        }

        return singleton;
    }

    private DatabaseConnectionManager() {
        try {
            InitialContext context = new InitialContext();
            Object dataSourceObj = context.lookup(DATASOURCE_JNDI_NAME);

            DataSource dataSource = checkAndCastDataSource(dataSourceObj);
            this.jdbcTemplate = new JdbcTemplate(dataSource);

        } catch (NamingException e) {
            throw new IllegalStateException("Failed to initialize naming service", e);
        }
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    private DataSource checkAndCastDataSource(Object dataSourceObj) {
        if(dataSourceObj == null) {
            throw new IllegalStateException(String.format(
                    "There is no Datasource object mapped on key '%s' in the naming services",
                    DATASOURCE_JNDI_NAME
            ));
        } else if(!(dataSourceObj instanceof DataSource)) {
            throw new IllegalStateException(String.format(
                    "Object mapped on key '%s' is an not instance of javax.sql.DataSource class",
                    DATASOURCE_JNDI_NAME
            ));
        }
        return (DataSource)dataSourceObj;
    }

}
