using CsvHelper;
using DirtyTestsWorkshop.App;
using Microsoft.VisualStudio.TestPlatform.CommunicationUtilities.Resources;
using NUnit.Framework;
using System.Formats.Asn1;
using System.Globalization;

namespace DirtyTestsWorkshop.Exercise.Temp
{
    [TestFixture]
    public class SlowUnitTests
    {
        private const string path_to_file = @"Exercise\Resources\slow_unit_test_data.csv";
        private const string path_to_file2 = @"Exercise\Resources\slow_unit_test_data_2.csv";

        [Test]
        public void test_1_2_3()
        {
            Calculator c = new Calculator();
            c.Press("1");
            c.Press("2");
            c.Press("3");
            Assert.AreEqual(c.GetDisplay(), "123");
        }


        [Test, TestCaseSource("GetTestData")]
        public void test_multiple_vals(string input, string output)
        {
            Calculator c = new Calculator();
            foreach (char ch in input)
            {
                c.Press(ch.ToString());
            }
            Assert.AreEqual(c.GetDisplay(), output);
        }

        [Test, TestCaseSource("GetTestData2")]
        public void test_multiple_vals_2(string input, string output)

        {
            Calculator c = new Calculator();
            c.PressAll(input);
            Assert.AreEqual(c.GetDisplay(), output);
        }

        private static IEnumerable<string[]> GetTestData()
        {
            using (var reader = new StreamReader(path_to_file))
            using (var csv = new CsvReader(reader, CultureInfo.InvariantCulture))
            {
                while (csv.Read())
                {
                    yield return new string[] { csv[0], csv[1] };

                }
            }
        }
        private static IEnumerable<string[]> GetTestData2()
        {
            using (var reader = new StreamReader(path_to_file2))
            using (var csv = new CsvReader(reader, CultureInfo.InvariantCulture))
            {
                while (csv.Read())
                {
                    yield return new string[] { csv[0], csv[1] };

                }
            }
        }
    }
}
