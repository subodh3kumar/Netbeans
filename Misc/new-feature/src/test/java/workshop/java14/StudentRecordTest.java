package workshop.java14;


import org.junit.jupiter.api.*;

import java.lang.reflect.RecordComponent;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentRecordTest {

    @Test
    @Order(1)
    public void testDisplayRecord() {
        Student student = new Student("Juli", 23);
        System.out.println(student);
        System.out.println("name: " + student.name());
        System.out.println("age: " + student.age());
    }

    @Test
    @Order(2)
    public void testRecordException() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new Student("Subodh", 30));
        System.out.println(exception.getMessage());
        Assertions.assertEquals("wrong age", exception.getMessage());
    }

    @Test
    @Order(3)
    public void testRecordClass() {
        Student akash = new Student("Akash", 20);
        System.out.println("isRecord ? " + akash.getClass().isRecord());
    }

    @Test
    @Order(4)
    public void testRecordComponents() {
        Student juli = new Student("Juli", 22);
        RecordComponent[] components = juli.getClass().getRecordComponents();
        for (RecordComponent component : components) {
            System.out.println("--> " + component);
        }
    }
}