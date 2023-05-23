package workshops.testingil.dirtytests.part1;

import org.junit.jupiter.api.Test;
import workshops.testingil.dirtytests.app.Calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnitTests {

    @Test
    public void test_nothing(){
        Calculator c = new Calculator();
        assertTrue(c.getDisplay() == "0");
    }

    @Test
    public void test_1(){
        Calculator c = new Calculator();
        c.press("1");
        assertTrue(c.getDisplay() == "1");
    }

    @Test
    public void test_1_and_C()
    {
        Calculator c = new Calculator();
        c.press("1");
        c.press("C");
        assertEquals(c.getDisplay(),"0");
    }
}
