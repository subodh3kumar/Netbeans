package workshop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Subodh Kumar
 */
public class PersistenceUtil {

    private static EntityManagerFactory entityManagerFactory;

    public static EntityManagerFactory getEntityManagerFactory() {
        Properties props = new Properties();
        if (entityManagerFactory == null) {
            try {
                props.load(new FileInputStream("/Development/Workspace/Netbeans/JPA/jpa-using-properties-file/src/main/resources/persistence.properties"));
                entityManagerFactory = Persistence.createEntityManagerFactory("persistence-unit", props);
            } catch (IOException ex) {
                Logger.getLogger(PersistenceUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return entityManagerFactory;
    }
}
