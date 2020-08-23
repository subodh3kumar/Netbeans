package workshop;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Subodh Kumar
 */
public class DateTest {

    private static final Logger log = LoggerFactory.getLogger(DateTest.class);
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
    public void testUtilDateTime() {
        log.info("testUtilDateTime() method called");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        DateUtilEntity entity = new DateUtilEntity();

        entity.setDate(new Date(2020, 7, 11));
        entity.setCalendar(new GregorianCalendar(2020, 7, 11));

        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Test
    public void testSqlDateTime() {
        log.info("testSqlDateTime() method called");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        DateSQLEntity entity = new DateSQLEntity();

        entity.setDate(new java.sql.Date(2020, 7, 11));
        entity.setTime(new java.sql.Time(22, 12, 20));
        entity.setTimestamp(new Timestamp(2020, 7, 11, 10, 13, 10, 0));

        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Test
    public void testNewDateTime() {
        log.info("testSqlDateTime() method called");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Java8DateEntity entity = new Java8DateEntity();

        entity.setLocalDate(LocalDate.now());
        entity.setLocalTime(LocalTime.now());
        entity.setLocalDateTime(LocalDateTime.now());
        entity.setOffsetTime(OffsetTime.now());
        entity.setOffsetDateTime(OffsetDateTime.now());
        entity.setDuration(Duration.ofDays(7));
        entity.setInstant(Instant.now());
        entity.setZonedDateTime(ZonedDateTime.now());

        log.info("zone: " + entity.getZonedDateTime().getZone());

        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }
}
