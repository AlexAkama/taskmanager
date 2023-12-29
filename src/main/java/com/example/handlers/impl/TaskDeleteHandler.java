package com.example.handlers.impl;

import com.example.handlers.HttpAbstractHandler;
import com.example.http.HttpMethod;
import com.example.http.HttpTaskResponse;
import com.example.model.ITaskManager;
import com.sun.net.httpserver.HttpExchange;

public class TaskDeleteHandler extends HttpAbstractHandler {

    public TaskDeleteHandler(ITaskManager manager) {
        super(manager);
    }

    @Override
    public String getRequestPath() {
        return "/api/tasks/delete";
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.DELETE;
    }

    @Override
    public HttpTaskResponse handle(HttpExchange exchange) {
        String taskId = exchange.getRequestURI().getQuery().split("=")[1];
        System.out.println("Запрос на удаление задачи: id=" + taskId);
        Long id = Long.parseLong(taskId);
        id = manager.deleteTask(id);
        if (id != null) {
            if (id > -1) {
                return HttpTaskResponse.ok(exchange, "Задача удалена успешно. Id=" + id);
            } else {
                return HttpTaskResponse.notFound(exchange);
            }
        } else {
            return HttpTaskResponse.serverError(exchange);
        }
    }

}
