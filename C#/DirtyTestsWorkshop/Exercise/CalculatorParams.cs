namespace DirtyTestsWorkshop.Exercise
{
    public class CalculatorParams
    {
		int firstArg;
		int secondArg;
		int thirdArg;
		OperationType op1;
		OperationType op2;

		public void SetFirst(int arg)
		{
			this.firstArg = arg;
		}

		public void SetSecond(int arg)
		{
			this.secondArg = arg;
		}

		public void SetThird(int arg)
		{
			this.thirdArg = arg;
		}

		public void SetFirstOp(OperationType op)
		{
			this.op1 = op;
		}

		public void SetSecondOp(OperationType op)
		{
			this.op2 = op;
		}

	}
}