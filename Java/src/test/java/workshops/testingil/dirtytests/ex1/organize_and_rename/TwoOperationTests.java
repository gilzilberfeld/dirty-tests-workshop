package workshops.testingil.dirtytests.ex1.organize_and_rename;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import workshops.testingil.dirtytests.exercise.CalculatorParams;
import workshops.testingil.dirtytests.exercise.OperationType;

class TwoOperationTests {

	@Test
	void same_order_of_operations() throws Exception {
		CalculatorParams calcParams = new CalculatorParams();

		calcParams.setFirst(3);
		calcParams.setSecond(4);
		calcParams.setThird(7);
		calcParams.setfirstOp(OperationType.Plus);
		calcParams.setSecondOp(OperationType.Minus);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(calcParams);

		HttpEntity<String> request = new HttpEntity<String>(json, headers);

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.postForObject("http://localhost:8888/root/calculate", request, String.class);

		assertThat(result, is("0"));
	}

	@Test
	void order_precedence_division_second() throws Exception {
		CalculatorParams calcParams = new CalculatorParams();

		calcParams.setFirst(3);
		calcParams.setSecond(4);
		calcParams.setThird(2);
		calcParams.setfirstOp(OperationType.Plus);
		calcParams.setSecondOp(OperationType.Div);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(calcParams);

		HttpEntity<String> request = new HttpEntity<String>(json, headers);

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.postForObject("http://localhost:8888/root/calculate", request, String.class);

		assertThat(result, is("5"));
	}

	@Test
	void order_precedence_multiply_first() throws Exception {
		CalculatorParams calcParams = new CalculatorParams();

		calcParams.setFirst(3);
		calcParams.setSecond(4);
		calcParams.setThird(2);
		calcParams.setfirstOp(OperationType.Multiply);
		calcParams.setSecondOp(OperationType.Minus);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(calcParams);

		HttpEntity<String> request = new HttpEntity<String>(json, headers);

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.postForObject("http://localhost:8888/root/calculate", request, String.class);

		assertThat(result, is("10"));
	}

}
