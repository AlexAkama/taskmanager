package com.example.handlers.impl;

import com.example.handlers.HttpAbstractHandler;
import com.example.http.HttpMethod;
import com.example.http.HttpTaskResponse;
import com.example.model.ITaskManager;
import com.example.model.impl.Task;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

public class TaskAddHandler extends HttpAbstractHandler {

    public TaskAddHandler(ITaskManager manager) {
        super(manager);
    }

    @Override
    public String getRequestPath() {
        return "/api/tasks/add";
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }

    @Override
    public HttpTaskResponse handle(HttpExchange exchange) {
        System.out.println("Запрос на добавление задачи");
        String requestBody = getRequestBody(exchange);
        Task task = new Gson().fromJson(requestBody, Task.class);
        Long id = manager.addTask(task);
        if (id != null) {
            if (id > -1) {
                return HttpTaskResponse.ok(exchange, "Задача добавлена успешно: Id=" + id);
            } else {
                return HttpTaskResponse.bad(exchange, "Задача не добавлена.");
            }
        } else {
            return HttpTaskResponse.serverError(exchange);
        }
    }

}
