package workshops.testingil.dirtytests.ex2.duplication;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import workshops.testingil.dirtytests.exercise.CalculatorParams;

public class Calls {

	private static RestTemplate restTemplate;
	private static HttpEntity<String> request;
	private static HttpHeaders headers;

	public static String calculate(CalculatorParams calcParams) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(calcParams);
	
		initializeRequestObjects(json);
		return restTemplate.postForObject("http://localhost:8888/root/calculate", request, String.class);
	}


	public static String reset() {
		initializeRequestObjects("");
		return restTemplate.postForObject("http://localhost:8888/root/reset", request, String.class);
	}

	private static void initializeRequestObjects(String body) {
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		request = new HttpEntity<String>(body, headers);
		restTemplate = new RestTemplate();
	}

}
