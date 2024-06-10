package workshops.testingil.dirtytests.solutions.e09.more_tests.helpers.more_tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import workshops.testingil.dirtytests.exercise.SequenceMessage;
import workshops.testingil.dirtytests.solutions.e09.more_tests.helpers.more_tests.helpers.CalculatorAPITestHelper;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
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

    @ParameterizedTest
    @CsvFileSource(resources = "/sequence_api_data.csv")
    public void test_multiple_sequences(String sequence, String expected) throws Exception {

        SequenceMessage message = new SequenceMessage();
        message.version = "1.0";
        message.addSequence(sequence);
        message.resetOnError = true;
        ObjectMapper messageMapper = new ObjectMapper();

        String body = messageMapper.writeValueAsString(message);
        testHelper.post_sequence(body);
        should_display(expected);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/slow_unit_test_data_2.csv")
    public void test_slow_sequences(String sequence, String expected) throws Exception {
        SequenceMessage message = new SequenceMessage();
        message.version = "1.0";
        message.addSequence(sequence);
        message.resetOnError = true;
        ObjectMapper messageMapper = new ObjectMapper();

        String body = messageMapper.writeValueAsString(message);
        testHelper.post_sequence(body);
        should_display(expected);
    }

    @Test
    public void press_sequence_5plus2C_and_compare_response_body() throws Exception {
        SequenceMessage message = new SequenceMessage();
        message.version = "1.0";
        message.addSequence("5+2C");
        message.resetOnError = true;
        ObjectMapper messageMapper = new ObjectMapper();

        String body = messageMapper.writeValueAsString(message);
        testHelper.post_sequence(body);


        String result = testHelper.get_display_as_json();
        String json= Files.readString(Paths.get(workshops.testingil.dirtytests.solutions.e09.more_tests.helpers.CalculatorAPITestHelper.REFERENCE_RESULT_JSON), StandardCharsets.UTF_8);

        assertThat(result).isEqualTo(json);

    }
    private void should_display(String number) {
        assertEquals(number, testHelper.get_display());
    }

}
