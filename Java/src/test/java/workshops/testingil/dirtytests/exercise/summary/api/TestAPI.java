package workshops.testingil.dirtytests.exercise.summary.api;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static java.lang.Integer.parseInt;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestAPI {
    @Test
    public void updateTodo() throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://apichallenges.herokuapp.com/challenger")
                .method("POST", body)
                .build();
        Response response = client.newCall(request).execute();
        String challangerHeader = response.header("X-Challenger");

        assertThat(challangerHeader).isNotNull();
        //////////////

        request = new Request.Builder()
                .url("https://apichallenges.herokuapp.com/todos")
                .header("X-Challenger", challangerHeader)
                .method("GET", null)
                .build();
        response = client.newCall(request).execute();
        String jsonData = response.body().string();
        JSONObject root = new JSONObject(jsonData);
        JSONArray todos = root.getJSONArray("todos");
        int theTodoId = todos.getJSONObject(0).getInt("id");
        int anotherTodoId = todos.getJSONObject(1).getInt("id");
        assertThat(theTodoId).isGreaterThan(0);

        ////////////////////
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
                .addHeader("X-Challenger", challangerHeader)
                .addHeader("Content-Type", "application/xml")
                .build();
        response = client.newCall(request).execute();
        assertThat(response.code()).isEqualTo(200);
        JSONObject todo = new JSONObject(response.body().string());
        assertThat(todo.getInt("id")).isEqualTo(theTodoId);
        assertThat(todo.getString("title")).isEqualTo("Updated");

        ////////////////////////////////
        request = new Request.Builder()
                .url("https://apichallenges.herokuapp.com/todos")
                .method("GET", null)
                .addHeader("X-Challenger", challangerHeader)
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
        ///////////////////////

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
                .addHeader("X-Challenger", challangerHeader)
                .addHeader("Content-Type", "application/xml")
                .build();
        response = client.newCall(request).execute();
        assertThat(response.code()).isEqualTo(200);
        todo = new JSONObject(response.body().string());
        assertThat(todo.getInt("id")).isEqualTo(theTodoId);
        assertThat(todo.getString("description")).isEqualTo("updatedDescription");

        //////////////////////////////////////////

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
                .addHeader("X-Challenger", challangerHeader)
                .addHeader("Content-Type", "application/xml")
                .build();
        response = client.newCall(request).execute();
        assertThat(response.code()).isEqualTo(200);
        todo = new JSONObject(response.body().string());
        assertThat(todo.getInt("id")).isEqualTo(anotherTodoId);
        assertThat(todo.getString("title")).isEqualTo("Updated");

        ///////////////////////////////////////
        request = new Request.Builder()
                .url("https://apichallenges.herokuapp.com/todos?id=" + Integer.toString(theTodoId))
                .method("GET", null)
                .addHeader("X-Challenger", challangerHeader)
                .build();
        response = client.newCall(request).execute();
        assertThat(response.code()).isEqualTo(200);
        root = new JSONObject(response.body().string());
        todo = root.getJSONArray("todos").getJSONObject(0);
        assertThat(todo).isNotNull();
        assertThat(todo.getString("title")).isEqualTo("Updated");

        //////////////////////////////////////////////

        request = new Request.Builder()
                .url("https://apichallenges.herokuapp.com/todos")
                .method("GET", null)
                .addHeader("X-Challenger", challangerHeader)
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
    }
}
