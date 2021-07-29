package workshops.testingil.dirtytests.ex2.duplication;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import workshops.testingil.dirtytests.exercise.CalculatorParams;
import workshops.testingil.dirtytests.exercise.OperationType;

class SingleOperationTests {

	private CalculatorParams calcParams;
	private String result;

	@BeforeEach
	void setup() {
		calcParams = new CalculatorParams();
	}
	
	@Test
	void caluclate_adding_two_numbers() throws Exception {
		calcParams.setFirst(3);
		calcParams.setSecond(4);
		calcParams.setfirstOp(OperationType.Plus);

		result = Calls.calculate(calcParams);

		assertThat(result, is("7"));
	}


	@Test
	void calculate_adding_negative_numbers() throws Exception {
		calcParams.setFirst(-5);
		calcParams.setSecond(-4);
		calcParams.setfirstOp(OperationType.Plus);

		result = Calls.calculate(calcParams);

		assertThat(result, is("-9"));

	}

	
	@Test
	void calculate_subtracting_numbers() throws Exception {
		calcParams.setFirst(20);
		calcParams.setSecond(4);
		calcParams.setfirstOp(OperationType.Minus);

		result = Calls.calculate(calcParams);

		assertThat(result, is("16"));
	}

	@Test
	void calculate_multiplying_numbers() throws Exception {
		calcParams.setFirst(3);
		calcParams.setSecond(6);
		calcParams.setfirstOp(OperationType.Multiply);

		result = Calls.calculate(calcParams);

		assertThat(result, is("18"));
	}
	
	@Test
	void calculate_division_of_numbers() throws Exception {
		calcParams.setFirst(6);
		calcParams.setSecond(3);
		calcParams.setfirstOp(OperationType.Div);

		result = Calls.calculate(calcParams);

		assertThat(result, is("2"));
	}
	
	@Test
	void division_by_zero_returns_E() throws Exception {
		calcParams.setFirst(6);
		calcParams.setSecond(0);
		calcParams.setfirstOp(OperationType.Div);

		result = Calls.calculate(calcParams);

		assertThat(result, is("E"));
	}

}