# To-Do List Application

This repository contains a basic skeleton for a multi-user To-Do List application.
It consists of a Java Spring Boot backend and a React frontend. The backend stores
users in MS SQL Server and tasks in MongoDB.

## Backend

The Spring Boot application is located in the `backend` directory. It exposes the
following REST endpoints:

- `POST /api/auth/register` – register a new user
- `POST /api/auth/login` – login (basic authentication for now)
- `GET /api/tasks` – list tasks for the current user
- `POST /api/tasks` – create a task
- `PUT /api/tasks/{id}` – update a task
- `DELETE /api/tasks/{id}` – delete a task

### Build

Install Maven and run:

```bash
mvn -f backend/pom.xml spring-boot:run
```

Adjust `src/main/resources/application.properties` for your SQL Server and MongoDB
connections.

## Frontend

The frontend folder is ready for a React application. You can bootstrap it with
Create React App or another tool. It should communicate with the backend API and
store the JWT token in `localStorage`.

This project is only a starting point and does not include full authentication or
a complete UI.
