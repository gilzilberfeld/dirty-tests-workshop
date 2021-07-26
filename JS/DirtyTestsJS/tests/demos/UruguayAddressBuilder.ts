class UruguayAddressBuilder {

	city = "";
	street = "";
	num = 0;

	in(city : string) {
		this.city = city;
		return this;
	}

	on(street : string ) {
		this.street = street;
		return this;
	}

	number(num : number) {
		this.num = num;
		return this;
	}

	build(): Address {
		var address = new Address();
		address.country = "Uruguay";
		address.city = this.city;
		address.street = this.street;
		address.num = this.num;
		return address;
	}

}
