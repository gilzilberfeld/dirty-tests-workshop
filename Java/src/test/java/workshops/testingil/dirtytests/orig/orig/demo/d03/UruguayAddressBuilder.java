package workshops.testingil.dirtytests.orig.orig.demo.d03;

import workshops.testingil.dirtytests.orig.demo.Address;

public class UruguayAddressBuilder {

	private String city="";
	private String street="";
	private int number=0;
	
	public UruguayAddressBuilder in(String city) {
		this.city = city;
		return this;
	}

	public UruguayAddressBuilder on(String street) {
		this.street = street;
		return this;
	}

	public UruguayAddressBuilder number(int number) {
		this.number = number;
		return this;
	}

	public Address build() {
		Address address = new Address();
		address.country = "Uruguay";
		address.city = city;
		address.street = street;
		address.number = number;
		return address;
	}

}
