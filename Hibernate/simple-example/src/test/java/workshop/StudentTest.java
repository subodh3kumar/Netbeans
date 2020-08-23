package workshop;

import java.util.List;
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
public class StudentTest {

    private static final Logger log = LoggerFactory.getLogger(StudentTest.class);
    private static SessionFactory factory;

    @BeforeAll
    public static void init() {
        factory = HibernateUtil.getSessionFactory();
    }

    @AfterAll
    public static void destroy() {
        factory.close();
    }

    @Test
    @Order(1)
    public void create() {
        log.info("create() method called.");
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            Student subodh = new Student("subodh", 30, "subodh@java.com");
            Student juli = new Student("juli", 20, "juli@java.com");
            session.save(subodh);
            session.save(juli);
            transaction.commit();
        } catch (Exception e) {
            rollback(transaction);
            log.error("Error: ", e);
        }
    }

    @Test
    @Order(2)
    public void read() {
        log.info("read() method called.");
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            List<Student> students = session.createQuery("from Student s").list();
            students.forEach(System.out::println);
            transaction.commit();
        } catch (HibernateException e) {
            rollback(transaction);
            log.error("Error: ", e);
        }
    }

    @Test
    @Order(3)
    public void update() {
        log.info("update() method called.");
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            Student student = session.find(Student.class, 2);
            System.out.println(student);
            student.setAge(23);
            transaction.commit();
            System.out.println(student);
        } catch (HibernateException e) {
            rollback(transaction);
            log.error("Error: ", e);
        }
    }

    @Test
    @Order(4)
    public void delete() {
        log.info("delete() method called.");
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            Student student = session.load(Student.class, 1);
            System.out.println(student);
            session.delete(student);
            transaction.commit();
            log.info("deleted the student");
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
