using DirtyTestsWorkshop.Demo;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace DirtyTests
{
    [TestClass]
    public class D2_Duplication
    {
        private const int HALF_PRICE = 10;
        private const int FULL_PRICE = 20;
        private const int DISCOUNTED_BOOK_ID = 20;
        private const int ANY_BOOK_ID = 17;
        private Store store;
        private Book book;

        [TestInitialize]
        public void Setup()
        {
            store = new Store();
        }

        [TestMethod]
        public void Should_return_full_price_for_regular_book()
        {
            book = store.GetBook(ANY_BOOK_ID);
            Assert.AreEqual(FULL_PRICE, book.GetPrice());
        }

        [TestMethod]
        public void Should_return_half_price_for_discounted_book()
        {
            book = store.GetBook(DISCOUNTED_BOOK_ID);
            Assert.AreEqual(HALF_PRICE, book.GetPrice());
        }

    }
}
