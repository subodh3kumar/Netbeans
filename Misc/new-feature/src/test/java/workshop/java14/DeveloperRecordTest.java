package workshop.java14;

import org.junit.jupiter.api.Test;

import java.util.Map;

public class DeveloperRecordTest {

    @Test
    public void entrySetWithRecord() {
        var developers = Map.of("java", 25, "javascript", 24, "ruby", 30);

        developers.entrySet()
                .stream()
                .map(Developer::new)
                .forEach(System.out::println);

        System.out.println("------------");

        developers.entrySet()
                .stream()
                .map(Developer::new)
                .map(Developer::message)
                .forEach(System.out::println);
    }
}
