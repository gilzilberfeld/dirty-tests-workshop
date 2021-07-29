package workshops.testingil.dirtytests.ex4.abstraction;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import workshops.testingil.dirtytests.exercise.CalculatorParams;
import workshops.testingil.dirtytests.exercise.OperationType;

class SingleOperationTests {

	private SingleOpParamsBuilder builder;
	private CalculatorParams calcParams;
	private String result;

	@BeforeEach
	void setup() {
		builder = new SingleOpParamsBuilder();
	}
	
	@Test
	void caluclate_adding_two_numbers() throws Exception {
		calcParams = builder.calc(OperationType.Plus)
				.withArgs(3, 4)
				.build();

		result = CalculatorCalls.calculate(calcParams);

		assertThat(result, is("7"));
	}


	@Test
	void calculate_adding_negative_numbers() throws Exception {
		calcParams = builder
				.withArgs(-5, -4)
				.build();

		result = CalculatorCalls.calculate(calcParams);

		assertThat(result, is("-9"));

	}

	
	@Test
	void calculate_subtracting_numbers() throws Exception {
		calcParams = builder.calc(OperationType.Minus)
				.withArgs(20, 4)
				.build();

		result = CalculatorCalls.calculate(calcParams);

		assertThat(result, is("16"));
	}

	@Test
	void calculate_multiplying_numbers() throws Exception {
		calcParams = builder.calc(OperationType.Multiply)
				.withArgs(3, 6)
				.build();

		result = CalculatorCalls.calculate(calcParams);

		assertThat(result, is("18"));
	}
	
	@Test
	void calculate_division_of_numbers() throws Exception {
		calcParams = builder.calc(OperationType.Div)
				.withArgs(6, 3)
				.build();

		result = CalculatorCalls.calculate(calcParams);

		assertThat(result, is("2"));
	}
	
	@Test
	void division_by_zero_returns_E() throws Exception {
		calcParams = builder.calc(OperationType.Div)
				.withArgs(6, 0)
				.build();

		result = CalculatorCalls.calculate(calcParams);

		assertThat(result, is("E"));
	}

}
