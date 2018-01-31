package pl.epoint.servlets.dao;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@Singleton
class DatabaseConnectionManager {

    private static final String DATASOURCE_JNDI_NAME = "java:jboss/datasources/Servlets";

    @Getter(AccessLevel.PACKAGE)
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    void init() {
        try {
            InitialContext context = new InitialContext();
            Object dataSourceObj = context.lookup(DATASOURCE_JNDI_NAME);

            DataSource dataSource = checkAndCastDataSource(dataSourceObj);
            this.jdbcTemplate = new JdbcTemplate(dataSource);

        } catch (NamingException e) {
            throw new IllegalStateException("Failed to initialize naming service", e);
        }
    }

    private DataSource checkAndCastDataSource(Object dataSourceObj) {
        if (dataSourceObj == null) {
            throw new IllegalStateException(String.format(
                    "There is no Datasource object mapped on key '%s' in the naming services",
                    DATASOURCE_JNDI_NAME
            ));
        } else if (!(dataSourceObj instanceof DataSource)) {
            throw new IllegalStateException(String.format(
                    "Object mapped on key '%s' is an not instance of javax.sql.DataSource class",
                    DATASOURCE_JNDI_NAME
            ));
        }
        return (DataSource) dataSourceObj;
    }

}
