package workshop.override.v2;

/**
 * @author Subodh Kumar
 */
public class ClassB extends ClassA {

    @Override
    public void display() throws RuntimeException {
        System.out.println("class name: " + this.getClass().getName());
    }

    public static void main(String[] args) {
        ClassB classB = new ClassB();
        classB.display();
    }
}
