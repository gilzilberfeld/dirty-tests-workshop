package workshops.testingil.dirtytests.ex4.abstraction;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import workshops.testingil.dirtytests.exercise.CalculatorParams;
import workshops.testingil.dirtytests.exercise.Routing;

public class CalculatorCalls {

	private final static String LOCAL_HOST = "http://localhost:8888";
	private final static String CALCULATE_URL = LOCAL_HOST + Routing.ROOT + Routing.CALCULATE;
	private final static String RESET_URL = LOCAL_HOST + Routing.ROOT + Routing.RESET;

	public static String calculate(CalculatorParams calcParams) throws Exception {
		return CallUtils.post(CALCULATE_URL, calcParams);
	}


	public static String reset() throws Exception {
		return CallUtils.post(RESET_URL, "");
	}


}
