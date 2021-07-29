package workshops.testingil.dirtytests.ex4.abstraction;

import workshops.testingil.dirtytests.exercise.CalculatorParams;
import workshops.testingil.dirtytests.exercise.OperationType;

public class TwoOpParamsBuilder {

	int first = 0;
	int second = 0;
	int third = 0;
	OperationType op1 = OperationType.Plus;
	OperationType op2 = OperationType.Plus;
	
	public TwoOpParamsBuilder withArgs(int first, int second, int third) {
		this.first = first;
		this.second = second;
		this.third = third;
		return this;
	}


	public TwoOpParamsBuilder calc(OperationType op) {
		this.op1 = op;
		return this;
	}

	public TwoOpParamsBuilder then(OperationType op) {
		this.op2 = op;
		return this;
	}

	
	public CalculatorParams build() {
		CalculatorParams calcParams = new CalculatorParams();
		calcParams.setFirst(first);
		calcParams.setSecond(second);
		calcParams.setThird(third);
		calcParams.setfirstOp(op1);
		calcParams.setSecondOp(op2);
		return calcParams;
	}

}
