package workshops.testingil.dirtytests.part2;

import org.junit.jupiter.api.Test;
import workshops.testingil.dirtytests.app.Calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnitTests {

    @Test
    public void test_01(){
        Calculator c = new Calculator();
        c.press("0");
        c.press("1");
        assertTrue(c.getDisplay() == "1");
    }

    @Test
    public void test_5_mul_and_mul()
    {
        Calculator c = new Calculator();
        c.press("5");
        c.press("*");
        c.press("*");
        assertEquals(c.getDisplay(),"5");
    }
}
