package com.example.handlers.impl;

import com.example.handlers.HttpAbstractHandler;
import com.example.http.HttpMethod;
import com.example.http.HttpTaskResponse;
import com.example.model.ITask;
import com.example.model.ITaskManager;
import com.example.model.impl.Task;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

public class TaskUpdateHandler extends HttpAbstractHandler {

    public TaskUpdateHandler(ITaskManager manager) {
        super(manager);
    }

    @Override
    public String getRequestPath() {
        return "/api/tasks/update";
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.PATCH;
    }

    @Override
    public HttpTaskResponse handle(HttpExchange exchange) {
        String requestBody = getRequestBody(exchange);
        Task request = new Gson().fromJson(requestBody, Task.class);
        ITask response = manager.updateTask(request);
        Long id = response.getId();
        String responseText;
        if (id != null) {
            if (id > -1) {
                responseText = "Задача обновлена успешно: Id=" + id;
            } else {
                responseText = "Задача найдена, но не обновлена.";
            }
        } else {
            responseText = "Задача для обновления не найдена";
        }
        return HttpTaskResponse.ok(exchange, responseText);
    }

}
