package workshops.testingil.dirtytests.exercise;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UpdateTodoTests {
    @Test
    public void updateTodo() throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");

        // get the X-challenger header to be used in all other APIs.
        Request request = new Request.Builder()
                .url("https://apichallenges.herokuapp.com/challenger")
                .method("POST", body)
                .build();
        Response response = client.newCall(request).execute();
        String challengerHeader = response.header("X-Challenger");

        assertThat(challengerHeader).isNotNull();
        // Get all todos

        request = new Request.Builder()
                .url("https://apichallenges.herokuapp.com/todos")
                .header("X-Challenger", challengerHeader)
                .method("GET", null)
                .build();
        response = client.newCall(request).execute();
        String jsonData = response.body().string();
        JSONObject root = new JSONObject(jsonData);
        JSONArray todos = root.getJSONArray("todos");
        int theTodoId = todos.getJSONObject(0).getInt("id");
        int anotherTodoId = todos.getJSONObject(1).getInt("id");
        int deleteTodoId = todos.getJSONObject(2).getInt("id");
        assertThat(theTodoId).isGreaterThan(0);

        // Update existing todo
        mediaType = MediaType.parse("application/xml");
        body = RequestBody.create(mediaType, "<todo>\r\n" +
                "    <doneStatus>true</doneStatus>\r\n" +
                "    <description>dognabbit</description>\r\n" +
                "    <title>Updated</title>\r\n" +
                "</todo>");
        request = new Request.Builder()
                .url("https://apichallenges.herokuapp.com/todos/" + theTodoId)
                .method("POST", body)
                .addHeader("Accept", "application/json")
                .addHeader("X-Challenger", challengerHeader)
                .addHeader("Content-Type", "application/xml")
                .build();
        response = client.newCall(request).execute();
        assertThat(response.code()).isEqualTo(200);
        JSONObject todo = new JSONObject(response.body().string());
        assertThat(todo.getInt("id")).isEqualTo(theTodoId);
        assertThat(todo.getString("title")).isEqualTo("Updated");

        // check the updated todo is in the todos list
        request = new Request.Builder()
                .url("https://apichallenges.herokuapp.com/todos")
                .method("GET", null)
                .addHeader("X-Challenger", challengerHeader)
                .build();
        response = client.newCall(request).execute();
        root = new JSONObject(response.body().string());
        todos = root.getJSONArray("todos");

        boolean todoExists = false;
        for (int i = 0; i < todos.length(); i++) {
            JSONObject aTodo = todos.getJSONObject(i);
            int id = aTodo.getInt("id");
            if (id == theTodoId) {
                todoExists = true;
                break;
            }
        }
        assertThat(todoExists).isTrue();
        // check the updated todo

        mediaType = MediaType.parse("application/xml");
        body = RequestBody.create(mediaType, "<todo>\r\n" +
                "    <doneStatus>true</doneStatus>\r\n" +
                "    <description>updatedDescription</description>\r\n" +
                "    <title>Updated</title>\r\n" +
                "</todo>");
        request = new Request.Builder()
                .url("https://apichallenges.herokuapp.com/todos/" + theTodoId)
                .method("POST", body)
                .addHeader("Accept", "application/json")
                .addHeader("X-Challenger", challengerHeader)
                .addHeader("Content-Type", "application/xml")
                .build();
        response = client.newCall(request).execute();
        assertThat(response.code()).isEqualTo(200);
        todo = new JSONObject(response.body().string());
        assertThat(todo.getInt("id")).isEqualTo(theTodoId);
        assertThat(todo.getString("description")).isEqualTo("updatedDescription");

        // update another todo

        mediaType = MediaType.parse("application/xml");
        body = RequestBody.create(mediaType, "<todo>\r\n" +
                "    <doneStatus>true</doneStatus>\r\n" +
                "    <description>dognabbit</description>\r\n" +
                "    <title>Updated</title>\r\n" +
                "</todo>");
        request = new Request.Builder()
                .url("https://apichallenges.herokuapp.com/todos/" + Integer.toString(anotherTodoId))
                .method("POST", body)
                .addHeader("Accept", "application/json")
                .addHeader("X-Challenger", challengerHeader)
                .addHeader("Content-Type", "application/xml")
                .build();
        response = client.newCall(request).execute();
        assertThat(response.code()).isEqualTo(200);
        todo = new JSONObject(response.body().string());
        assertThat(todo.getInt("id")).isEqualTo(anotherTodoId);
        assertThat(todo.getString("title")).isEqualTo("Updated");

        // check the first todo is still updated
        request = new Request.Builder()
                .url("https://apichallenges.herokuapp.com/todos?id=" + Integer.toString(theTodoId))
                .method("GET", null)
                .addHeader("X-Challenger", challengerHeader)
                .build();
        response = client.newCall(request).execute();
        assertThat(response.code()).isEqualTo(200);
        root = new JSONObject(response.body().string());
        todo = root.getJSONArray("todos").getJSONObject(0);
        assertThat(todo).isNotNull();
        assertThat(todo.getString("title")).isEqualTo("Updated");

        // check both updated todos are in the all todos list

        request = new Request.Builder()
                .url("https://apichallenges.herokuapp.com/todos")
                .method("GET", null)
                .addHeader("X-Challenger", challengerHeader)
                .build();
        response = client.newCall(request).execute();
        root = new JSONObject(response.body().string());
        todos = root.getJSONArray("todos");

        todoExists = false;
        boolean anotherTodoExists = false;
        for (int i = 0; i < todos.length(); i++) {
            JSONObject aTodo = todos.getJSONObject(i);
            int id = aTodo.getInt("id");
            if (id == theTodoId) {
                todoExists = true;
            }
            if (id == anotherTodoId) {
                anotherTodoExists = true;
            }
            if (todoExists && anotherTodoExists)
                break;
        }
        assertThat(todoExists).isTrue();
        assertThat(anotherTodoExists).isTrue();

        // create a new todo
        mediaType = MediaType.parse("application/json");
        body = RequestBody.create(mediaType, "{\r\n" +
                "            \"title\": \"newTitle\",\r\n" +
                "            \"doneStatus\": false,\r\n" +
                "            \"description\": \"dognabbit\"\r\n" +
                "        }");
        request = new Request.Builder()
                .url("https://apichallenges.herokuapp.com/todos")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("X-Challenger", challengerHeader)
                .build();
        response = client.newCall(request).execute();
        assertThat(response.code()).isEqualTo(201);
        todo = new JSONObject(response.body().string());
        int createdTodoId = todo.getInt("id");
        assertThat(createdTodoId).isNotEqualTo(0);

        // check the created todo is created correctly

        request = new Request.Builder()
                .url("https://apichallenges.herokuapp.com/todos?id=" + Integer.toString(createdTodoId))
                .method("GET", null)
                .addHeader("X-Challenger", challengerHeader)
                .build();
        response = client.newCall(request).execute();
        assertThat(response.code()).isEqualTo(200);
        root = new JSONObject(response.body().string());
        todos = root.getJSONArray("todos");
        todo = todos.getJSONObject(0);
        assertThat(todo.getInt("id")).isEqualTo(createdTodoId);
        assertThat(todo.getString("title")).isEqualTo("newTitle");

        // delete a todo

        request = new Request.Builder()
                .url("https://apichallenges.herokuapp.com/todos/" + Integer.toString(deleteTodoId))
                .addHeader("X-Challenger", challengerHeader)
                .method("DELETE", null)
                .build();
        response = client.newCall(request).execute();
        assertThat(response.code()).isEqualTo(200);

        // make sure the deleted todo is gone

        request = new Request.Builder()
                .url("https://apichallenges.herokuapp.com/todos?id=" + Integer.toString(deleteTodoId))
                .method("GET", null)
                .addHeader("X-Challenger", challengerHeader)
                .build();
        response = client.newCall(request).execute();
        root = new JSONObject(response.body().string());
        todos = root.getJSONArray("todos");
        assertThat(todos.length()).isEqualTo(0);

        // try to update the deleted todo
        mediaType = MediaType.parse("application/xml");
        body = RequestBody.create(mediaType, "<todo>\r\n" +
                "    <doneStatus>true</doneStatus>\r\n" +
                "    <description>dognabbit</description>\r\n" +
                "    <title>Updated</title>\r\n" +
                "</todo>");
        request = new Request.Builder()
                .url("https://apichallenges.herokuapp.com/todos/" + Integer.toString(deleteTodoId))
                .method("POST", body)
                .addHeader("Accept", "application/json")
                .addHeader("X-Challenger", challengerHeader)
                .addHeader("Content-Type", "application/xml")
                .build();
        response = client.newCall(request).execute();
        assertThat(response.code()).isEqualTo(404);

        // Update the todo we created

        mediaType = MediaType.parse("application/xml");
        body = RequestBody.create(mediaType, "<todo>\r\n" +
                "    <doneStatus>true</doneStatus>\r\n" +
                "    <description>dognabbit</description>\r\n" +
                "    <title>Reupdated</title>\r\n" +
                "</todo>");
        request = new Request.Builder()
                .url("https://apichallenges.herokuapp.com/todos/" + Integer.toString(createdTodoId))
                .method("POST", body)
                .addHeader("Accept", "application/json")
                .addHeader("X-Challenger", challengerHeader)
                .addHeader("Content-Type", "application/xml")
                .build();
        response = client.newCall(request).execute();
        assertThat(response.code()).isEqualTo(200);
        todo = new JSONObject(response.body().string());
        assertThat(todo.getString("title")).isEqualTo("Reupdated");

        // check the created todo was updated correctly

        request = new Request.Builder()
                .url("https://apichallenges.herokuapp.com/todos?id=" + Integer.toString(createdTodoId))
                .addHeader("X-Challenger", challengerHeader)
                .method("GET", null)
                .build();
        response = client.newCall(request).execute();
        assertThat(response.code()).isEqualTo(200);
        root = new JSONObject(response.body().string());
        todo = root.getJSONArray("todos").getJSONObject(0);
        assertThat(todo.getString("title")).isEqualTo("Reupdated");

        // Check the created and updated todo appears correctly in the todos list

        request = new Request.Builder()
                .url("https://apichallenges.herokuapp.com/todos")
                .method("GET", null)
                .addHeader("X-Challenger", challengerHeader)
                .build();
        response = client.newCall(request).execute();
        root = new JSONObject(response.body().string());
        todos = root.getJSONArray("todos");

        String theTitle = "";
        for (int i = 0; i < todos.length(); i++) {
            JSONObject aTodo = todos.getJSONObject(i);
            int id = aTodo.getInt("id");
            if (id == createdTodoId) {
                theTitle = aTodo.getString("title");
                break;
            }
        }
        assertThat(theTitle).isEqualTo("Reupdated");

    }

}
