package workshops.testingil.dirtytests.ex1;

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

class SingleOperationTests {

	// Check that we can add two numbers
	// and return the right result as a string
	@Test
	void test_add() throws Exception {
		CalculatorParams calcParams = new CalculatorParams();
		calcParams.setFirst(3);
		calcParams.setSecond(4);
		calcParams.setfirstOp(OperationType.Plus);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(calcParams);

		HttpEntity<String> request = new HttpEntity<String>(json, headers);

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.postForObject("http://localhost:8888/root/calculate", request, String.class);

		assertThat(result, is("7"));
	}

	// Check that we can add two numbers
	// and return the right result as a string
	@Test
	void test_add_minus() throws Exception {
		CalculatorParams calcParams = new CalculatorParams();

		calcParams.setFirst(-5);
		calcParams.setSecond(-4);
		calcParams.setfirstOp(OperationType.Plus);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(calcParams);

		HttpEntity<String> request = new HttpEntity<String>(json, headers);

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.postForObject("http://localhost:8888/root/calculate", request, String.class);

		assertThat(result, is("-9"));

	}

	// subtract numbers
	@Test
	void test_minus() throws Exception {
		CalculatorParams calcParams = new CalculatorParams();

		calcParams.setFirst(20);
		calcParams.setSecond(4);
		calcParams.setfirstOp(OperationType.Minus);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(calcParams);

		HttpEntity<String> request = new HttpEntity<String>(json, headers);

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.postForObject("http://localhost:8888/root/calculate", request, String.class);

		assertThat(result, is("16"));
	}

	@Test
	// multiply two numbers
	void test_mul() throws Exception {
		CalculatorParams calcParams = new CalculatorParams();

		calcParams.setFirst(3);
		calcParams.setSecond(6);
		calcParams.setfirstOp(OperationType.Multiply);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(calcParams);

		HttpEntity<String> request = new HttpEntity<String>(json, headers);

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.postForObject("http://localhost:8888/root/calculate", request, String.class);

		assertThat(result, is("18"));
	}
	
	@Test
	// Divide numbers
	void test_div() throws Exception {
		CalculatorParams calcParams = new CalculatorParams();

		calcParams.setFirst(6);
		calcParams.setSecond(3);
		calcParams.setfirstOp(OperationType.Div);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(calcParams);

		HttpEntity<String> request = new HttpEntity<String>(json, headers);

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.postForObject("http://localhost:8888/root/calculate", request, String.class);

		assertThat(result, is("2"));
	}
	
	@Test
	// Divide numbers
	void test_div_errors() throws Exception {
		CalculatorParams calcParams = new CalculatorParams();

		calcParams.setFirst(6);
		calcParams.setSecond(0);
		calcParams.setfirstOp(OperationType.Div);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(calcParams);

		HttpEntity<String> request = new HttpEntity<String>(json, headers);

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.postForObject("http://localhost:8888/root/calculate", request, String.class);

		assertThat(result, is("E"));
	}

	


}
