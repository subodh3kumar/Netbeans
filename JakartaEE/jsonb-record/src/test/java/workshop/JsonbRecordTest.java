package workshop;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import org.junit.jupiter.api.Test;

/**
 * @author Subodh Kumar
 */
public class JsonbRecordTest {

    @Test
    public void testJsonbWithRecord() {
        Jsonb jsonb = JsonbBuilder.create();
        var developer = new Developer("java", 25);
        var serialized = jsonb.toJson(developer);
        System.out.println("serialized: " + serialized);
        var clone = jsonb.fromJson(serialized, Developer.class);
        System.out.println("clone: " + clone);
    }
}
