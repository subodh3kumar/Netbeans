package workshop;

import java.util.stream.IntStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author Subodh Kumar
 */
public class MathToolTest {

    @Test
    public void divideTest() {
        double result = MathTool.divide(3, 4);
        Assertions.assertEquals(.75, result);
    }

    @Test
    public void divideExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> MathTool.divide(3, 0));
    }

    @Test
    public void isEvenTest() {
        Assertions.assertTrue(MathTool.isEven(20));
        Assertions.assertFalse(MathTool.isEven(21));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 2, 4, 6, 8, 10, 100, 1000})
    public void isEvenValueSourceTest(int number) {
        Assertions.assertTrue(MathTool.isEven(number));
    }

    @ParameterizedTest
    @MethodSource(value = "generateEvenNumbers")
    public void isEvenMethodSourceTest(int number) {
        Assertions.assertTrue(MathTool.isEven(number));
    }

    public static IntStream generateEvenNumbers() {
        return IntStream.iterate(0, i -> i + 2).limit(500);
    }
}
