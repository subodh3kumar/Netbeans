package workshop;

import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtil {

    private static final Logger log = LoggerFactory.getLogger(HibernateUtil.class);
    
    private static final String POSTGRES_DRIVER = "org.postgresql.Driver";
    private static final String POSTGRES_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String POSTGRES_USER = "postgres";
    private static final String POSTGRES_PWD = "root";
    private static final String POSTGRES_DIALECT = "org.hibernate.dialect.PostgreSQL95Dialect";
    
    private static SessionFactory factory;

    public static SessionFactory getSessionFactory() {
        log.info("setting configuration");

        try {
            if (factory == null) {
                Properties prop = new Properties();
                prop.put(Environment.DRIVER, POSTGRES_DRIVER);
                prop.put(Environment.URL, POSTGRES_URL);
                prop.put(Environment.USER, POSTGRES_USER);
                prop.put(Environment.PASS, POSTGRES_PWD);
                prop.put(Environment.DIALECT, POSTGRES_DIALECT);
                prop.put(Environment.SHOW_SQL, "true");
                prop.put(Environment.FORMAT_SQL, "true");
                prop.put(Environment.HBM2DDL_AUTO, "create");

                Configuration configuration = new Configuration();
                configuration.setProperties(prop);
                
                configuration.addAnnotatedClass(Student.class);
                configuration.addAnnotatedClass(Address.class);

                ServiceRegistry registry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();

                log.info("creating SessionFactory");

                factory = configuration.buildSessionFactory(registry);
            }
        } catch (HibernateException e) {
            log.error("Error: ", e);
        }
        return factory;
    }
}
