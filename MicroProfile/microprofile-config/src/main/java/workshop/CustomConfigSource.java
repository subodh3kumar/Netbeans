package workshop;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.microprofile.config.spi.ConfigSource;

public class CustomConfigSource implements ConfigSource {

    private static final String MOBILE_NUMBER = "876-543-1278";
    private static final String MESSAGE = "Hello, from Jakarta EE 8";

    @Override
    public Map<String, String> getProperties() {
        Map<String, String> map = new HashMap<>();
        map.put("mobile", MOBILE_NUMBER);
        map.put("message", MESSAGE);
        return map;
    }

    @Override
    public int getOrdinal() {
        //return 100;
        return 500;
    }

    @Override
    public String getValue(String key) {
        if (key.equalsIgnoreCase("mobile")) {
            return MOBILE_NUMBER;
        } else if (key.equalsIgnoreCase("message")) {
            return MESSAGE;
        }
        return null;
    }

    @Override
    public String getName() {
        return "random-config-source";
    }
}
