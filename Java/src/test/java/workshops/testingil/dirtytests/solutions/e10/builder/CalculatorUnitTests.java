package workshops.testingil.dirtytests.solutions.e10.builder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import workshops.testingil.dirtytests.app.Calculator;
import workshops.testingil.dirtytests.solutions.e10.builder.helpers.CalculatorTestHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorUnitTests {

    private Calculator calculator;

    @BeforeEach
    public void setup() {
        calculator = new Calculator();
    }


    @Test
    public void on_start_display_is_0(){
        should_display("0");
    }

    @Test
    public void pressing_1_displays_1(){
        calculator.press("1");
        should_display("1");
    }

    @Test
    public void pressing_1C_displays_0()
    {
        CalculatorTestHelper.press_all(calculator, "1C");
        should_display("0");
    }

    @Test
    public void pressing_01_displays_1(){
        CalculatorTestHelper.press_all(calculator, "01");
        should_display("1");
    }

    @Test
    public void test_5_mul_and_mul()
    {
        CalculatorTestHelper.press_all(calculator,"5**");
        should_display("5");
    }

    private void should_display(String result) {
        assertEquals(calculator.getDisplay(), result);
    }

}