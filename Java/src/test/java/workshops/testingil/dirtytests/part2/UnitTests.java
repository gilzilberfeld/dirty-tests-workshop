package workshops.testingil.dirtytests.part2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import workshops.testingil.dirtytests.app.Calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnitTests {

    @Test
    public void test_01(){
        Calculator c = new Calculator();
        c.press("0");
        c.press("1");
        assertTrue(c.getDisplay() == "1");
    }

    @Test
    public void test_5_mul_and_mul()
    {
        Calculator c = new Calculator();
        c.press("5");
        c.press("*");
        c.press("*");
        assertEquals(c.getDisplay(),"5");
    }

    @Test
    public void temp() throws JSONException, JsonProcessingException {

        SequenceMessageHelper message = new SequenceMessageHelper();
        message.version = "1.0";
        message.addSequence("5+2C");
        message.resetOnError = true;
        ObjectMapper messageMapper = new ObjectMapper();

        String body = messageMapper.writeValueAsString(message);

        var sequenceMessage = "{\"data\":\"{\\\"version\\\":\\\"1.0\\\",\\\"sequence\\\":[\\\"5\\\",\\\"+\\\",\\\"2\\\",\\\"C\\\"],\\\"resetOnError\\\":true}\"} ";
        JSONObject jsonObject = new JSONObject(sequenceMessage);

        String data = jsonObject.getString("data");
        JSONObject jsonObject1 = new JSONObject(data);

        assertEquals("5+2C", jsonObject1.getString("sequence"));

    }
}
