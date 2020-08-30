package workshop.v3;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
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
public class BookEntity3Test {

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

            BookEntity3 entity = new BookEntity3();
            entity.setTitle("Programming");
            entity.setTopics(new String[]{"Java", "Python", "JavaScript"});

            entityManager.persist(entity);
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

            String sql = "SELECT * FROM book_entity3 b WHERE :topics = ANY(b.topics)";
            Query query = entityManager.createNativeQuery(sql, BookEntity3.class);
            query.setParameter("topics", "Java");

            var books = query.getResultList();

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
