package workshop;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@TestMethodOrder(OrderAnnotation.class)
public class OneToOneSharedPrimaryKeyTest {

    private static final Logger log = LoggerFactory.getLogger(OneToOneSharedPrimaryKeyTest.class);
    private static SessionFactory factory;

    @BeforeAll
    public static void init() {
        factory = HibernateUtil.getSessionFactory();
        log.info("SessionFactory created successfully.");
    }

    @AfterAll
    public static void close() {
        factory.close();
        log.info("SessionFactory closed successfully.");
    }

    @Test
    @Order(1)
    public void create() {
        log.info("create() method called");
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();

            Student subodh = new Student("Subodh Kumar", "subodh@gmail.com");
            Address subodhAddress = new Address("Medavakkam", "Chennai");
            subodh.setAddress(subodhAddress);
            session.save(subodh);

            Student juli = new Student("Juli Kumari", "juli@gmail.com");
            Address juliAddress = new Address("Baisi", "Purnea");
            juli.setAddress(juliAddress);
            session.save(juli);

            transaction.commit();
            log.info("entity saved successfully");
        } catch (HibernateException e) {
            rollback(transaction);
            log.error("Error: ", e);
        }
    }

    private void rollback(Transaction transaction) {
        if (transaction != null) {
            transaction.rollback();
        }
    }
}
