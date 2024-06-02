package workshops.testingil.dirtytests.solutions.e08.refactoring;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import workshops.testingil.dirtytests.solutions.e08.refactoring.helpers.CalculatorUITestHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorUITests {


    private CalculatorUITestHelper testHelper;
    
    @BeforeEach
    public void setup() {
        testHelper = new CalculatorUITestHelper();
    }

    @AfterEach
    public void teardown()    {
        testHelper.close();
    }

    @Test
    public void pressing_1plus34_displays34() {
        testHelper.click_on("reset");
        testHelper.click_on("1");
        testHelper.click_on("plus");
        testHelper.click_on("3");
        testHelper.click_on("4");
        testHelper.click_on("Update");

        String result = testHelper.read_display();
        assertEquals("34", result);
    }

    @Test
    public void calculating_6_div_2_displays_3() {

        testHelper.click_on("reset");
        testHelper.click_on("6");
        testHelper.click_on("div");
        testHelper.click_on("2");
        testHelper.click_on("eql");
        testHelper.click_on("Update");

        String result = testHelper.read_display();
        assertEquals("3", result);
    }

}
