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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import workshops.testingil.dirtytests.exercise.CalculatorParams;
import workshops.testingil.dirtytests.exercise.OperationType;

class SingleOperationTests {

	private CalculatorParams calcParams;

	@BeforeEach
	void setup() {
		
	}
	
	@Test
	void caluclate_adding_two_numbers() throws Exception {
		calcParams = new CalculatorParams();
		calcParams.setFirst(3);
		calcParams.setSecond(4);
		calcParams.setfirstOp(OperationType.Plus);

		String result = callCalculate(calcParams);

		assertThat(result, is("7"));
	}


	@Test
	void calculate_adding_negative_numbers() throws Exception {
		CalculatorParams calcParams = new CalculatorParams();

		calcParams.setFirst(-5);
		calcParams.setSecond(-4);
		calcParams.setfirstOp(OperationType.Plus);

		String result = callCalculate(calcParams);

		assertThat(result, is("-9"));

	}

	
	@Test
	void calculate_subtracting_numbers() throws Exception {
		CalculatorParams calcParams = new CalculatorParams();

		calcParams.setFirst(20);
		calcParams.setSecond(4);
		calcParams.setfirstOp(OperationType.Minus);

		String result = callCalculate(calcParams);

		assertThat(result, is("16"));
	}

	@Test
	void calculate_multiplying_numbers() throws Exception {
		CalculatorParams calcParams = new CalculatorParams();

		calcParams.setFirst(3);
		calcParams.setSecond(6);
		calcParams.setfirstOp(OperationType.Multiply);

		String result = callCalculate(calcParams);

		assertThat(result, is("18"));
	}
	
	@Test
	void calculate_division_of_numbers() throws Exception {
		CalculatorParams calcParams = new CalculatorParams();

		calcParams.setFirst(6);
		calcParams.setSecond(3);
		calcParams.setfirstOp(OperationType.Div);

		String result = callCalculate(calcParams);

		assertThat(result, is("2"));
	}
	
	@Test
	void division_by_zero_returns_E() throws Exception {
		CalculatorParams calcParams = new CalculatorParams();

		calcParams.setFirst(6);
		calcParams.setSecond(0);
		calcParams.setfirstOp(OperationType.Div);

		String result = callCalculate(calcParams);

		assertThat(result, is("E"));
	}

	private String callCalculate(CalculatorParams calcParams) throws JsonProcessingException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(calcParams);

		HttpEntity<String> request = new HttpEntity<String>(json, headers);

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.postForObject("http://localhost:8888/root/calculate", request, String.class);
		return result;
	}

}
