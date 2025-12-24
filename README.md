# Task Tracker App

## 1. Introduction
Task tracker app with simple CRUD.
![Gif](media/task-app.gif)

## 2. Role
Backend developer implements controllers, services and datasource management, handle api response for the front end web app

## 3. Tech
- Spring Boot
- React
- Vite
- JPA / Hibernate
- Swagger (OpenAPI)
- PostgreSQL
- H2 (for testing)

Relevant files:
- [`com.project.task_backend.TaskBackendApplication`](task-backend/src/main/java/com/project/task_backend/TaskBackendApplication.java)
- [task-backend/pom.xml](task-backend/pom.xml)
- [task-frontend/package.json](task-frontend/package.json)
- [task-frontend/src/main.tsx](task-frontend/src/main.tsx)
- [task-frontend/vite.config.ts](task-frontend/vite.config.ts)
- [task-frontend/Dockerfile](task-frontend/Dockerfile)

## 4. ERD
(placeholder — image to be inserted)

## 5. Endpoints
Task lists:
- GET /api/task-lists — list task lists  
  Controller: [`com.project.task_backend.controller.TaskListController`](task-backend/src/main/java/com/project/task_backend/controller/TaskListController.java)
- GET /api/task-lists/{task_list_id} — get single task list  
  DTO: [`com.project.task_backend.domain.dto.TaskListDto`](task-backend/src/main/java/com/project/task_backend/domain/dto/TaskListDto.java)
- POST /api/task-lists — create task list
- PUT /api/task-lists/{task_list_id} — update task list
- DELETE /api/task-lists/{task_list_id} — delete task list

Tasks:
- GET /api/task-lists/{task_list_id}/tasks — list tasks  
  Controller: [`com.project.task_backend.controller.TaskController`](task-backend/src/main/java/com/project/task_backend/controller/TaskController.java)
- GET /api/task-lists/{task_list_id}/tasks/{task_id} — get single task  
  DTO: [`com.project.task_backend.domain.dto.TaskDto`](task-backend/src/main/java/com/project/task_backend/domain/dto/TaskDto.java)
- POST /api/task-lists/{task_list_id}/tasks — create task
- PUT /api/task-lists/{task_list_id}/tasks/{task_id} — update task
- DELETE /api/task-lists/{task_list_id}/tasks/{task_id} — delete task

For repository and service implementations see:
- [`com.project.task_backend.repository.TaskListRepository`](task-backend/src/main/java/com/project/task_backend/repository/TaskListRepository.java)
- [`com.project.task_backend.repository.TaskRepository`](task-backend/src/main/java/com/project/task_backend/repository/TaskRepository.java)
- [`com.project.task_backend.service.TaskListService`](task-backend/src/main/java/com/project/task_backend/service/TaskListService.java)
- [`com.project.task_backend.service.TaskService`](task-backend/src/main/java/com/project/task_backend/service/TaskService.java)