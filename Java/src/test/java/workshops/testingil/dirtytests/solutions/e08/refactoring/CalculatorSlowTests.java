package workshops.testingil.dirtytests.solutions.e08.refactoring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import workshops.testingil.dirtytests.app.Calculator;
import workshops.testingil.dirtytests.solutions.e08.refactoring.helpers.CalculatorTestHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(calculator.getDisplay(),"123");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/slow_unit_test_data.csv")
    public void multiple_presses_display_correctly(String in, String out){
        CalculatorTestHelper.press_all(calculator, in);
        assertEquals(calculator.getDisplay(), out);
    }

}

