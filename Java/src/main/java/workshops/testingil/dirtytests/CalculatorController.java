package workshops.testingil.dirtytests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (Routing.ROOT)
public class CalculatorController {
	@Autowired private Calculator calculator;
	
	@PostMapping(value =Routing.CALCULATE)
	public ResponseEntity<String> calc(
			@RequestParam(value = "key") CalculatorParams params) {
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
