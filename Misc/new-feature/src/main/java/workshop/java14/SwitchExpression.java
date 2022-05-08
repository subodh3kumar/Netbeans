package workshop.java14;

import workshop.util.Month;
import static workshop.util.Constants.*;

public class SwitchExpression {

    public int traditionalSwitch(String day) {
        int result = 0;
        switch (day) {
            case MONDAY:
            case FRIDAY:
            case SUNDAY:
                result = 6;
                break;
            case TUESDAY:
                result = 7;
                break;
            case THURSDAY:
            case SATURDAY:
                result = 8;
                break;
            case WEDNESDAY:
                result = 9;
                break;
            default:
                throw new IllegalStateException("Unexpected value");
        }
        System.out.println("result: " + result);
        return result;
    }

    public int traditionalSwitchWithoutBreak(int num) {
        int result = 0;
        switch (num) {
            case 1:
            case 2:
                result = 6;
            case 3:
                result = 7;
            case 4:
                result = 8;
            default:
                result = 100;
        }
        System.out.println("result: " + result);
        return result;
    }

    public int newSwitchStatement(String day) {
        int result = switch (day) {
            case MONDAY, FRIDAY, SUNDAY ->
                6;
            case TUESDAY ->
                7;
            case THURSDAY, SATURDAY ->
                8;
            case WEDNESDAY ->
                9;
            default ->
                throw new IllegalStateException("Unexpected value");
        };
        System.out.println("result: " + result);
        return result;
    }

    public String traditionalSwitchWithYield(Month month) {
        String result = switch (month) {
            case JANUARY:
            case FEBRUARY:
            case MARCH:
                yield FIRST_QUARTER;
            case APRIL:
            case MAY:
            case JUNE:
                yield SECOND_QUARTER;
            case JULY:
            case AUGUST:
            case SEPTEMBER:
                yield THIRD_QUARTER;
            case OCTOBER:
            case NOVEMBER:
            case DECEMBER:
                yield FOURTH_QUARTER;
            default:
                yield UNKNOWN;
        };
        System.out.println("result: " + result);
        return result;
    }

    public String switchWithYield(Month month) {
        String result = switch (month) {
            case JANUARY, FEBRUARY, MARCH:
                yield FIRST_QUARTER;
            case APRIL, MAY, JUNE:
                yield SECOND_QUARTER;
            case JULY, AUGUST, SEPTEMBER:
                yield THIRD_QUARTER;
            case OCTOBER, NOVEMBER, DECEMBER:
                yield FOURTH_QUARTER;
            default:
                yield UNKNOWN;
        };
        System.out.println("result: " + result);
        return result;
    }
}
