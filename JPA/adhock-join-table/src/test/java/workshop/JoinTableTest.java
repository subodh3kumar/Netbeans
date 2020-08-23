package workshop;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Subodh Kumar
 */
public class JoinTableTest {

    private static final Logger log = LoggerFactory.getLogger(JoinTableTest.class);
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

        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();

            Person person1 = new Person("subodh", "kumar");
            Person person2 = new Person("juli", "kumari");
            Person person3 = new Person("akash", "kumar");
            Person person4 = new Person("moni", "sharma");

            manager.persist(person1);
            manager.persist(person2);
            manager.persist(person3);
            manager.persist(person4);

            PhoneBook phoneBook1 = new PhoneBook("subodh", "kumar", "45654");
            PhoneBook phoneBook2 = new PhoneBook("juli", "kumari", "56546");
            PhoneBook phoneBook3 = new PhoneBook("akash", "kumar", "67873");

            manager.persist(phoneBook1);
            manager.persist(phoneBook2);
            manager.persist(phoneBook3);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("exception occurred while persisting", e);
        }
        manager.close();
    }

    @Test
    @Disabled
    @Order(2)
    public void testJpaCrossJoin() {
        log.info("testJpaCrossJoin() method called");

        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();

            final String sql = "select p.firstName, p.lastName, pb.phoneNumber from Person p, PhoneBook pb "
                    + "where p.firstName = pb.firstName and p.lastName = pb.lastName";
            log.info("query: {}", sql);

            List<Object[]> result = manager.createQuery(sql).getResultList();

            result.forEach((var obj) -> {
                log.info(String.format("%-15s%-15s%-15s", obj[0], obj[1], obj[2]));
            });
            System.out.println("");

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("exception occurred while loading data", e);
        }
        manager.close();
    }

    @Test
    @Disabled
    @Order(2)
    public void testJpaInnerJoin() {
        log.info("testJpaInnerJoin() method called");

        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();

            final String sql = "select p.firstName, p.lastName, pb.phoneNumber from Person p join PhoneBook pb "
                    + "on p.firstName = pb.firstName and p.lastName = pb.lastName";
            log.info("query: {}", sql);

            List<Object[]> result = manager.createQuery(sql).getResultList();

            result.forEach((var obj) -> {
                log.info(String.format("%-15s%-15s%-15s", obj[0], obj[1], obj[2]));
            });
            System.out.println("");

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("exception occurred while loading data", e);
        }
        manager.close();
    }
    
    @Test
    //@Disabled
    @Order(2)
    public void testJpaLeftJoin() {
        log.info("testJpaLeftJoin() method called");

        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();

            final String sql = "select p.firstName, p.lastName, pb.phoneNumber from Person p left join PhoneBook pb "
                    + "on p.firstName = pb.firstName and p.lastName = pb.lastName";
            log.info("query: {}", sql);

            List<Object[]> result = manager.createQuery(sql).getResultList();

            result.forEach((var obj) -> {
                log.info(String.format("%-15s%-15s%-15s", obj[0], obj[1], obj[2]));
            });
            System.out.println("");

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("exception occurred while loading data", e);
        }
        manager.close();
    }
}
