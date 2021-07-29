package workshops.testingil.dirtytests.ex3.builder;

import workshops.testingil.dirtytests.exercise.CalculatorParams;
import workshops.testingil.dirtytests.exercise.OperationType;

public class SingleOpParamsBuilder {
	int first = 0;
	int second = 0;
	OperationType op = OperationType.Plus;
		
	public SingleOpParamsBuilder withArgs(int first, int second) {
		this.first = first;
		this.second = second;
		return this;
	}
	
	public SingleOpParamsBuilder calc(OperationType op) {
		this.op = op;
		return this;
	}
	
	
	public CalculatorParams build() {
		CalculatorParams calcParams = new CalculatorParams();
		calcParams.setFirst(first);
		calcParams.setSecond(second);
		calcParams.setfirstOp(op);
		return calcParams;
	}
}
