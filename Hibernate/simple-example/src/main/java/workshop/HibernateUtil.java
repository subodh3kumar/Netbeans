package workshop;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtil {

    private static final Logger log = LoggerFactory.getLogger(HibernateUtil.class);
    private static SessionFactory factory;

    public static SessionFactory getSessionFactory() {
        log.info("setting configuration");
        if (factory == null) {
            Properties prop = new Properties();
            prop.put(Environment.DRIVER, "org.postgresql.Driver");
            prop.put(Environment.URL, "jdbc:postgresql://localhost:5432/postgres");
            prop.put(Environment.USER, "postgres");
            prop.put(Environment.PASS, "root");
            prop.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL95Dialect");
            prop.put(Environment.SHOW_SQL, "true");
            prop.put(Environment.FORMAT_SQL, "true");
            prop.put(Environment.HBM2DDL_AUTO, "create");

            Configuration configuration = new Configuration();
            configuration.setProperties(prop);
            configuration.addAnnotatedClass(Student.class);

            ServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();
            log.info("creating SessionFactory");
            factory = configuration.buildSessionFactory(registry);
        }
        return factory;
    }
}
