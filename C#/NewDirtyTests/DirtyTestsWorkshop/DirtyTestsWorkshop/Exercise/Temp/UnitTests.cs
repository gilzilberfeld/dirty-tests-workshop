using DirtyTestsWorkshop.App;
using NUnit.Framework;

namespace DirtyTestsWorkshop.Exercise.Temp
{
    [TestFixture]
    public class UnitTests
    {
        [Test]
        public void test_nothing()
        {
            Calculator c = new Calculator();
            Assert.IsTrue(c.GetDisplay() == "0");
        }

        [Test]
        public void test_1() 
        { 
                Calculator c = new Calculator();
                c.Press("1");
                Assert.IsTrue(c.GetDisplay() == "1");
        }

        [Test]
        public void test_1_and_C()
        {
            Calculator c = new Calculator();
            c.Press("1");
            c.Press("C");
            Assert.AreEqual(c.GetDisplay(), "0");
        }
    }
}
