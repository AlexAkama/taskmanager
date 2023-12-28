package com.example.handlers.impl;

import com.example.handlers.AbstractHandler;
import com.example.http.HttpTaskResponse;
import com.example.model.ITaskManager;
import com.example.model.impl.Task;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

public class TaskUpdateHand extends AbstractHandler {

    public TaskUpdateHand(ITaskManager manager) {
        super(manager);
    }

    @Override
    public String getRequestUrl() {
        return "/api/tasks/update";
    }

    @Override
    public HttpTaskResponse handle(HttpExchange exchange) {
        String requestBody = getRequestString(exchange);
        Task task = new Gson().fromJson(requestBody, Task.class);
        Long id = manager.updateTask(task);
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
