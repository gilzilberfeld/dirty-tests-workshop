package workshops.testingil.dirtytests.ex1;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

class ResetTests {

	@Test
	void once_resets() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> request = new HttpEntity<String>("", headers);

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.postForObject("http://localhost:8888/root/reset", request, String.class);

		assertThat(result, is("0"));
	}

	@Test
	void twice_returns_E() throws Exception {
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
