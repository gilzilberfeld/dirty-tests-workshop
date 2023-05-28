using DirtyTestsWorkshop.App;
using System.Net;
using System.Text.Encodings.Web;
using System;
using NUnit.Framework;
using Newtonsoft.Json;
using System.Web;
using CsvHelper;
using System.Globalization;
using System.Net.Http.Headers;
using System.Net.Http;

namespace DirtyTestsWorkshop.Exercise.Temp
{
    [TestFixture]
    public class APITests
    {

        private const string path_to_file1 = @"Exercise\Resources\sequence_api_data.csv";
        private const string path_to_file2 = @"Exercise\Resources\slow_unit_test_data_2.csv";

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
