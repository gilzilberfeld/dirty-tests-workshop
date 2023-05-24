package workshops.testingil.dirtytests.solutions.e10.builder;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import workshops.testingil.dirtytests.solutions.e10.builder.helpers.CalculatorUITestHelper;

public class CalculatorUITests {


    private CalculatorUITestHelper testHelper;

    @BeforeEach
    public void setup() {
        testHelper = new CalculatorUITestHelper();
        testHelper.click_on("reset");
    }

    @AfterEach
    public void teardown() {
        testHelper.close();
    }

    @Test
    public void pressing_1plus34_displays34() {
        testHelper.click_on("1");
        testHelper.click_on("plus");
        testHelper.click_on("3");
        testHelper.click_on("4");
        testHelper.click_on("Update");

        testHelper.should_display("34");
    }

    @Test
    public void calculating_6_div_2_displays_3() {
        testHelper.click_on("6");
        testHelper.click_on("div");
        testHelper.click_on("2");
        testHelper.click_on("eql");
        testHelper.click_on("Update");

        testHelper.should_display("3");
    }

    @Test
    public void pressing_123_displays_123() {
        testHelper.click_on("1");
        testHelper.click_on("2");
        testHelper.click_on("3");
        testHelper.click_on("Update");

        testHelper.should_display("123");
    }

    @Test
    public void pressing_5plus3_displays_3() {
        testHelper.click_on("5");
        testHelper.click_on("plus");
        testHelper.click_on("3");
        testHelper.click_on("Update");

        testHelper.should_display("3");
    }

}
