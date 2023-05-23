package workshops.testingil.dirtytests.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@CrossOrigin()
@RequestMapping("/calc")
public class CalculatorController {

    Calculator calc = new Calculator();


    @PostMapping("/press")
    public void press(@RequestParam String key ){
        System.out.println("-------- received pressed " + key + " -------------------");
        calc.press(key);
    }

    @GetMapping(value = "/display", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getDisplay() throws JsonProcessingException {
        ResultWrapper wrapper = new ResultWrapper();
        wrapper.setDisplay(calc.getDisplay());
        ObjectMapper mapper = new ObjectMapper();

        String response = mapper.writeValueAsString(wrapper);
        System.out.println("-------- getting display " + response + " -------------------");
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/sequence")
    public void pressSequence(@RequestBody String sequenceMessage) throws Exception {
        System.out.println("-------- received sequence " + sequenceMessage + " -------------------");
        JSONObject jsonObject = new JSONObject(sequenceMessage);
        calc.pressAll(jsonObject.getString("sequence"));
    }
    @PostMapping("/reset")
    public void reset(){
        System.out.println("-------- received reset -------------------");
        calc.reset();
    }

    @GetMapping("/")
    public String getRoot(){
        return "Working hard";
    }

}