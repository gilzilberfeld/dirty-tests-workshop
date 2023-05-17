package workshops.testingil.dirtytests.orig.orig.demo.d02;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import workshops.testingil.dirtytests.orig.demo.Book;
import workshops.testingil.dirtytests.orig.demo.Store;

class Duplication {

	private static final int HALF_PRICE = 10;
	private static final int FULL_PRICE = 20;
	private static final int DISCOUNTED_BOOK_ID = 20;
	private static final int ANY_BOOK_ID = 17;
	private Store store;
	private Book book;
	
	@BeforeEach
	void setup() {
		store = new Store();
	}

	@Test
	void should_return_full_price_for_regular_book() {
		book = store.getBook(ANY_BOOK_ID);
		assertEquals(FULL_PRICE, book.getPrice());
	}

	@Test
	void should_return_half_price_for_discounted_book() {
		book = store.getBook(DISCOUNTED_BOOK_ID);
		assertEquals(HALF_PRICE, book.getPrice());
	}

}
