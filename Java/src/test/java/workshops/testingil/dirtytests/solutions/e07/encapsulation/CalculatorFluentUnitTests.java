package workshops.testingil.dirtytests.solutions.e07.encapsulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import workshops.testingil.dirtytests.app.Calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorFluentUnitTests {

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
        pressing("1").should_display("1");
    }

    @Test
    public void pressing_1C_displays_0()
    {
        pressing("1")
                .pressing("C")
                .should_display("0");
    }

    private void should_display(String result) {
        assertEquals(calculator.getDisplay(), result);
    }

    private CalculatorFluentUnitTests pressing(String key) {
        calculator.press(key);
        return this;
    }

}
