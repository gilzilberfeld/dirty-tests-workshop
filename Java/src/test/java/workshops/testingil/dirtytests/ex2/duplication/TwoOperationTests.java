package workshops.testingil.dirtytests.ex2.duplication;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import workshops.testingil.dirtytests.exercise.CalculatorParams;
import workshops.testingil.dirtytests.exercise.OperationType;

class TwoOperationTests {

	private CalculatorParams calcParams;
	private String result;

	@BeforeEach
	void setup() {
		calcParams = new CalculatorParams();
	}

	@Test
	void same_order_of_operations() throws Exception {
		calcParams.setFirst(3);
		calcParams.setSecond(4);
		calcParams.setThird(7);
		calcParams.setfirstOp(OperationType.Plus);
		calcParams.setSecondOp(OperationType.Minus);

		result = Calls.calculate(calcParams);

		assertThat(result, is("0"));
	}

	@Test
	void order_precedence_division_second() throws Exception {
		calcParams.setFirst(3);
		calcParams.setSecond(4);
		calcParams.setThird(2);
		calcParams.setfirstOp(OperationType.Plus);
		calcParams.setSecondOp(OperationType.Div);

		result = Calls.calculate(calcParams);

		assertThat(result, is("5"));
	}

	@Test
	void order_precedence_multiply_first() throws Exception {
		calcParams.setFirst(3);
		calcParams.setSecond(4);
		calcParams.setThird(2);
		calcParams.setfirstOp(OperationType.Multiply);
		calcParams.setSecondOp(OperationType.Minus);

		result = Calls.calculate(calcParams);

		assertThat(result, is("10"));
	}

}
