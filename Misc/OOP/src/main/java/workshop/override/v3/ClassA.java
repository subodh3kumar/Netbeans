package workshop.override.v3;

import java.io.IOException;

/**
 * @author Subodh Kumar
 */
public class ClassA {

    public void display() throws IOException {
        System.out.println("class name: " + this.getClass().getName());
    }
}
