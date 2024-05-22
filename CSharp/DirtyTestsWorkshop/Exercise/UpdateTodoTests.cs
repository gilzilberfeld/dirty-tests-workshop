using Newtonsoft.Json.Linq;
using NUnit.Framework;
using System.Net.Http.Headers;
using System;
using System.Net.Http;
using System.Threading.Tasks;
using Newtonsoft.Json.Linq;
using System.Text;
using System.Net;

namespace DirtyTestsWorkshop.Exercise
{
    [TestFixture]
    public class UpdateTodoTests
    {
        [Test]
        public async Task UpdateTodo()
        {

            var client = new HttpClient();
            var mediaType = new MediaTypeHeaderValue("text/plain");
            var body = new StringContent("", mediaType);

            // Get the X-challenger header to be used in all other APIs.
            var response = await client.PostAsync("https://apichallenges.herokuapp.com/challenger", body);
            var challengerHeader = response.Headers.GetValues("X-Challenger").FirstOrDefault();

            Assert.That(challengerHeader, Is.Not.Null);
            Assert.That(challengerHeader, Is.Not.Null);

            // Get all todos
            client.DefaultRequestHeaders.Add("X-Challenger", challengerHeader);
            var todosResponse = await client.GetAsync("https://apichallenges.herokuapp.com/todos");
            var jsonData = await todosResponse.Content.ReadAsStringAsync();

            var root = JObject.Parse(jsonData);
            var todos = root["todos"];
            var theTodoId = (int)todos[0]["id"];
            var anotherTodoId = (int)todos[1]["id"];
            var deleteTodoId = (int)todos[2]["id"];

            Assert.That(theTodoId, Is.GreaterThan(0));

            // Update existing todo
            body = new StringContent("<todo>\r\n" +
                "    <doneStatus>true</doneStatus>\r\n" +
                "    <description>dognabbit</description>\r\n" +
                "    <title>Updated</title>\r\n" +
                "</todo>", Encoding.UTF8, "application/xml");

            client.DefaultRequestHeaders.Clear();
            client.DefaultRequestHeaders.Add("Accept", "application/json");
            client.DefaultRequestHeaders.Add("X-Challenger", challengerHeader);

            response = await client.PostAsync($"https://apichallenges.herokuapp.com/todos/{theTodoId}", body);

            Assert.That(response.StatusCode, Is.EqualTo(HttpStatusCode.OK));
            var todo = JObject.Parse(await response.Content.ReadAsStringAsync());

            Assert.That(todo.Value<int>("id"), Is.EqualTo(theTodoId));
            Assert.That(todo.Value<string>("title"), Is.EqualTo("Updated"));

            // check the updated todo is in the todos list
            client.DefaultRequestHeaders.Clear();
            client.DefaultRequestHeaders.Add("X-Challenger", challengerHeader);

            response = await client.GetAsync("https://apichallenges.herokuapp.com/todos");

            root = JObject.Parse(await response.Content.ReadAsStringAsync());
            todos = root["todos"];
            var todoExists = false;

            foreach (var aTodo in todos)
            {
                var id = (int)aTodo["id"];
                if (id == theTodoId)
                {
                    todoExists = true;
                    break;
                }
            }

            Assert.That(todoExists, Is.True);
            // check the updated todo
            body = new StringContent("<todo>\r\n" +
            "    <doneStatus>true</doneStatus>\r\n" +
            "    <description>updatedDescription</description>\r\n" +
            "    <title>Updated</title>\r\n" +
            "</todo>", Encoding.UTF8, "application/xml");

            client.DefaultRequestHeaders.Clear();
            client.DefaultRequestHeaders.Add("Accept", "application/json");
            client.DefaultRequestHeaders.Add("X-Challenger", challengerHeader);

            response = await client.PostAsync($"https://apichallenges.herokuapp.com/todos/{theTodoId}", body);
            Assert.That(response.StatusCode, Is.EqualTo(HttpStatusCode.OK));
            todo = JObject.Parse(await response.Content.ReadAsStringAsync());

            Assert.That(todo.Value<int>("id"), Is.EqualTo(theTodoId));
            Assert.That(todo.Value<string>("description"), Is.EqualTo("updatedDescription"));

            body = new StringContent("<todo>\r\n" +
                "    <doneStatus>true</doneStatus>\r\n" +
                "    <description>dognabbit</description>\r\n" +
                "    <title>Updated</title>\r\n" +
                "</todo>", Encoding.UTF8, "application/xml");

            client.DefaultRequestHeaders.Clear();
            client.DefaultRequestHeaders.Add("Accept", "application/json");
            client.DefaultRequestHeaders.Add("X-Challenger", challengerHeader);

            response = await client.PostAsync($"https://apichallenges.herokuapp.com/todos/{anotherTodoId}", body);
            Assert.That(response.StatusCode, Is.EqualTo(HttpStatusCode.OK));
            todo = JObject.Parse(await response.Content.ReadAsStringAsync());

            Assert.That(todo.Value<int>("id"), Is.EqualTo(anotherTodoId));
            Assert.That(todo.Value<string>("title"), Is.EqualTo("Updated"));


            // check the first todo is still updated
            client.DefaultRequestHeaders.Clear();
            client.DefaultRequestHeaders.Add("X-Challenger", challengerHeader);

            response = await client.GetAsync($"https://apichallenges.herokuapp.com/todos?id={theTodoId}");
            Assert.That(response.StatusCode, Is.EqualTo(HttpStatusCode.OK));
            root = JObject.Parse(await response.Content.ReadAsStringAsync());
            todos = root["todos"];
            todo = (JObject)todos[0];
            Assert.That(todo, Is.Not.Null);
            Assert.That(todo.Value<string>("title"), Is.EqualTo("Updated"));

            // check both updated todos are in the all todos list
            client.DefaultRequestHeaders.Clear();
            client.DefaultRequestHeaders.Add("X-Challenger", challengerHeader);

            response = await client.GetAsync("https://apichallenges.herokuapp.com/todos");
            root = JObject.Parse(await response.Content.ReadAsStringAsync());
            todos = root["todos"];
            todoExists = false;
            var anotherTodoExists = false;

            foreach (var aTodo in todos)
            {
                var id = (int)aTodo["id"];
                if (id == theTodoId)
                {
                    todoExists = true;
                }
                if (id == anotherTodoId)
                {
                    anotherTodoExists = true;
                }
                if (todoExists && anotherTodoExists)
                {
                    break;
                }
            }
            Assert.That(todoExists, Is.True);
            Assert.That(anotherTodoExists, Is.True);

            // create a new todo
            body = new StringContent(
                    "{\r\n" +
                    "    \"title\": \"newTitle\",\r\n" +
                    "    \"doneStatus\": false,\r\n" +
                    "    \"description\": \"dognabbit\"\r\n" +
                    "}", Encoding.UTF8, "application/json");

            client.DefaultRequestHeaders.Clear();
            client.DefaultRequestHeaders.Add("X-Challenger", challengerHeader);

            response = await client.PostAsync("https://apichallenges.herokuapp.com/todos", body);
            Assert.That(response.StatusCode, Is.EqualTo(HttpStatusCode.Created));
            todo = JObject.Parse(await response.Content.ReadAsStringAsync());
            var createdTodoId = todo.Value<int>("id");
            Assert.That(createdTodoId, Is.Not.EqualTo(0));

            // check the created todo is created correctly
            client.DefaultRequestHeaders.Clear();
            client.DefaultRequestHeaders.Add("X-Challenger", challengerHeader);

            response = await client.GetAsync($"https://apichallenges.herokuapp.com/todos?id={createdTodoId}");
            Assert.That(response.StatusCode, Is.EqualTo(HttpStatusCode.OK));
            root = JObject.Parse(await response.Content.ReadAsStringAsync());
            todos = root["todos"];
            todo = (JObject)todos[0];
            Assert.That(todo.Value<int>("id"), Is.EqualTo(createdTodoId));
            Assert.That(todo.Value<string>("title"), Is.EqualTo("newTitle"));
            // delete a todo
            client.DefaultRequestHeaders.Clear();
            client.DefaultRequestHeaders.Add("X-Challenger", challengerHeader);

            response = await client.DeleteAsync($"https://apichallenges.herokuapp.com/todos/{deleteTodoId}");
            Assert.That(response.StatusCode, Is.EqualTo(HttpStatusCode.OK));
            // make sure the deleted todo is gone
            client.DefaultRequestHeaders.Clear();
            client.DefaultRequestHeaders.Add("X-Challenger", challengerHeader);

            response = await client.GetAsync($"https://apichallenges.herokuapp.com/todos?id={deleteTodoId}");
            Assert.That(response.StatusCode, Is.EqualTo(HttpStatusCode.OK));
            root = JObject.Parse(await response.Content.ReadAsStringAsync());
            todos = root["todos"];
            Assert.That(todos.Count, Is.EqualTo(0));

            // try to update the deleted todo
            body = new StringContent(
                "<todo>\r\n" +
                "    <doneStatus>true</doneStatus>\r\n" +
                "    <description>dognabbit</description>\r\n" +
                "    <title>Updated</title>\r\n" +
                "</todo>", Encoding.UTF8, "application/xml");


            // Attempt to update a non-existent todo
            client.DefaultRequestHeaders.Clear();
            client.DefaultRequestHeaders.Add("Accept", "application/json");
            client.DefaultRequestHeaders.Add("X-Challenger", challengerHeader);

            response = await client.PostAsync($"https://apichallenges.herokuapp.com/todos/{deleteTodoId}", body);
            Assert.That(response.StatusCode, Is.EqualTo(HttpStatusCode.NotFound));

            // Update the todo we created
            body = new StringContent(
                "<todo>\r\n" +
                "    <doneStatus>true</doneStatus>\r\n" +
                "    <description>dognabbit</description>\r\n" +
                "    <title>Reupdated</title>\r\n" +
                "</todo>", Encoding.UTF8, "application/xml");

            client.DefaultRequestHeaders.Clear();
            client.DefaultRequestHeaders.Add("Accept", "application/json");
            client.DefaultRequestHeaders.Add("X-Challenger", challengerHeader);

            response = await client.PostAsync($"https://apichallenges.herokuapp.com/todos/{createdTodoId}", body);
            Assert.That(response.StatusCode, Is.EqualTo(HttpStatusCode.OK));
            todo = JObject.Parse(await response.Content.ReadAsStringAsync());
            Assert.That(todo.Value<string>("title"), Is.EqualTo("Reupdated"));
            // check the created todo was updated correctly
            client.DefaultRequestHeaders.Clear();
            client.DefaultRequestHeaders.Add("X-Challenger", challengerHeader);

            response = await client.GetAsync($"https://apichallenges.herokuapp.com/todos?id={createdTodoId}");
            Assert.That(response.StatusCode, Is.EqualTo(HttpStatusCode.OK));
            root = JObject.Parse(await response.Content.ReadAsStringAsync());
            todos = root["todos"];
            todo = (JObject)todos[0];
            Assert.That(todo.Value<string>("title"), Is.EqualTo("Reupdated"));

            // Check the created and updated todo appears correcetly in the todos list
            client.DefaultRequestHeaders.Clear();
            client.DefaultRequestHeaders.Add("X-Challenger", challengerHeader);

            response = await client.GetAsync("https://apichallenges.herokuapp.com/todos");
            root = JObject.Parse(await response.Content.ReadAsStringAsync());
            todos = root["todos"];
            var theTitle = string.Empty;

            foreach (var aTodo in todos)
            {
                var id = aTodo.Value<int>("id");
                if (id == createdTodoId)
                {
                    theTitle = (string)aTodo["title"];
                    break;
                }
            }
            Assert.That(theTitle, Is.EqualTo("Reupdated"));
        }

    }

}



