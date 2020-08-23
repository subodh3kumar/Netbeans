package workshop.override.v3;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Subodh Kumar
 */
public class ClassB extends ClassA {

    @Override
    public void display() throws FileNotFoundException {
        System.out.println("class name: " + this.getClass().getName());
    }

    public static void main(String[] args) throws IOException, CustomException {
        ClassB classB = new ClassB();
        classB.display();
    }
}
