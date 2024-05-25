import requests
import json


def test_updated_todos():
    url = "https://apichallenges.herokuapp.com/challenger"
    headers = {"Content-Type": "text/plain"}

    response = requests.post(url, headers=headers)
    challengerHeader = response.headers.get("X-Challenger")

    assert challengerHeader is not None

    # Get all todos
    url = "https://apichallenges.herokuapp.com/todos"
    headers = {"X-Challenger": challengerHeader}

    response = requests.get(url, headers=headers)
    json_data = response.json()

    todos = json_data.get("todos", [])
    the_todo_id = todos[0].get("id", 0)
    another_todo_id = todos[1].get("id", 0)
    delete_todo_id = todos[2].get("id", 0)

    assert the_todo_id > 0

    # Update existing todo
    url = f"https://apichallenges.herokuapp.com/todos/{the_todo_id}"
    headers = {
        "Accept": "application/json",
        "X-Challenger": challengerHeader,
        "Content-Type": "application/xml",
    }

    body = """
    <todo>
        <doneStatus>true</doneStatus>
        <description>dognabbit</description>
        <title>Updated</title>
    </todo>
    """

    response = requests.post(url, headers=headers, data=body)
    assert response.status_code == 200
    todo = response.json()
    assert todo.get("id") == the_todo_id
    assert todo.get("title") == "Updated"

    # check the updated todo is in the todos list
    url = "https://apichallenges.herokuapp.com/todos"
    headers = {"X-Challenger": challengerHeader}
    response = requests.get(url, headers=headers)
    json_data = response.json()
    todos = json_data.get("todos", [])
    todo_exists = any(todo.get("id") == the_todo_id for todo in todos)
    assert todo_exists

    # check the updated todo
    url = f"https://apichallenges.herokuapp.com/todos/{the_todo_id}"
    headers = {
        "Accept": "application/json",
        "X-Challenger": challengerHeader,
        "Content-Type": "application/xml",
    }

    body = """
    <todo>
        <doneStatus>true</doneStatus>
        <description>updatedDescription</description>
        <title>Updated</title>
    </todo>
    """
    response = requests.post(url, headers=headers, data=body)
    assert response.status_code == 200
    todo = response.json()
    assert todo.get("id") == the_todo_id
    assert todo.get("description") == "updatedDescription"

    # update another todo
    url = f"https://apichallenges.herokuapp.com/todos/{another_todo_id}"
    headers = {
        "Accept": "application/json",
        "X-Challenger": challengerHeader,
        "Content-Type": "application/xml",
    }

    body = """
    <todo>
        <doneStatus>true</doneStatus>
        <description>dognabbit</description>
        <title>Updated</title>
    </todo>
    """

    response = requests.post(url, headers=headers, data=body)
    assert response.status_code == 200
    todo = response.json()
    assert todo.get("id") == another_todo_id
    assert todo.get("title") == "Updated"

    # check the first todo is still updated
    url = f"https://apichallenges.herokuapp.com/todos?id={the_todo_id}"
    headers = {"X-Challenger": challengerHeader}
    response = requests.get(url, headers=headers)
    assert response.status_code == 200
    root = response.json()
    todos = root.get("todos", [])
    todo = todos[0]
    assert todo is not None
    assert todo.get("title") == "Updated"

    #  check both updated todos are in the all todos list
    url = "https://apichallenges.herokuapp.com/todos"
    headers = {"X-Challenger": challengerHeader}
    response = requests.get(url, headers=headers)
    json_data = response.json()
    todos = json_data.get("todos", [])
    todo_exists = any(todo.get("id") == the_todo_id for todo in todos)
    another_todo_exists = any(todo.get("id") == another_todo_id for todo in todos)

    # Assert that both todos exist
    assert todo_exists, f"Todo with ID {the_todo_id} does not exist!"
    assert another_todo_exists, f"Todo with ID {another_todo_id} does not exist!"

    # create a new todo
    url = "https://apichallenges.herokuapp.com/todos"
    headers = {
        "Content-Type": "application/json",
        "X-Challenger": challengerHeader,
    }
    body = {
        "title": "newTitle",
        "doneStatus": False,
        "description": "dognabbit",
    }

    response = requests.post(url, headers=headers, json=body)
    assert response.status_code == 201
    todo = response.json()
    created_todo_id = todo.get("id", 0)
    assert created_todo_id != 0

    # check the created todo is created correctly
    url = f"https://apichallenges.herokuapp.com/todos?id={created_todo_id}"
    headers = {"X-Challenger": challengerHeader}
    response = requests.get(url, headers=headers)
    assert response.status_code == 200
    root = response.json()
    todos = root.get("todos", [])
    todo = todos[0]
    assert todo.get("id") == created_todo_id
    assert todo.get("title") == "newTitle"

    # delete a todo
    url = f"https://apichallenges.herokuapp.com/todos/{delete_todo_id}"
    headers = {"X-Challenger": challengerHeader}
    response = requests.delete(url, headers=headers)
    assert response.status_code == 200

    # make sure the deleted todo is gone
    url = f"https://apichallenges.herokuapp.com/todos?id={delete_todo_id}"
    headers = {"X-Challenger": challengerHeader}
    response = requests.get(url, headers=headers)
    assert response.status_code == 200
    root = response.json()
    todos = root.get("todos", [])
    assert len(todos) == 0

    # try to update the deleted todo
    url = f"https://apichallenges.herokuapp.com/todos/{delete_todo_id}"
    headers = {
        "Accept": "application/json",
        "X-Challenger": challengerHeader,
        "Content-Type": "application/xml",
    }
    body = """
    <todo>
        <doneStatus>true</doneStatus>
        <description>dognabbit</description>
        <title>Updated</title>
    </todo>
    """
    response = requests.post(url, headers=headers, data=body)
    assert response.status_code == 404

    # Update the todo we created
    url = f"https://apichallenges.herokuapp.com/todos/{created_todo_id}"
    headers = {
        "Accept": "application/json",
        "X-Challenger": challengerHeader,
        "Content-Type": "application/xml",
    }
    body = """
    <todo>
        <doneStatus>true</doneStatus>
        <description>dognabbit</description>
        <title>Reupdated</title>
    </todo>
    """
    response = requests.post(url, headers=headers, data=body)
    assert response.status_code == 200
    todo = response.json()
    assert todo.get("title") == "Reupdated"

    # check the created todo was updated correctly
    url = f"https://apichallenges.herokuapp.com/todos?id={created_todo_id}"
    headers = {"X-Challenger": challengerHeader}
    response = requests.get(url, headers=headers)
    assert response.status_code == 200
    root = response.json()
    todos = root.get("todos", [])
    todo = todos[0]
    assert todo.get("title") == "Reupdated"

    # Check the created and updated todo appears correctly in the todos list
    url = "https://apichallenges.herokuapp.com/todos"
    headers = {"X-Challenger": challengerHeader}
    response = requests.get(url, headers=headers)
    json_data = response.json()
    todos = json_data.get("todos", [])
    the_title = ""
    for todo in todos:
        if todo.get("id") == created_todo_id:
            the_title = todo.get("title", "")
            break
    assert the_title == "Reupdated"
