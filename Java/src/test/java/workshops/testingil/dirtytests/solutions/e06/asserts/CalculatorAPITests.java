package workshops.testingil.dirtytests.solutions.e06.asserts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import workshops.testingil.dirtytests.solutions.e06.asserts.helpers.CalculatorAPITestHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorAPITests {


    private CalculatorAPITestHelper testHelper;
    
    @BeforeEach
    public void setup() {
        testHelper = new CalculatorAPITestHelper(CalculatorAPITestHelper.URL);
        testHelper.post_reset();
    }

    @Test
    public void on_start_show_0() {

        testHelper.post_press("0");
        should_display("0");
    }

    @Test
    @DisplayName("When pressing 1+ displays 1")
    public void pressing_1_plus_displays_1() {
        testHelper.post_press("1");
        testHelper.post_press(testHelper.encode("+"));
        should_display("1");
    }

    @Test
    @DisplayName("When pressing +2 displays 2")
    public void pressing_plus_2_displays_2() {
        testHelper.post_press(testHelper.encode("+"));
        testHelper.post_press("2");
        should_display("2");
    }

    @Test
    @DisplayName("0 => 0")
    public void pressing_0_displays_0() {
        testHelper.post_press("0");
        testHelper.post_press("0");
        should_display("0");
    }

    private void should_display(String number) {
        assertEquals(number, testHelper.get_display());
    }

}
