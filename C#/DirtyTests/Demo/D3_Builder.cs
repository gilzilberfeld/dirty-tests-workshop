using DirtyTests.Demo;
using DirtyTestsWorkshop.Demo;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace DirtyTests
{
    [TestClass]
    public class D3_Builder
    {
        [TestMethod]
        public void Data_object_setup()
        {
            Address address = new Address();
            address.country = "Uruguay";
            address.city = "Montevideo";
            address.street = "Dr. Luis Piera";
            address.number = 1921;

            Assert.AreEqual("1921 Dr. Luis Piera St., Montevideo, Uruguay",
                    address.ToString());
        }

        [TestMethod]
        public void Builder_setup()
        {
            UruguayAddressBuilder builder = new UruguayAddressBuilder();

            Address address = builder
                    .inCity("Montevideo")
                    .on("Dr. Luis Piera").number(1921)
                    .build();

            Assert.AreEqual("1921 Dr. Luis Piera St., Montevideo, Uruguay",
                    address.ToString());
        }


    }
}
