package workshop;

import java.util.List;
import java.util.Map;

/**
 * @author Subodh Kumar
 */
public class Student {

    public String name;
    public int age;
    public String[] emails;
    public List<String> phones;
    public Map<String, Integer> marks;
    public Address address;

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String[] getEmails() {
        return emails;
    }

    public void setEmails(String[] emails) {
        this.emails = emails;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public Map<String, Integer> getMarks() {
        return marks;
    }

    public void setMarks(Map<String, Integer> marks) {
        this.marks = marks;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" + "name=" + name + ", age=" + age + ", emails=" + emails + ", phones=" + phones + ", marks=" + marks + ", address=" + address + '}';
    }
}
