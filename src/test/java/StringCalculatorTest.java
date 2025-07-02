import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class StringCalculatorTest {
    @Test
    public void testAddEmptyString() {
        StringCalculator calc = new StringCalculator();
        assertEquals(0, calc.add(""));
    }

    @Test
    public void testAddOneNumberString() {
        StringCalculator calc = new StringCalculator();
        assertEquals(1, calc.add("1"));
    }

    @Test
    public void testAddTwoNumbersString() {
        StringCalculator calc = new StringCalculator();
        assertEquals(5, calc.add("2,3"));
    }

    @Test
    public void testAddNumbersStringWithSpace() {
        StringCalculator calc = new StringCalculator();
        assertEquals(6, calc.add("1, 2, 3"));
    }

    @Test
    public void testAddManyNumbersWithSpace() {
        StringCalculator calc = new StringCalculator();
        assertEquals(15, calc.add("1,2,3,4,5"));
    }

    @Test
    public void testAddNoneNumberString() {
        StringCalculator calc = new StringCalculator();
        assertThrows(IllegalArgumentException.class, () -> calc.add("a,b"));
    }

    @Test
    public void testAddNumberStringWithBackslashN() {
        StringCalculator calc = new StringCalculator();
        assertEquals(6, calc.add("1\n2,3"));
    }


}