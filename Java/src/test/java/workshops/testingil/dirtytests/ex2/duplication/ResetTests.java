package workshops.testingil.dirtytests.ex2.duplication;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpStatusCodeException;

class ResetTests {

	@Test
	void once_resets() throws Exception {
		String result = Calls.reset();
		assertThat(result, is("0"));
	}


	@Test
	void twice_returns_E() throws Exception {
		Calls.reset();
		try {
			Calls.reset();
		} catch (HttpStatusCodeException e) {
			assertThat(e.getRawStatusCode(), is(400));
		}
	}

}
