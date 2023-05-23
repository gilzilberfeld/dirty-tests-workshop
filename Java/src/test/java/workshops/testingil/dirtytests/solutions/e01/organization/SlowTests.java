package workshops.testingil.dirtytests.solutions.e01.organization;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import workshops.testingil.dirtytests.app.Calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SlowTests {

    @Test
    public void test_1_2_3()
    {
        Calculator c = new Calculator();
        c.press("1");
        c.press("2");
        c.press("3");
        assertEquals(c.getDisplay(),"123");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/slow_unit_test_data.csv")
    public void test_multiple_vals(String in, String out){
        Calculator c = new Calculator();
        in.chars()
           .forEach(ch -> c.press(String.valueOf((char)ch)));
        assertEquals(c.getDisplay(), out);
    }
}

