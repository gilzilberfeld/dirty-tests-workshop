package workshops.testingil.dirtytests.demo.d00;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import workshops.testingil.dirtytests.demo.Book;
import workshops.testingil.dirtytests.demo.Store;

class Start {

	@Test
	void priceTest() {
		Store s = new Store();
		Book b = s.getBook(17);
		assertEquals(20, b.getPrice());
	}

	@Test
	void discountTest() {
		Store s = new Store();
		Book b = s.getBook(20);
		assertEquals(10, b.getPrice());
	}

}
