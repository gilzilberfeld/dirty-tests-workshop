import 'jasmine';

describe('Start', () => {
    it('priceTest', () => {
        var s = new Store();
        var b = s.getBook(17);
        expect(b.getPrice()).toBe(20);
    });

    it('discountTest', () => {
        var s = new Store();
        var b = s.getBook(20);
        expect(b.getPrice()).toBe(10);
    });
});
