package workshop.java14;

import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * @author Subodh Kumar
 */
public class NullPointerTest {

    @Test
    public void testNullPointerException() {
        // Add JVM argument as -XX:+ShowCodeDetailsInExceptionMessages
        List<Person> people = List.of(
                new Person("Subodh", new Address("street", new PhoneNumber(123, 12345))),
                new Person("Subodh", new Address("street", new PhoneNumber(123, 12345))),
                new Person("Subodh", new Address("street", new PhoneNumber(231, 12345))),
                new Person("Subodh", new Address("street", new PhoneNumber(123, 12345)))
        );

        people.stream()
                .filter(p -> p.address().phone().areaCode() > 200)
                .forEach(System.out::println);
    }

    public static record Person(String name, Address address) {

        public Person  {
            if (name == null) {
                throw new IllegalArgumentException();
            }
        }
    }

    public static record Address(String street, PhoneNumber phone) {

    }

    public static record PhoneNumber(int areaCode, int number) {

    }
}
