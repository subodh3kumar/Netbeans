package workshop;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
public class ElementCollectionTest {

    private static final Logger log = LoggerFactory.getLogger(ElementCollectionTest.class);
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

            Student subodh = new Student();
            subodh.setName("Subodh Kumar");

            String[] subodhSubjects = {"Java", "JPA", "Spring"};
            subodh.setSubjects(subodhSubjects);

            List<String> subodhPhones = subodh.getPhoneNumber();
            subodhPhones.add("45678");
            subodh.setPhoneNumber(subodhPhones);

            Set<String> subodhEmails = subodh.getEmails();
            subodhEmails.add("subodh@gmail.com");
            subodhEmails.add("subodh@yahoo.com");
            subodhEmails.add("subodh@outlook.com");
            subodh.setEmails(subodhEmails);

            Map<String, Integer> subodhMarks = subodh.getMarks();
            subodhMarks.put("Java", 80);
            subodhMarks.put("JPA", 90);
            subodhMarks.put("Spring", 75);
            subodh.setMarks(subodhMarks);

            Student juli = new Student();
            juli.setName("Juli Kumari");

            String[] juliSubjects = {"Math", "Science", "Hindi"};
            juli.setSubjects(juliSubjects);

            List<String> juliPhones = juli.getPhoneNumber();
            juliPhones.add("23456");
            juliPhones.add("34567");
            juli.setPhoneNumber(juliPhones);

            Set<String> juliEmails = juli.getEmails();
            juliEmails.add("juli@gmail.com");
            juli.setEmails(juliEmails);

            Map<String, Integer> juliMarks = juli.getMarks();
            juliMarks.put("Math", 80);
            juliMarks.put("Science", 70);
            juliMarks.put("Hindi", 90);
            juli.setMarks(juliMarks);

            session.save(subodh);
            session.save(juli);

            transaction.commit();
            log.info("student record inserted successfully.");
        } catch (HibernateException e) {
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
            Student subodh = session.load(Student.class, 1);
            Student juli = session.load(Student.class, 2);
            log.info(subodh.toString());
            log.info(juli.toString());
            transaction.commit();
        } catch (HibernateException e) {
            rollback(transaction);
            log.error("Error: ", e);
        }
    }

    @Test
    @Order(3)
    public void update() {
        log.info("update() method called");
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();

            Student student = session.load(Student.class, 1);
            log.info(student.toString());

            var subjects = Arrays.asList(student.getSubjects());
            int index = subjects.indexOf("Spring");
            subjects.set(index, "Spring Boot");
            log.info(subjects.toString());
            student.setSubjects(subjects.stream().toArray(String[]::new));

            var phoneNumbers = student.getPhoneNumber();
            index = phoneNumbers.indexOf("45678");
            phoneNumbers.set(index, "45679");
            log.info(phoneNumbers.toString());
            student.setPhoneNumber(phoneNumbers);

            var emails = student.getEmails();
            emails.removeIf(email -> email.equals("subodh@yahoo.com"));
            log.info(emails.toString());
            student.setEmails(emails);

            var marks = student.getMarks();
            marks.entrySet().removeIf(entry -> entry.getKey().equalsIgnoreCase("spring"));
            marks.put("Spring Boot", 75);
            log.info(marks.toString());
            student.setMarks(marks);

            session.update(student);
            transaction.commit();
            log.info("student updated successfully");
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
            Student student = session.find(Student.class, 1);
            log.info(student.toString());
            session.delete(student);
            transaction.commit();
            log.info("student deleted successfully.");
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
