package workshop.java14;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import workshop.util.Constants;
import workshop.util.Month;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SwitchExpressionTest implements Constants {

    private static SwitchExpression obj;

    @BeforeAll
    public static void init() {
        obj = new SwitchExpression();
    }

    @AfterAll
    public static void destroy() {
        obj = null;
    }

    @Test
    public void traditionalSwitch() {
        assertEquals(6, obj.traditionalSwitch(SUNDAY));
        assertEquals(7, obj.traditionalSwitch(TUESDAY));
        assertEquals(8, obj.traditionalSwitch(THURSDAY));
        assertEquals(9, obj.traditionalSwitch(WEDNESDAY));

        Exception exception = assertThrows(IllegalStateException.class, () -> obj.traditionalSwitch("hello"));
        assertEquals("Unexpected value", exception.getMessage());
        System.out.println("----------");
    }

    @Test
    public void newSwitchStatement() {
        assertEquals(6, obj.newSwitchStatement(MONDAY));
        assertEquals(7, obj.newSwitchStatement(TUESDAY));
        assertEquals(8, obj.newSwitchStatement(SATURDAY));
        assertEquals(9, obj.newSwitchStatement(WEDNESDAY));

        Exception exception = assertThrows(IllegalStateException.class, () -> obj.newSwitchStatement("hello"));
        assertEquals("Unexpected value", exception.getMessage());
        System.out.println("----------");
    }

    @Test
    public void testTraditionalSwitchWithYield() {
        assertEquals(FIRST_QUARTER, obj.traditionalSwitchWithYield(Month.FEBRUARY));
        assertEquals(SECOND_QUARTER, obj.traditionalSwitchWithYield(Month.MAY));
        assertEquals(THIRD_QUARTER, obj.traditionalSwitchWithYield(Month.AUGUST));
        assertEquals(FOURTH_QUARTER, obj.traditionalSwitchWithYield(Month.DECEMBER));
        assertEquals(UNKNOWN, obj.traditionalSwitchWithYield(Month.UNKNOWN));
        System.out.println("----------");
    }

    @Test
    public void testSwitchWithYield() {
        assertEquals(FIRST_QUARTER, obj.traditionalSwitchWithYield(Month.JANUARY));
        assertEquals(SECOND_QUARTER, obj.traditionalSwitchWithYield(Month.APRIL));
        assertEquals(THIRD_QUARTER, obj.traditionalSwitchWithYield(Month.JULY));
        assertEquals(FOURTH_QUARTER, obj.traditionalSwitchWithYield(Month.OCTOBER));
        assertEquals(UNKNOWN, obj.traditionalSwitchWithYield(Month.UNKNOWN));
        System.out.println("----------");
    }

    @Test
    public void testTraditionalSwitchWithoutBreak() {
        assertEquals(100, obj.traditionalSwitchWithoutBreak(1));
        assertEquals(100, obj.traditionalSwitchWithoutBreak(2));
        assertEquals(100, obj.traditionalSwitchWithoutBreak(3));
        assertEquals(100, obj.traditionalSwitchWithoutBreak(4));
        System.out.println("----------");
    }
}
