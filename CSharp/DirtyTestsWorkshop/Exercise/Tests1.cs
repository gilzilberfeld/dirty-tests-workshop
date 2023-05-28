using CsvHelper;
using DirtyTestsWorkshop.App;
using Newtonsoft.Json;
using NUnit.Framework;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using System.Globalization;
using System.Web;

namespace DirtyTestsWorkshop.Exercise
{
    [TestFixture]
    internal class Tests1
    {
        private WebDriver driver;
        private const string path_to_file = @"Exercise\Resources\slow_unit_test_data.csv";

        [Test]
        public void test_1_2_3()
        {
            Calculator c = new Calculator();
            c.Press("1");
            c.Press("2");
            c.Press("3");
            Assert.AreEqual(c.GetDisplay(), "123");
        }

        [Test]
        //[Ignore]
        public async Task test_plus_and_2()
        {
            HttpClient client = new HttpClient();
            ResultWrapper result;

            await client.PostAsync("http://localhost:8080/calc/reset", null);

            var builder = new UriBuilder("http://localhost:8080/calc/press");
            builder.Query = "key=" + HttpUtility.UrlEncode("+");
            await client.PostAsync(builder.ToString(), null);

            builder.Query = "key=2";
            await client.PostAsync(builder.ToString(), null);

            HttpResponseMessage response = await client.GetAsync("http://localhost:8080/calc/display");
            var body = await response.Content.ReadAsStringAsync();
            result = JsonConvert.DeserializeObject<ResultWrapper>(body);
            Assert.AreEqual("2", result.display);

        }
        [Test]
        public void test_ui_1_plus_34()
        {
            var URL = "file:///" + Environment.CurrentDirectory + "/CalculatorUI.html";
            ChromeOptions options = new ChromeOptions();
            options.AddArguments("remote-allow-origins=*");
            driver = new ChromeDriver(options);
            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(10);
            driver.Url = URL;

            String BUTTON_XPATH4 = "//*[@id=\'id" + "reset" + "\']";
            IWebElement keyElement4 = driver.FindElement(By.XPath(BUTTON_XPATH4));
            keyElement4.Click();

            Thread.Sleep(500);

            String BUTTON_XPATH3 = "//*[@id=\'id" + "1" + "\']";
            IWebElement keyElement3 = driver.FindElement(By.XPath(BUTTON_XPATH3));
            keyElement3.Click();
            Thread.Sleep(500);

            String BUTTON_XPATH2 = "//*[@id=\'id" + "plus" + "\']";
            IWebElement keyElement2 = driver.FindElement(By.XPath(BUTTON_XPATH2));
            keyElement2.Click();
            Thread.Sleep(500);

            String BUTTON_XPATH1 = "//*[@id=\'id" + "3" + "\']";
            IWebElement keyElement1 = driver.FindElement(By.XPath(BUTTON_XPATH1));
            keyElement1.Click();
            Thread.Sleep(500);

            String BUTTON_XPATH = "//*[@id=\'id" + "4" + "\']";
            IWebElement keyElement = driver.FindElement(By.XPath(BUTTON_XPATH));
            keyElement.Click();
            Thread.Sleep(500);

            String BUTTON_XPATH5 = "//*[@id=\'id" + "Update" + "\']";
            IWebElement keyElement5 = driver.FindElement(By.XPath(BUTTON_XPATH5));
            keyElement5.Click();
            Thread.Sleep(500);

            String DISPLAY_XPATH = "//*[@id=\"result\"]";
            IWebElement res = driver.FindElement(By.XPath(DISPLAY_XPATH));
            String result = res.GetAttribute("value");
            Assert.AreEqual("34", result);

            driver.Quit();
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

        [Test]
        public void test_nothing()
        {
            Calculator c = new Calculator();
            Assert.IsTrue(c.GetDisplay() == "0");
        }

        [Test]
        //[Ignore]
        public async Task test_1_and_plus()
        {

            HttpClient client = new HttpClient();
            ResultWrapper result;

            await client.PostAsync("http://localhost:8080/calc/reset", null);

            var builder = new UriBuilder("http://localhost:8080/calc/press");
            builder.Query = "key=1";

            await client.PostAsync(builder.ToString(), null);

            builder.Query = "key=" + HttpUtility.UrlEncode("+");
            await client.PostAsync(builder.ToString(), null);

            HttpResponseMessage response = await client.GetAsync("http://localhost:8080/calc/display");
            var body = await response.Content.ReadAsStringAsync();
            result = JsonConvert.DeserializeObject<ResultWrapper>(body);
            Assert.AreEqual("1", result.display);

        }

        [Test]
        public void test_1()
        {
            Calculator c = new Calculator();
            c.Press("1");
            Assert.IsTrue(c.GetDisplay() == "1");
        }

        [Test]
        public async Task test_zero_zero()
        {
            HttpClient client = new HttpClient();
            ResultWrapper result;

            await client.PostAsync("http://localhost:8080/calc/reset", null);

            var builder = new UriBuilder("http://localhost:8080/calc/press");
            builder.Query = "key=0";

            await client.PostAsync(builder.ToString(), null);
            await client.PostAsync(builder.ToString(), null);

            HttpResponseMessage response = await client.GetAsync("http://localhost:8080/calc/display");
            var body = await response.Content.ReadAsStringAsync();
            result = JsonConvert.DeserializeObject<ResultWrapper>(body);
            Assert.AreEqual("0", result.display);
        }

        [Test]
        public void test_div_6_2()
        {
            var URL = "file:///" + Environment.CurrentDirectory + "/CalculatorUI.html";
            ChromeOptions options = new ChromeOptions();
            options.AddArguments("remote-allow-origins=*");
            driver = new ChromeDriver(options);
            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(10);
            driver.Url = URL;

            String BUTTON_XPATH4 = "//*[@id=\'id" + "reset" + "\']";
            IWebElement keyElement4 = driver.FindElement(By.XPath(BUTTON_XPATH4));
            keyElement4.Click();
            Thread.Sleep(500);

            String BUTTON_XPATH3 = "//*[@id=\'id" + "6" + "\']";
            IWebElement keyElement3 = driver.FindElement(By.XPath(BUTTON_XPATH3));
            keyElement3.Click();
            Thread.Sleep(500);

            String BUTTON_XPATH2 = "//*[@id=\'id" + "div" + "\']";
            IWebElement keyElement2 = driver.FindElement(By.XPath(BUTTON_XPATH2));
            keyElement2.Click();
            Thread.Sleep(500);

            String BUTTON_XPATH1 = "//*[@id=\'id" + "2" + "\']";
            IWebElement keyElement1 = driver.FindElement(By.XPath(BUTTON_XPATH1));
            keyElement1.Click();
            Thread.Sleep(500);

            String BUTTON_XPATH = "//*[@id=\'id" + "eql" + "\']";
            IWebElement keyElement = driver.FindElement(By.XPath(BUTTON_XPATH));
            keyElement.Click();
            Thread.Sleep(500);

            String BUTTON_XPATH5 = "//*[@id=\'id" + "Update" + "\']";
            IWebElement keyElement5 = driver.FindElement(By.XPath(BUTTON_XPATH5));
            keyElement5.Click();
            Thread.Sleep(500);

            String DISPLAY_XPATH = "//*[@id=\'result\']";
            IWebElement res = driver.FindElement(By.XPath(DISPLAY_XPATH));
            String result = res.GetAttribute("value");
            Assert.AreEqual("3", result);
            driver.Quit();
        }

        [Test]
        public void test_1_and_C()
        {
            Calculator c = new Calculator();
            c.Press("1");
            c.Press("C");
            Assert.AreEqual(c.GetDisplay(), "0");
        }

        [Test]
        public async Task test_0()
        {
            HttpClient client = new HttpClient();

            var builder = new UriBuilder("http://localhost:8080/calc/press");
            builder.Query = "key=0";
            var url = builder.ToString();

            await client.PostAsync(url, null);

            HttpResponseMessage response = await client.GetAsync("http://localhost:8080/calc/display");
            var body = await response.Content.ReadAsStringAsync();
            ResultWrapper result = JsonConvert.DeserializeObject<ResultWrapper>(body);
            Assert.AreEqual("0", result.display);
        }


    }
}
