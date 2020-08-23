package workshop.java14;

/**
 * @author Subodh Kumar
 */
public class InstanceOf {

    public String patternMatching(Object obj) {
        String result = "";
        if (obj instanceof String name && name.length() > 5) {
            result = "valid name";
        } else {
            result = "not valid name";
        }
        return result;
    }
}
