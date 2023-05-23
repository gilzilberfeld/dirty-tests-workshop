package workshops.testingil.dirtytests.solutions.e05.duplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import workshops.testingil.dirtytests.app.Calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculatorUnitTests {

    private Calculator calculator;

    @BeforeEach
    public void setup() {
        calculator = new Calculator();
    }


    @Test
    public void on_start_display_is_0(){
        assertTrue(calculator.getDisplay() == "0");
    }

    @Test
    public void pressing_1_displays_1(){
        calculator.press("1");
        assertTrue(calculator.getDisplay() == "1");
    }

    @Test
    public void pressing_1C_displays_0()
    {
        calculator.press("1");
        calculator.press("C");
        assertEquals(calculator.getDisplay(),"0");
    }

}
