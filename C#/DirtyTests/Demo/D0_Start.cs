using DirtyTestsWorkshop.Demo;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace DirtyTests
{
    [TestClass]
    public class D0_Start
    {
        [TestMethod]
        public void PriceTest()
        {
			Store s = new Store();
			Book b = s.GetBook(17);
			Assert.AreEqual(20, b.GetPrice());
		}

		[TestMethod]
		public void DiscountTest()
		{
			Store s = new Store();
			Book b = s.GetBook(20);
			Assert.AreEqual(10, b.GetPrice());
		}
	}
}
