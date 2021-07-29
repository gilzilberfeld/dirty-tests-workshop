package workshops.testingil.dirtytests.ex3.builder;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import workshops.testingil.dirtytests.exercise.CalculatorParams;
import workshops.testingil.dirtytests.exercise.OperationType;
import static workshops.testingil.dirtytests.exercise.OperationType.*;

class TwoOperationTests {

	private CalculatorParams calcParams;
	private TwoOpParamsBuilder builder;
	private String result;

	@BeforeEach
	void setup() {
		builder = new TwoOpParamsBuilder();
	}

	@Test
	void same_order_of_operations() throws Exception {
		calcParams = builder.withArgs(3, 4, 7)
			.calc(Plus)
			.then(Minus)
			.build();

		result = Calls.calculate(calcParams);

		assertThat(result, is("0"));
	}

	@Test
	void order_precedence_division_second() throws Exception {
		calcParams = builder.withArgs(3, 4, 3)
				.calc(Plus)
				.then(Div)
				.build();

		result = Calls.calculate(calcParams);

		assertThat(result, is("5"));
	}

	@Test
	void order_precedence_multiply_first() throws Exception {
		calcParams = builder.withArgs(3, 4, 3)
				.calc(Multiply)
				.then(Minus)
				.build();

		result = Calls.calculate(calcParams);

		assertThat(result, is("10"));
	}

}
