package workshops.testingil.dirtytests.solutions.e03.consts;

import org.junit.jupiter.api.Test;
import workshops.testingil.dirtytests.app.Calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculatorUnitTests {

    @Test
    public void on_start_display_is_0(){
        Calculator c = new Calculator();
        assertTrue(c.getDisplay() == "0");
    }

    @Test
    public void pressing_1_displays_1(){
        Calculator c = new Calculator();
        c.press("1");
        assertTrue(c.getDisplay() == "1");
    }

    @Test
    public void pressing_1C_displays_0()
    {
        Calculator c = new Calculator();
        c.press("1");
        c.press("C");
        assertEquals(c.getDisplay(),"0");
    }
}
