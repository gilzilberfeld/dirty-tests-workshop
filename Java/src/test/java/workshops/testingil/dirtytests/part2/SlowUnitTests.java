package workshops.testingil.dirtytests.part2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import workshops.testingil.dirtytests.newone.Calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SlowUnitTests {
    @ParameterizedTest
    @CsvFileSource(resources = "/slow_unit_test_data_2.csv")
    public void test_multiple_vals_2(String in, String out){
        Calculator c = new Calculator();
        c.pressAll(in);
        assertEquals(c.getDisplay(), out);
    }

}

