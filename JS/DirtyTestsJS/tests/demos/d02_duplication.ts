import 'jasmine';

let store: Store;
let book: Book;

describe('Renaming', () => {
    const HALF_PRICE = 10;
    const FULL_PRICE = 20;
    const DISCOUNTED_BOOK_ID = 20;
    const ANY_BOOK_ID = 17;

    beforeEach(() => {
        store = new Store();
    });
   
    it('should return full price for regular book', () => {
        book = store.getBook(ANY_BOOK_ID);
        expect(book.getPrice()).toBe(FULL_PRICE);
    });

    it('discountTest', () => {
        book = store.getBook(DISCOUNTED_BOOK_ID);
        expect(book.getPrice()).toBe(HALF_PRICE);
    });
});
