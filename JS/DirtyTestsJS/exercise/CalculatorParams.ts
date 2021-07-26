class CalculatorParams {
    firstArg: number;
    secondArg: number;
    thirdArg: number;
    op1: OperationType;
    op2: OperationType;

    setFirst(arg: number) {
        this.firstArg = arg;
    }

    setSecond(arg: number) {
        this.secondArg = arg;
    }

    setThird(arg: number) {
        this.thirdArg = arg;
    }

    setfirstOp(op: OperationType) {
        this.op1 = op;
    }

    setSecondOp(op: OperationType) {
        this.op2 = op;
    }

}