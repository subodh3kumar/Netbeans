package workshop;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Subodh Kumar
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Duke {

    private String language;
    private int age;

    public Duke() {
    }

    public Duke(String language, int age) {
        this.language = language;
        this.age = age;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Duke {" + "language=" + language + ", age=" + age + '}';
    }
}
