package Test1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    @Test
    void testadd(){
        Calculator calc=new Calculator();
        int result=calc.add(2,3);
        assertEquals(5, result);
    }

    @Test
    void testsub(){
        Calculator calc1=new Calculator();
        int result=calc1.subtract(3,1);
        assertEquals(2, result);
    }

    @Test
    void testmul(){
        Calculator calc2=new Calculator();
        int result=calc2.multiply(3,1);
        assertEquals(3, result);
    }

    @Test
    void testdiv(){
        Calculator calc3=new Calculator();
        int result=calc3.divide(3,1);
        assertEquals(3, result);
    }

    @Test
    void testdivbyzero(){
        Calculator calc4=new Calculator();
        assertThrows(IllegalArgumentException.class, ()->{
            calc4.divide(5, 0);
        });
    }

}
