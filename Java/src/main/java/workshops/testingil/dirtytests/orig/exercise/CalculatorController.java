package workshops.testingil.dirtytests.orig.exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (Routing.ROOT)
public class CalculatorController {
	@Autowired private Calculator calculator;
	
	@PostMapping(value =Routing.CALCULATE)
	public ResponseEntity<String> calc(
			@RequestBody CalculatorParams params) {
		String result = calculator.calculate(params);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping(value = Routing.RESET) 
	public ResponseEntity<String> reset() {
		
		String result = calculator.reset();
		if (result == "E")
			return ResponseEntity.badRequest().body(result);
		return ResponseEntity.ok(result);
	}
	
}
