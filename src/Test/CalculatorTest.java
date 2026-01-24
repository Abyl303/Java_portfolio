package Test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CalculatorTest {
    @Test
    void testAdd() {
        Calculator calc = new Calculator();
        int result = calc.sum(2, 3);

        assertEquals(5, result);
    }
}
