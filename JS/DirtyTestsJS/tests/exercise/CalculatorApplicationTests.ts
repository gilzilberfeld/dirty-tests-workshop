const fetch = require("node-fetch");

describe('CalculatorAppTests', () => {
	// Check that we can add two numbers
	// and return the right result as a string
	it('test add', async function () {
		let calcParams = new CalculatorParams();
		calcParams.setFirst(3);
		calcParams.setSecond(4);
		calcParams.setfirstOp(OperationType.Plus);
		const response = await fetch("http://localhost:8888/root/calculate", {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(calcParams)
		});
		expect(response.json).toBe("7");
	});

	// Check that we can add two numbers
	// and return the right result as a string
	it('test add minus', async function () {
		let calcParams = new CalculatorParams();
		calcParams.setFirst(-5);
		calcParams.setSecond(-4);
		calcParams.setfirstOp(OperationType.Plus);
		const response = await fetch("http://localhost:8888/root/calculate", {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(calcParams)
		});
		expect(response.json).toBe("-9");
	});

	// subtract numbers
	it('test minus', async function () {
		let calcParams = new CalculatorParams();
		calcParams.setFirst(20);
		calcParams.setSecond(4);
		calcParams.setfirstOp(OperationType.Minus);
		const response = await fetch("http://localhost:8888/root/calculate", {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(calcParams)
		});
		expect(response.json).toBe("16");
	});

	// multiply two numbers
	it('test mul', async function () {
		let calcParams = new CalculatorParams();
		calcParams.setFirst(3);
		calcParams.setSecond(6);
		calcParams.setfirstOp(OperationType.Multiply);
		const response = await fetch("http://localhost:8888/root/calculate", {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(calcParams)
		});
		expect(response.json).toBe("18");
	});

	it('test reset', async function () {
		const response = await fetch("http://localhost:8888/root/reset", {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			}
		});
		expect(response.json).toBe("0");
	});

	// Divide numbers
	it('test div', async function () {
		let calcParams = new CalculatorParams();
		calcParams.setFirst(6);
		calcParams.setSecond(3);
		calcParams.setfirstOp(OperationType.Div);
		const response = await fetch("http://localhost:8888/root/calculate", {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(calcParams)
		});
		expect(response.json).toBe("2");
	});

	// Divide numbers
	it('test div errors', async function () {
		let calcParams = new CalculatorParams();
		calcParams.setFirst(6);
		calcParams.setSecond(0);
		calcParams.setfirstOp(OperationType.Div);
		const response = await fetch("http://localhost:8888/root/calculate", {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(calcParams)
		});
		expect(response.json).toBe("E");
	});

	// multiple numbers
	it('test mul numbers', async function () {
		let calcParams = new CalculatorParams();
		calcParams.setFirst(3);
		calcParams.setSecond(4);
		calcParams.setThird(7);
		calcParams.setfirstOp(OperationType.Plus);
		calcParams.setSecondOp(OperationType.Minus);
		const response = await fetch("http://localhost:8888/root/calculate", {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(calcParams)
		});
		expect(response.json).toBe("0");
	});

	// multiple numbers
	it('test mul plus', async function () {
		let calcParams = new CalculatorParams();
		calcParams.setFirst(3);
		calcParams.setSecond(4);
		calcParams.setThird(2);
		calcParams.setfirstOp(OperationType.Plus);
		calcParams.setSecondOp(OperationType.Div);
		const response = await fetch("http://localhost:8888/root/calculate", {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(calcParams)
		});
		expect(response.json).toBe("5");
	});

	// multiple numbers
	it('test mul minus', async function () {
		let calcParams = new CalculatorParams();
		calcParams.setFirst(3);
		calcParams.setSecond(4);
		calcParams.setThird(2);
		calcParams.setfirstOp(OperationType.Multiply);
		calcParams.setSecondOp(OperationType.Minus);
		const response = await fetch("http://localhost:8888/root/calculate", {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(calcParams)
		});
		expect(response.json).toBe("10");
	});

	it('test reset 2', async function () {
		var response = await fetch("http://localhost:8888/root/reset", {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			}
		});
		response = await fetch("http://localhost:8888/root/reset", {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			}
		});
		expect(response.status).toBe(400);
	});
});
