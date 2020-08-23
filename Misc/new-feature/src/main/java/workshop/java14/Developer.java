package workshop.java14;

import java.util.Map;

public record Developer(String language, int age) {

    public Developer(Map.Entry<String, Integer> entry) {
        this(entry.getKey(), entry.getValue());
    }

    public String message() {
        return "I am a " + this.language + " developer and " + this.age + " year old";
    }
}
