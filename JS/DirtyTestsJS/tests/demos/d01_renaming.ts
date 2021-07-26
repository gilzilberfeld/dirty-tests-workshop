import 'jasmine';

describe('Renaming', () => {
    const HALF_PRICE = 10;
	const FULL_PRICE = 20;
	const DISCOUNTED_BOOK_ID = 20;
	const ANY_BOOK_ID = 17;

    it('should return full price for regular book', () => {
        var store = new Store();
        var book = store.getBook(ANY_BOOK_ID);
        expect(book.getPrice()).toBe(FULL_PRICE);
    });

    it('discountTest', () => {
        var store = new Store();
        var book = store.getBook(DISCOUNTED_BOOK_ID);
        expect(book.getPrice()).toBe(HALF_PRICE);
    });
});
