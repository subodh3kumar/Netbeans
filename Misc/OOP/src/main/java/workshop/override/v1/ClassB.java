package workshop.override.v1;

/**
 * @author Subodh Kumar
 */
public class ClassB extends ClassA {

    @Override
    public void display() {
        System.out.println("class name: " + this.getClass().getName());
    }

    public static void main(String[] args) {
        ClassB classB = new ClassB();
        classB.display();
    }
}
