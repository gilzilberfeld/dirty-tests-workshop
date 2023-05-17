package workshops.testingil.dirtytests.part1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import workshops.testingil.dirtytests.newone.Calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SlowUnitTests {

//    123->123
//            5+C2 ->2
//            1+2*3=->7

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
    @CsvFileSource(resources = "/data.csv")
    public void test_multiple_vals(String in, String out){
        Calculator c = new Calculator();
        in.chars()
           .forEach(ch -> c.press(String.valueOf((char)ch)));
        assertEquals(c.getDisplay(), out);
    }
}

