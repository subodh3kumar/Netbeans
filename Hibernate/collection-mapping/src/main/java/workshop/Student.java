package workshop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyColumn;
import javax.persistence.OrderColumn;

@Entity
class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stu_id", nullable = false, updatable = false)
    private int id;

    @Column
    private String name;

    @OrderColumn(name = "subject_index")
    @ElementCollection
    @CollectionTable(name = "student_subject", joinColumns = {
        @JoinColumn(name = "stu_id", referencedColumnName = "stu_id")
    })
    @Column(name = "subject")
    private String[] subjects;

    @OrderColumn(name = "phone_index")
    @ElementCollection
    @CollectionTable(name = "student_phone", joinColumns = {
        @JoinColumn(name = "stu_id", referencedColumnName = "stu_id")
    })
    @Column(name = "phone_number")
    private List<String> phoneNumber = new ArrayList<>();

    @OrderColumn(name = "email_index")
    @ElementCollection
    @JoinTable(name = "student_email", joinColumns = {
        @JoinColumn(name = "stu_id", referencedColumnName = "stu_id")
    })
    @Column(name = "email")
    private Set<String> emails = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "student_mark", joinColumns = {
        @JoinColumn(name = "stu_id", referencedColumnName = "stu_id")
    })
    @MapKeyColumn(name = "subject")
    @Column(name = "mark")
    Map<String, Integer> marks = new HashMap<>();

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getSubjects() {
        return subjects;
    }

    public void setSubjects(String[] subjects) {
        this.subjects = subjects;
    }

    public List<String> getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(List<String> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<String> getEmails() {
        return emails;
    }

    public void setEmails(Set<String> emails) {
        this.emails = emails;
    }

    public Map<String, Integer> getMarks() {
        return marks;
    }

    public void setMarks(Map<String, Integer> marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", subjects=" + Arrays.toString(subjects) + ", phoneNumber=" + phoneNumber + ", emails=" + emails + ", marks=" + marks + '}';
    }
}
