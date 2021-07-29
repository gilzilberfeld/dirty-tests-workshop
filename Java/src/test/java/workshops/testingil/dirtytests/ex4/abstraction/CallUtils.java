package workshops.testingil.dirtytests.ex4.abstraction;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import workshops.testingil.dirtytests.exercise.CalculatorParams;

public class CallUtils {

	static RestTemplate restTemplate;
	static HttpEntity<String> request;
	private static HttpHeaders headers;

	public static String post(String url, Object body) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(body);
	
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		request = new HttpEntity<String>(json, headers);
		restTemplate = new RestTemplate();
		return restTemplate.postForObject(url, request, String.class);
	}

}
