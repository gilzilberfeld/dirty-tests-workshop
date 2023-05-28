using CsvHelper;
using DirtyTestsWorkshop.App;
using Newtonsoft.Json;
using NUnit.Framework;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Net.Http.Headers;
using System.Text;
using System.Threading.Tasks;

namespace DirtyTestsWorkshop.Exercise
{

    [TestFixture]
    internal class Test2
    {
        private const string path_to_file1 = @"Exercise\Resources\sequence_api_data.csv";
        private WebDriver driver;
        private const string path_to_file2 = @"Exercise\Resources\slow_unit_test_data_2.csv";


        [Test, TestCaseSource("GetTestData1")]
        public async Task test_multiple_sequences(string sequence, string expected)
        {
            HttpClient client = new HttpClient();
            ResultWrapper result;

            await client.PostAsync("http://localhost:8080/calc/reset", null);

            SequenceMessageHelper message = new SequenceMessageHelper();
            message.version = "1.0";
            message.addSequence(sequence);
            message.resetOnError = true;
            string message_body = JsonConvert.SerializeObject(message);

            var builder = new UriBuilder("http://localhost:8080/calc/sequence");
            client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

            await client.PostAsync(builder.ToString(), new StringContent(message_body));

            HttpResponseMessage response = await client.GetAsync("http://localhost:8080/calc/display");
            var body = await response.Content.ReadAsStringAsync();
            result = JsonConvert.DeserializeObject<ResultWrapper>(body);
            Assert.AreEqual(expected, result.display);
        }
        private static IEnumerable<string[]> GetTestData1()
        {
            using (var reader = new StreamReader(path_to_file1))
            using (var csv = new CsvReader(reader, CultureInfo.InvariantCulture))
            {
                while (csv.Read())
                {
                    yield return new string[] { csv[0], csv[1] };

                }
            }
        }
        [Test]
        public void test_01()
        {
            Calculator c = new Calculator();
            c.Press("0");
            c.Press("1");
            Assert.IsTrue(c.GetDisplay() == "1");
        }

        [Test]
        public void test_ui_123()
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

            String BUTTON_XPATH2 = "//*[@id=\'id" + "2" + "\']";
            IWebElement keyElement2 = driver.FindElement(By.XPath(BUTTON_XPATH2));
            keyElement2.Click();
            Thread.Sleep(500);

            String BUTTON_XPATH1 = "//*[@id=\'id" + "3" + "\']";
            IWebElement keyElement1 = driver.FindElement(By.XPath(BUTTON_XPATH1));
            keyElement1.Click();
            Thread.Sleep(500);

            String BUTTON_XPATH5 = "//*[@id=\'id" + "Update" + "\']";
            IWebElement keyElement5 = driver.FindElement(By.XPath(BUTTON_XPATH5));
            keyElement5.Click();
            Thread.Sleep(5000);

            String DISPLAY_XPATH = "//*[@id=\'result\']";
            IWebElement res = driver.FindElement(By.XPath(DISPLAY_XPATH));
            String result = res.GetAttribute("value");
            Assert.AreEqual("123", result);
            driver.Quit();
        }

        [Test, TestCaseSource("GetTestData2")]
        public async Task test_more_sequences(string sequence, string expected)
        {
            HttpClient client = new HttpClient();
            ResultWrapper result;

            await client.PostAsync("http://localhost:8080/calc/reset", null);

            SequenceMessageHelper message = new SequenceMessageHelper();
            message.version = "1.0";
            message.addSequence(sequence);
            message.resetOnError = true;

            string message_body = JsonConvert.SerializeObject(message);

            var builder = new UriBuilder("http://localhost:8080/calc/sequence");
            client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

            await client.PostAsync(builder.ToString(), new StringContent(message_body));

            HttpResponseMessage response = await client.GetAsync("http://localhost:8080/calc/display");
            var body = await response.Content.ReadAsStringAsync();
            result = JsonConvert.DeserializeObject<ResultWrapper>(body);
            Assert.AreEqual(expected, result.display);
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

        [Test]
        public void test_5_mul_and_mul()
        {
            Calculator c = new Calculator();
            c.Press("5");
            c.Press("*");
            c.Press("*");
            Assert.AreEqual(c.GetDisplay(), "5");
        }

        [Test]
        public void test_5_plus_3()
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

            String BUTTON_XPATH3 = "//*[@id=\'id" + "5" + "\']";
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

            String BUTTON_XPATH5 = "//*[@id=\'id" + "Update" + "\']";
            IWebElement keyElement5 = driver.FindElement(By.XPath(BUTTON_XPATH5));
            keyElement5.Click();
            Thread.Sleep(5000);

            String DISPLAY_XPATH = "//*[@id=\'result\']";
            IWebElement res = driver.FindElement(By.XPath(DISPLAY_XPATH));
            String result = res.GetAttribute("value");
            Assert.AreEqual("3", result);
            driver.Quit();
        }

        [Test, TestCaseSource("GetTestData2")]
        public void test_multiple_vals_2(string input, string output)

        {
            Calculator c = new Calculator();
            c.PressAll(input);
            Assert.AreEqual(c.GetDisplay(), output);
        }

        [Test]
        public async Task test_result()
        {
            HttpClient client = new HttpClient();
            ResultWrapper result;

            await client.PostAsync("http://localhost:8080/calc/reset", null);

            SequenceMessageHelper message = new SequenceMessageHelper();
            message.version = "1.0";
            message.addSequence("5+2C");
            message.resetOnError = true;

            string message_body = JsonConvert.SerializeObject(message);

            var builder = new UriBuilder("http://localhost:8080/calc/sequence");
            client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

            await client.PostAsync(builder.ToString(), new StringContent(message_body));

            HttpResponseMessage response = await client.GetAsync("http://localhost:8080/calc/display");
            var body = await response.Content.ReadAsStringAsync();
            string json =
                File.ReadAllText(@"Exercise\Resources\reference_result.json");
            Assert.AreEqual(json, body);
        }

    }
}
