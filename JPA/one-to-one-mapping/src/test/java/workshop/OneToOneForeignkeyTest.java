package workshop;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OneToOneForeignkeyTest {

    private static final Logger log = LoggerFactory.getLogger(OneToOneForeignkeyTest.class);
    private static EntityManagerFactory factory;

    @BeforeAll
    public static void init() {
        factory = Persistence.createEntityManagerFactory("persistence-unit");
        log.info("EntityManagerFactory object created");
    }

    @AfterAll
    public static void close() {
        factory.close();
        log.info("EntityManagerFactory object closed");
    }
}
