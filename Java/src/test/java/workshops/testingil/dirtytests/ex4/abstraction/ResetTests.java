package workshops.testingil.dirtytests.ex4.abstraction;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpStatusCodeException;

class ResetTests {

	@Test
	void once_resets() throws Exception {
		String result = CalculatorCalls.reset();
		assertThat(result, is("0"));
	}


	@Test
	void twice_returns_E() throws Exception {
		CalculatorCalls.reset();
		try {
			CalculatorCalls.reset();
			fail();
		} catch (HttpStatusCodeException e) {
			assertThat(e.getRawStatusCode(), is(400));
		}
	}

}
