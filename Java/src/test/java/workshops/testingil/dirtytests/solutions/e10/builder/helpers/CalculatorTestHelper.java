package workshops.testingil.dirtytests.solutions.e10.builder.helpers;

import workshops.testingil.dirtytests.app.Calculator;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorTestHelper {
    public static void press_all(Calculator calc, String sequence) {
        sequence.chars()
           .forEach(ch -> calc.press(String.valueOf((char)ch)));
    }

    public static void should_display(Calculator calculator, String result) {
        assertThat(calculator.getDisplay()).isEqualTo(result);
    }
}
