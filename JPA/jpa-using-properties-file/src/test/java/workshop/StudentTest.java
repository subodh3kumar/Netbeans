package workshop;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * @author Subodh Kumar
 */
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentTest {

    private static EntityManagerFactory factory;

    @BeforeAll
    public static void init() {
        factory = PersistenceUtil.getEntityManagerFactory();
    }

    @AfterAll
    public static void close() {
        if (factory != null) {
            factory.close();
        }
    }

    @Test
    @Order(1)
    public void create() {
        System.out.println("create() method called");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Student subodh = new Student(1001, "subodh ", "kumar");
            Student juli = new Student(1002, "juli ", "kumari");
            entityManager.persist(subodh);
            entityManager.persist(juli);
            transaction.commit();
            System.out.println("employee details inserted successfully.");
        } catch (Exception e) {
            transaction.rollback();
            System.err.println("ERROR: " + e);
        }
        entityManager.close();
    }

    @Test
    @Order(2)
    public void read() {
        System.out.println("read() method called");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            String query = "select s from Student s";
            List<Student> students = entityManager.createQuery(query, Student.class).getResultList();
            System.out.println("size of student list: " + students.size());
            students.forEach(System.out::println);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.err.println("ERROR: " + e);
        }
        entityManager.close();
    }
}
