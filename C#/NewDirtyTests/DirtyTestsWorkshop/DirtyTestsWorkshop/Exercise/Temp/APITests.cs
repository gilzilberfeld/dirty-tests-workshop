using DirtyTestsWorkshop.App;
using System.Net;
using System.Text.Encodings.Web;
using System;
using NUnit.Framework;
using Newtonsoft.Json;
using System.Web;

namespace DirtyTestsWorkshop.Exercise.Temp
{
    [TestFixture]
    public class APITests
    {
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
    }
}
