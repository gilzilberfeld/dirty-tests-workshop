using DirtyTestsWorkshop;
using DirtyTestsWorkshop.Demo;
using DirtyTestsWorkshop.Exercise;
using Microsoft.AspNetCore.Mvc.Testing;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Newtonsoft.Json;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using System.Net;

namespace DirtyTests
{
    [TestClass]
    public class DirtytestsApplicationTests
    {
        // Check that we can add two numbers
        // and return the right result as a string
        [TestMethod]
        public async Task Test_Add()
        {
            CalculatorParams calcParams = new CalculatorParams();
            calcParams.SetFirst(3);
            calcParams.SetSecond(4);
            calcParams.SetFirstOp(OperationType.Plus);

            WebApplicationFactory<Startup> appFactory = new WebApplicationFactory<Startup>();
            HttpClient _client = appFactory.CreateClient();

            string json = JsonConvert.SerializeObject(calcParams);
            HttpContent content = new StringContent(json, Encoding.UTF8, "application/json");

            var response = await _client.PostAsync("http://localhost:8888/root/calculate", content);
            Assert.AreEqual(HttpStatusCode.OK, response.StatusCode);
            string result = await response.Content.ReadAsStringAsync();
            Assert.AreEqual("7", result);
        }

        // Check that we can add two numbers
        // and return the right result as a string
        [TestMethod]
        public async Task Test_Add_Minus()
        {
            CalculatorParams calcParams = new CalculatorParams();
            calcParams.SetFirst(-5);
            calcParams.SetSecond(-4);
            calcParams.SetFirstOp(OperationType.Plus);

            WebApplicationFactory<Startup> appFactory = new WebApplicationFactory<Startup>();
            HttpClient _client = appFactory.CreateClient();

            string json = JsonConvert.SerializeObject(calcParams);
            HttpContent content = new StringContent(json, Encoding.UTF8, "application/json");

            var response = await _client.PostAsync("http://localhost:8888/root/calculate", content);
            Assert.AreEqual(HttpStatusCode.OK, response.StatusCode);
            string result = await response.Content.ReadAsStringAsync();

            Assert.AreEqual("-9", result);
        }
        // subtract numbers
        [TestMethod]
        public async Task Test_Minus()
        {
            CalculatorParams calcParams = new CalculatorParams();
            calcParams.SetFirst(20);
            calcParams.SetSecond(4);
            calcParams.SetFirstOp(OperationType.Minus);

            WebApplicationFactory<Startup> appFactory = new WebApplicationFactory<Startup>();
            HttpClient _client = appFactory.CreateClient();

            string json = JsonConvert.SerializeObject(calcParams);
            HttpContent content = new StringContent(json, Encoding.UTF8, "application/json");

            var response = await _client.PostAsync("http://localhost:8888/root/calculate", content);
            Assert.AreEqual(HttpStatusCode.OK, response.StatusCode);
            string result = await response.Content.ReadAsStringAsync();

            Assert.AreEqual("16", result);
        }

        [TestMethod]
        // multiply two numbers
        public async Task Test_Mul()
        {
            CalculatorParams calcParams = new CalculatorParams();
            calcParams.SetFirst(3);
            calcParams.SetSecond(6);
            calcParams.SetFirstOp(OperationType.Multiply);

            WebApplicationFactory<Startup> appFactory = new WebApplicationFactory<Startup>();
            HttpClient _client = appFactory.CreateClient();

            string json = JsonConvert.SerializeObject(calcParams);
            HttpContent content = new StringContent(json, Encoding.UTF8, "application/json");

            var response = await _client.PostAsync("http://localhost:8888/root/calculate", content);
            Assert.AreEqual(HttpStatusCode.OK, response.StatusCode);
            string result = await response.Content.ReadAsStringAsync();

            Assert.AreEqual("18", result);
        }

        [TestMethod]
        public async Task Test_Reset()
        {
            WebApplicationFactory<Startup> appFactory = new WebApplicationFactory<Startup>();
            HttpClient _client = appFactory.CreateClient();

            HttpContent content = new StringContent("", Encoding.UTF8, "application/json");

            var response = await _client.PostAsync("http://localhost:8888/root/reset", content);
            Assert.AreEqual(HttpStatusCode.OK, response.StatusCode);
            string result = await response.Content.ReadAsStringAsync();

            Assert.AreEqual("0", result);
        }

        [TestMethod]
        // Divide numbers
        public async Task Test_Div()
        {
            CalculatorParams calcParams = new CalculatorParams();
            calcParams.SetFirst(6);
            calcParams.SetSecond(3);
            calcParams.SetFirstOp(OperationType.Div);

            WebApplicationFactory<Startup> appFactory = new WebApplicationFactory<Startup>();
            HttpClient _client = appFactory.CreateClient();

            string json = JsonConvert.SerializeObject(calcParams);
            HttpContent content = new StringContent(json, Encoding.UTF8, "application/json");

            var response = await _client.PostAsync("http://localhost:8888/root/calculate", content);
            Assert.AreEqual(HttpStatusCode.OK, response.StatusCode);
            string result = await response.Content.ReadAsStringAsync();

            Assert.AreEqual("2", result);

        }


        [TestMethod]
        // Divide numbers
        public async Task Test_Div_errors()
        {
            CalculatorParams calcParams = new CalculatorParams();
            calcParams.SetFirst(6);
            calcParams.SetSecond(0);
            calcParams.SetFirstOp(OperationType.Div);

            WebApplicationFactory<Startup> appFactory = new WebApplicationFactory<Startup>();
            HttpClient _client = appFactory.CreateClient();

            string json = JsonConvert.SerializeObject(calcParams);
            HttpContent content = new StringContent(json, Encoding.UTF8, "application/json");

            var response = await _client.PostAsync("http://localhost:8888/root/calculate", content);
            Assert.AreEqual(HttpStatusCode.OK, response.StatusCode);
            string result = await response.Content.ReadAsStringAsync();

            Assert.AreEqual("E", result);

        }

        [TestMethod]
        // multiple numbers
        public async Task Test_Mul_Numbers()
        {
            CalculatorParams calcParams = new CalculatorParams();
            calcParams.SetFirst(3);
            calcParams.SetSecond(4);
            calcParams.SetThird(7);
            calcParams.SetFirstOp(OperationType.Plus);
            calcParams.SetSecondOp(OperationType.Minus);

            WebApplicationFactory<Startup> appFactory = new WebApplicationFactory<Startup>();
            HttpClient _client = appFactory.CreateClient();

            string json = JsonConvert.SerializeObject(calcParams);
            HttpContent content = new StringContent(json, Encoding.UTF8, "application/json");

            var response = await _client.PostAsync("http://localhost:8888/root/calculate", content);
            Assert.AreEqual(HttpStatusCode.OK, response.StatusCode);
            string result = await response.Content.ReadAsStringAsync();

            Assert.AreEqual("0", result);
        }
        [TestMethod]
        // multiple numbers
        public async Task Test_Mul_Plus()
        {
            CalculatorParams calcParams = new CalculatorParams();
            calcParams.SetFirst(3);
            calcParams.SetSecond(4);
            calcParams.SetThird(2);
            calcParams.SetFirstOp(OperationType.Plus);
            calcParams.SetSecondOp(OperationType.Div);

            WebApplicationFactory<Startup> appFactory = new WebApplicationFactory<Startup>();
            HttpClient _client = appFactory.CreateClient();

            string json = JsonConvert.SerializeObject(calcParams);
            HttpContent content = new StringContent(json, Encoding.UTF8, "application/json");

            var response = await _client.PostAsync("http://localhost:8888/root/calculate", content);
            Assert.AreEqual(HttpStatusCode.OK, response.StatusCode);
            string result = await response.Content.ReadAsStringAsync();

            Assert.AreEqual("5", result);
        }

        [TestMethod]
        // multiple numbers
        public async Task Test_Mul_Minus()
        {
            CalculatorParams calcParams = new CalculatorParams();

            calcParams.SetFirst(3);
            calcParams.SetSecond(4);
            calcParams.SetThird(2);
            calcParams.SetFirstOp(OperationType.Multiply);
            calcParams.SetSecondOp(OperationType.Minus);

            WebApplicationFactory<Startup> appFactory = new WebApplicationFactory<Startup>();
            HttpClient _client = appFactory.CreateClient();

            string json = JsonConvert.SerializeObject(calcParams);
            HttpContent content = new StringContent(json, Encoding.UTF8, "application/json");

            var response = await _client.PostAsync("http://localhost:8888/root/calculate", content);
            Assert.AreEqual(HttpStatusCode.OK, response.StatusCode);
            string result = await response.Content.ReadAsStringAsync();

            Assert.AreEqual("10", result);
        }


        [TestMethod]
        public async Task Test_Reset_2()
        {
            WebApplicationFactory<Startup> appFactory = new WebApplicationFactory<Startup>();
            HttpClient _client = appFactory.CreateClient();

            HttpContent content = new StringContent("", Encoding.UTF8, "application/json");

            var response = await _client.PostAsync("http://localhost:8888/root/reset", content);
            Assert.AreEqual(HttpStatusCode.OK, response.StatusCode);

            response = await _client.PostAsync("http://localhost:8888/root/reset", content);
            Assert.AreEqual(HttpStatusCode.BadRequest, response.StatusCode);
        }

    }
}
