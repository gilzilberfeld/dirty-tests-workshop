package workshops.testingil.dirtytests;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import testingil.webinar.cleantests.CalculatorParams;
import testingil.webinar.cleantests.Ops;

@SpringBootTest
class DirtytestsApplicationTests {

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
	void test_reset() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> request = new HttpEntity<String>("", headers);

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.postForObject("http://localhost:8888/root/reset", request, String.class);

		assertThat(result, is("0"));
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

	@Test
	// multiple numbers
	void test_mul_numbers() throws Exception {
		CalculatorParams calcParams = new CalculatorParams();

		calcParams.setFirst(3);
		calcParams.setSecond(4);
		calcParams.setSecond(7);
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
	// multiple numbers
	void test_mul_plus() throws Exception {
		CalculatorParams calcParams = new CalculatorParams();

		calcParams.setFirst(3);
		calcParams.setSecond(4);
		calcParams.setSecond(2);
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
	// multiple numbers
	void test_mul_minus() throws Exception {
		CalculatorParams calcParams = new CalculatorParams();

		calcParams.setFirst(3);
		calcParams.setSecond(4);
		calcParams.setSecond(2);
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

	
	@Test
	void test_reset_2() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> request = new HttpEntity<String>("", headers);

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.postForObject("http://localhost:8888/root/reset", request, String.class);

		try {
			result =  restTemplate.postForObject("http://localhost:8888/root/reset", request, String.class);
		} catch (HttpStatusCodeException e) {
			assertThat(e.getRawStatusCode(), is(400));
		}
	}
}
