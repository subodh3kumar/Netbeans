package workshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * @author Subodh Kumar
 */
public class JsonbTest {

    @Test
    @Disabled
    public void testJsonb() {
        Student student = new Student();

        student.setName("Subodh");
        student.setAge(32);

        String[] emails = {"subodh@gmail.com", "munna@gmail.com"};
        student.setEmails(emails);

        List<String> phones = new ArrayList<>();
        phones.add("12345");
        phones.add("78965");
        student.setPhones(phones);

        Map<String, Integer> marks = new HashMap<>();
        marks.put("Computer", 89);
        marks.put("Math", 90);
        student.setMarks(marks);

        Address address = new Address("Chennai", "TN");
        student.setAddress(address);

        Jsonb jsonb = JsonbBuilder.create();
        String serialize = jsonb.toJson(student);
        System.out.println("to json: " + serialize);

        Student copy = jsonb.fromJson(serialize, Student.class);
        System.out.println("from json: " + copy);
    }
    
    @Test
    public void testDeveloper() {
        
        Developer developer = new Developer();
        
        developer.setExceptionType("FAILS");
        developer.setReportFilePath("C:/Development/Files/Input/xlsx");
        developer.setViewName("fails_exception");
        
        SearchCriteria searchCriteria = new SearchCriteria();
        Map<String, String> map = new HashMap<>();
        map.put("$ne", "Closed");
        
        searchCriteria.setExStatus(map);
        developer.setSearchCriteria(searchCriteria);
        
        String msg = JsonbBuilder.create().toJson(developer);
        System.out.println("msg: " + msg);
    }
}
