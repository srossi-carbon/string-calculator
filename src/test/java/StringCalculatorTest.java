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
    public void testAddNoneNumberString() {
        StringCalculator calc = new StringCalculator();
       assertThrows(IllegalArgumentException.class, () -> calc.add("a,b"));
    }


}