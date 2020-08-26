package workshop.v1;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * @author Subodh Kumar
 */
@Slf4j
@TestMethodOrder(OrderAnnotation.class)
public class BookEntity1Test {

    private static EntityManagerFactory factory;

    @BeforeAll
    public static void init() {
        factory = Persistence.createEntityManagerFactory("persistence-unit");
        log.info("EntityManagerFactory object created");
    }

    @AfterAll
    public static void destroy() {
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

            BookEntity1 book1 = new BookEntity1();
            book1.setTitle("Programming");
            book1.setTopics(new String[]{"Java", "Python", "JavaScript"});

            entityManager.persist(book1);
            transaction.commit();

            log.info("entity persist successfully");
        } catch (Exception e) {
            log.error("not able to persist", e);
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }

    @Test
    @Order(2)
    public void read() {
        log.info("read() method called");

        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            String query = "SELECT b FROM BookEntity1 b";
            TypedQuery<BookEntity1> typedQuery = entityManager.createQuery(query, BookEntity1.class);

            List<BookEntity1> books = typedQuery.getResultList();

            Assertions.assertEquals(1, books.size());
            books.forEach(book -> log.info(book.toString()));

            log.info("entity loaded successfully");
        } catch (Exception e) {
            log.error("not able to load", e);
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }
}
