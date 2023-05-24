package workshops.testingil.dirtytests.solutions.e08.encapsulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import workshops.testingil.dirtytests.app.Calculator;
import workshops.testingil.dirtytests.solutions.e08.encapsulation.helpers.CalculatorTestHelper;

public class CalculatorSlowTests {

    private Calculator calculator;

    @BeforeEach
    public void setup() {
        calculator = new Calculator();
    }

    @Test
    public void pressing_123_displays_123()
    {
        calculator.press("1");
        calculator.press("2");
        calculator.press("3");
        CalculatorTestHelper.should_display(calculator, "123");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/slow_unit_test_data.csv")
    public void multiple_presses_display_correctly(String in, String out){
        CalculatorTestHelper.press_all(calculator, in);
        CalculatorTestHelper.should_display(calculator, out);
    }

}

