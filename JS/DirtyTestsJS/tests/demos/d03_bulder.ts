import 'jasmine';

describe('Builder tests', () => {
	it('uses data object setup', () => {
		let address = new Address();
		address.country = "Uruguay";
		address.city = "Montevideo";
		address.street = "Dr. Luis Piera";
		address.num = 1921;

		expect(address.toString())
			.toBe("1921 Dr. Luis Piera St., Montevideo, Uruguay");
	});

	it('uses builder setup', () => {

		let builder = new UruguayAddressBuilder();

		let address = builder
			.in("Montevideo")
			.on("Dr. Luis Piera").number(1921)
			.build();

		expect(address.toString())
			.toBe("1921 Dr. Luis Piera St., Montevideo, Uruguay");
	});

});
