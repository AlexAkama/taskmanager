package com.example.handlers.impl;

import com.example.handlers.AbstractHandler;
import com.example.http.HttpLinkRequest;
import com.example.http.HttpTaskResponse;
import com.example.model.ITaskManager;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

public class TaskLinkHandler extends AbstractHandler {

    public TaskLinkHandler(ITaskManager manager) {
        super(manager);
    }

    @Override
    public String getRequestUrl() {
        return "/api/tasks/link";
    }

    @Override
    public HttpTaskResponse handle(HttpExchange exchange) {
        System.out.println("Запрос на связывание задач");
        String requestBody = getRequestString(exchange);
        HttpLinkRequest request = new Gson().fromJson(requestBody, HttpLinkRequest.class);
        if (manager.linkTask(request)) {
            return HttpTaskResponse.ok(exchange, "Задачи успешно связаны.");
        } else {
            return HttpTaskResponse.bad(exchange, "Не удалось связать задачи");
        }
    }

}
