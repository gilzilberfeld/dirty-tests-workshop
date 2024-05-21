import axios, { AxiosInstance, AxiosRequestConfig, AxiosResponse } from "axios";
import exp from "constants";

describe("API tests", () => {
  test("update Todo", async () => {
    const client: AxiosInstance = axios.create();

    // get the X-challenger header to be used in all other APIs.
    let response: AxiosResponse = await client.post("https://apichallenges.herokuapp.com/challenger", "");
    const challengerHeader: string | null = response.headers["x-challenger"];

    expect(challengerHeader).not.toBeNull();

    // Get all todos
    response = await axios.get("https://apichallenges.herokuapp.com/todos", {
      headers: {
        "X-Challenger": challengerHeader,
      },
    });

    let { todos } = response.data;
    const theTodoId = todos[0].id;
    const anotherTodoId = todos[1].id;
    const deleteTodoId = todos[2].id;

    expect(theTodoId).toBeGreaterThan(0);

    // Update existing todo
    let body = `<todo>
    <doneStatus>true</doneStatus>
    <description>dognabbit</description>
    <title>Updated</title>
    </todo>`;

    let config: AxiosRequestConfig = {
      url: `https://apichallenges.herokuapp.com/todos/${theTodoId}`,
      method: "post",
      headers: {
        Accept: "application/json",
        "X-Challenger": challengerHeader,
        "Content-Type": "application/xml",
      },
      data: body,
    };

    response = await axios(config);
    expect(response.status).toBe(200);
    let todo = response.data;
    expect(todo.id).toBe(theTodoId);
    expect(todo.title).toBe("Updated");

    // check the updated todo is in the todos list

    response = await axios.get("https://apichallenges.herokuapp.com/todos", {
      headers: {
        "X-Challenger": challengerHeader,
      },
    });

    todos = response.data.todos;
    let todoExists = false;

    for (let i = 0; i < todos.length; i++) {
      if (todos[i].id === theTodoId) {
        todoExists = true;
        break;
      }
    }
    expect(todoExists).toBe(true);

    // check the updated todo
    body = `<todo>
    <doneStatus>true</doneStatus>
    <description>updatedDescription</description>
    <title>Updated</title>
    </todo>`;

    config = {
      url: `https://apichallenges.herokuapp.com/todos/${theTodoId}`,
      method: "post",
      headers: {
        Accept: "application/json",
        "X-Challenger": challengerHeader,
        "Content-Type": "application/xml",
      },
      data: body,
    };

    response = await axios(config);
    expect(response.status).toBe(200);
    todo = response.data;
    expect(todo.id).toBe(theTodoId);
    expect(todo.description).toBe("updatedDescription");

    // update another todo

    body = `
            <todo>
                <doneStatus>true</doneStatus>
                <description>dognabbit</description>
                <title>Updated</title>
            </todo>
        `;

    config = {
      headers: {
        Accept: "application/json",
        "X-Challenger": challengerHeader,
        "Content-Type": "application/xml",
      },
    };

    response = await axios.post(`https://apichallenges.herokuapp.com/todos/${anotherTodoId}`, body, config);

    expect(response.status).toBe(200);
    todo = response.data;
    expect(todo.id).toBe(anotherTodoId);
    expect(todo.title).toBe("Updated");

    // check the first todo is still updated
    config = {
      headers: {
        "X-Challenger": challengerHeader,
      },
    };

    response = await axios.get(`https://apichallenges.herokuapp.com/todos?id=${theTodoId}`, config);

    expect(response.status).toBe(200);
    let root = response.data;
    todo = root.todos[0];
    expect(todo).not.toBeNull();
    expect(todo.title).toBe("Updated");

    // check both updated todos are in the all todos list
    config = {
      headers: {
        "X-Challenger": challengerHeader,
      },
    };

    response = await axios.get("https://apichallenges.herokuapp.com/todos", config);
    todos = response.data.todos;

    todoExists = false;
    let anotherTodoExists = false;

    for (const aTodo of todos) {
      const id = aTodo.id;
      if (id === theTodoId) {
        todoExists = true;
      }
      if (id === anotherTodoId) {
        anotherTodoExists = true;
      }
      if (todoExists && anotherTodoExists) {
        break;
      }
    }
    expect(todoExists).toBeTruthy();
    expect(anotherTodoExists).toBeTruthy();

    // create a new todo
    let requestBody = {
      title: "newTitle",
      doneStatus: false,
      description: "dognabbit",
    };

    config = {
      headers: {
        "Content-Type": "application/json",
        "X-Challenger": challengerHeader,
      },
    };

    response = await axios.post("https://apichallenges.herokuapp.com/todos", requestBody, config);

    expect(response.status).toBe(201);
    todo = response.data;
    let createdTodoId = todo.id;
    expect(createdTodoId).not.toEqual(0);

    // check the create todos is created correctly

    config = {
      headers: {
        "X-Challenger": challengerHeader,
      },
    };

    response = await axios.get(`https://apichallenges.herokuapp.com/todos?id=${createdTodoId}`, config);
    todos = response.data.todos;
    todo = todos[0];
    expect(todo.id).toBe(createdTodoId);
    expect(todo.title).toBe("newTitle");

    // delete a todo
    config = {
      headers: {
        "X-Challenger": challengerHeader,
      },
    };

    response = await axios.delete(`https://apichallenges.herokuapp.com/todos/${deleteTodoId}`, config);
    expect(response.status).toBe(200);

    // make sure the deleted todo is gone
    config = {
      headers: {
        "X-Challenger": challengerHeader,
      },
    };

    response = await axios.get(`https://apichallenges.herokuapp.com/todos?id=${deleteTodoId}`, config);
    todos = response.data.todos;
    expect(todos.length).toBe(0);

    // try to update the deleted todo
    body = `
                        <todo>
                            <doneStatus>true</doneStatus>
                            <description>dognabbit</description>
                            <title>Updated</title>
                        </todo>
                    `;

    config = {
      headers: {
        Accept: "application/json",
        "X-Challenger": challengerHeader,
        "Content-Type": "application/xml",
      },
    };

    response = await axios.post(`https://apichallenges.herokuapp.com/todos/${deleteTodoId}`, body, config);
    expect(response.status).toBe(404);
    // Update the todo we created

    body = `
            <todo>
                <doneStatus>true</doneStatus>
                <description>dognabbit</description>
                <title>Reupdated</title>
            </todo>
        `;

    config = {
      headers: {
        Accept: "application/json",
        "X-Challenger": challengerHeader,
        "Content-Type": "application/xml",
      },
    };

    response = await axios.post(`https://apichallenges.herokuapp.com/todos/${createdTodoId}`, body, config);
    expect(response.status).toBe(200);
    todo = response.data;
    expect(todo.title).toBe("Reupdated");

    // check the created todo was updated correctly

    config = {
      headers: {
        "X-Challenger": challengerHeader,
      },
    };

    response = await axios.get(`https://apichallenges.herokuapp.com/todos?id=${createdTodoId}`, config);
    todos = response.data.todos;
    todo = todos[0];
    expect(todo.title).toBe("Reupdated");

    // Check the created and updated todo appears correcetly in the todos list
    config = {
      headers: {
        "X-Challenger": challengerHeader,
      },
    };

    response = await axios.get(`https://apichallenges.herokuapp.com/todos`, config);
    todos = response.data.todos;

    let theTitle = "";
    for (const aTodo of todos) {
      const id = aTodo.id;
      if (id === createdTodoId) {
        theTitle = aTodo.title;
        break;
      }
    }
    expect(theTitle).toBe("Reuptdated");
  }, 20000);
});
