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
public class OneToOneBidirectionalTest {

    private static final Logger log = LoggerFactory.getLogger(OneToOneBidirectionalTest.class);
    private static SessionFactory factory;

    @BeforeAll
    public static void intit() {
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

            Student subodh = new Student("S-100", "Subodh Kumar", "subodh@gmail.com");
            Address subodhAddress = new Address("A-100", "Medavakkam", "Chennai");
            subodh.setAddress(subodhAddress);

            Student juli = new Student("S-101", "Juli Kumari", "juli@gmail.com");
            Address juliAddress = new Address("A-101", "Baisi", "Purnea");
            juli.setAddress(juliAddress);

            session.save(subodh);
            session.save(juli);

            transaction.commit();

            log.info("student saved successfully.");
        } catch (HibernateException e) {
            rollback(transaction);
            log.error("Error: ", e);
        }
    }

    @Test
    @Order(2)
    public void read() {
        log.info("read() method called");
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();

            Student subodh = session.find(Student.class, "S-100");
            Address juliAddress = session.find(Address.class, "A-101");
            transaction.commit();

            Address subohAddress = subodh.getAddress();
            log.info(subodh.toString());
            log.info(subohAddress.toString());

            log.info("--------------");

            Student juli = juliAddress.getStudent();
            log.info(juli.toString());
            log.info(juliAddress.toString());
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
