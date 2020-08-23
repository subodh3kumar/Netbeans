package workshop.override.v2;

/**
 * @author Subodh Kumar
 */
public class ClassA {

    public void display() throws CustomException {
        System.out.println("class name: " + this.getClass().getName());
    }
}
