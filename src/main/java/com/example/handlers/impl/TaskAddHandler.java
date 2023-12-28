package com.example.handlers.impl;

import com.example.handlers.AbstractHandler;
import com.example.http.HttpTaskResponse;
import com.example.model.ITaskManager;
import com.example.model.impl.Task;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

public class TaskAddHandler extends AbstractHandler {

    public TaskAddHandler(ITaskManager manager) {
        super(manager);
    }

    @Override
    public String getRequestUrl() {
        return "/api/tasks/add";
    }

    @Override
    public HttpTaskResponse handle(HttpExchange exchange) {
        System.out.println("Запрос на добавление задачи");
        String requestBody = getRequestString(exchange);
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
