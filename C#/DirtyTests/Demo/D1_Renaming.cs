using DirtyTestsWorkshop.Demo;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace DirtyTests
{

    [TestClass]
    public class D1_Renaming
    {
        private const int HALF_PRICE = 10;
        private const int FULL_PRICE = 20;
        private const int DISCOUNTED_BOOK_ID = 20;
        private const int ANY_BOOK_ID = 17;

        [TestMethod]
        public void should_return_full_price_for_regular_book()
        {
            Store store = new Store();
            Book book = store.GetBook(ANY_BOOK_ID);
            Assert.AreEqual(FULL_PRICE, book.GetPrice());

        }

        [TestMethod]
        public void should_return_half_price_for_discounted_book()
        {
            Store store = new Store();
            Book book = store.GetBook(ANY_BOOK_ID);
            Assert.AreEqual(FULL_PRICE, book.GetPrice());

        }
    }
}
