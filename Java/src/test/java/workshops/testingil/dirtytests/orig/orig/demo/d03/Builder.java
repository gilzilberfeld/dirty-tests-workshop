package workshops.testingil.dirtytests.orig.orig.demo.d03;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import workshops.testingil.dirtytests.orig.demo.Address;

class Builder {

	@Test
	void data_object_setup() {
		Address address = new Address();
		address.country = "Uruguay";
		address.city = "Montevideo";
		address.street = "Dr. Luis Piera";
		address.number = 1921;
	
		assertEquals("1921 Dr. Luis Piera St., Montevideo, Uruguay", 
				address.toString());
	}

	@Test
	void builder_setup() {
		UruguayAddressBuilder builder = new  UruguayAddressBuilder();

		Address address = builder
				.in("Montevideo")
				.on("Dr. Luis Piera").number(1921)
				.build();
	
		assertEquals("1921 Dr. Luis Piera St., Montevideo, Uruguay", 
				address.toString());
	}

}
