package workshop.override.v1;

/**
 * @author Subodh Kumar
 */
public class ClassA {

    public void display() {
        System.out.println("class name: " + this.getClass().getName());
    }

    public static void main(String[] args) {
        ClassA classA = new ClassA();
        classA.display();
    }
}
