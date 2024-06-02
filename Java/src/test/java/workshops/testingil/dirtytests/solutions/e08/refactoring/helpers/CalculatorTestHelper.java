package workshops.testingil.dirtytests.solutions.e08.refactoring.helpers;

import workshops.testingil.dirtytests.app.Calculator;

public class CalculatorTestHelper {
    public static void press_all(Calculator calc, String sequence) {
        sequence.chars()
           .forEach(ch -> calc.press(String.valueOf((char)ch)));
    }
}
