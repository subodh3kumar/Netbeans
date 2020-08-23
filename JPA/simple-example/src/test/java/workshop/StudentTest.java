package workshop;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentTest {

    private static final Logger log = LoggerFactory.getLogger(StudentTest.class);
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

    @Test
    @Order(1)
    public void create() {
        log.info("create() method called");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Student subodh = new Student("Subodh", "subodh@gmail.com", 25);
            Student juli = new Student("Juli", "juli@gmail.com", 23);
            entityManager.persist(subodh);
            entityManager.persist(juli);
            transaction.commit();
            log.info("employee details inserted successfully.");
        } catch (Exception e) {
            rollback(transaction);
            log.error("Error: ", e);
        }
        entityManager.close();
    }

    @Test
    @Order(2)
    public void readAll() {
        log.info("readAll() method called");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            String query = "select stu from Student stu";
            List<Student> students = entityManager.createQuery(query, Student.class).getResultList();
            students.forEach(student -> log.info(student.toString()));
            
        } catch (Exception e) {
            log.error("exception occurred while reading all entities", e);
            rollback(transaction);
        }
    }

    @Test
    @Order(3)
    public void update() {
        log.info("update() method called");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Student student = entityManager.find(Student.class, 1);
            student.setEmail("subodh@java.com");
            transaction.commit();
            log.info(student.toString());
        } catch (Exception e) {
            rollback(transaction);
            log.error("Error: ", e);
        }
        entityManager.close();
    }

    @Test
    @Order(4)
    public void read() {
        log.info("read() method called");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Student student = entityManager.find(Student.class, 1);
            transaction.commit();
            log.info(student.toString());
        } catch (Exception e) {
            rollback(transaction);
            log.error("Error: ", e);
        }
        entityManager.close();
    }

    @Test
    @Order(5)
    public void delete() {
        log.info("delete() method called");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Student student = entityManager.find(Student.class, 2);
            log.info(student.toString());
            entityManager.remove(student);
            transaction.commit();
        } catch (Exception e) {
            rollback(transaction);
            log.error("Error: ", e);
        }
        entityManager.close();
    }

    private void rollback(EntityTransaction transaction) {
        if (transaction != null) {
            transaction.rollback();
        }
    }
}
