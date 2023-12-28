package com.example.handlers.impl;

import com.example.handlers.AbstractHandler;
import com.example.http.HttpTaskResponse;
import com.example.model.ITask;
import com.example.model.ITaskManager;
import com.sun.net.httpserver.HttpExchange;

public class TaskGetByIdHandler extends AbstractHandler {

    public TaskGetByIdHandler(ITaskManager manager) {
        super(manager);
    }

    @Override
    public String getRequestUrl() {
        return "/api/tasks/task";
    }

    @Override
    public HttpTaskResponse handle(HttpExchange exchange) {
        String taskId = exchange.getRequestURI().getQuery().split("=")[1];
        System.out.println("Запрос на получение задачи: id=" + taskId);
        long id = Long.parseLong(taskId);
        ITask task = manager.getTask(id);
        if (task != null) {
            if (id > -1) {
                return HttpTaskResponse.ok(exchange, "Задача. Id=" + id);
            } else {
                return HttpTaskResponse.notFound(exchange);
            }
        } else {
            return HttpTaskResponse.serverError(exchange);
        }
    }

}
