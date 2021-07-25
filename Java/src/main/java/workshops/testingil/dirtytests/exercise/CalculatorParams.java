package workshops.testingil.dirtytests.exercise;

public class CalculatorParams {

	int firstArg;
	int secondArg;
	int thirdArg;
	OperationType op1;
	OperationType op2;

	public void setFirst(int arg) {
		this.firstArg = arg;
	}

	public void setSecond(int arg) {
		this.secondArg = arg;
	}

	public void setThird(int arg) {
		this.thirdArg = arg;
	}

	public void setfirstOp(OperationType op) {
		this.op1 = op;
	}

	public void setSecondOp(OperationType op) {
		this.op2 = op;
	}

}
