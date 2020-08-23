package workshop;

/**
 * @author Subodh Kumar
 */
public class MathTool {

    public static double divide(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator must not be 0");
        }
        return (double) numerator / (double) denominator;
    }

    public static boolean isEven(int number) {
        return number % 2 == 0;
    }
}
