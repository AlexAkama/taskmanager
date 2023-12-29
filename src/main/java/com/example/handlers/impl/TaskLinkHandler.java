package com.example.handlers.impl;

import com.example.handlers.HttpAbstractHandler;
import com.example.http.HttpLinkRequest;
import com.example.http.HttpMethod;
import com.example.http.HttpTaskResponse;
import com.example.model.ITaskManager;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

public class TaskLinkHandler extends HttpAbstractHandler {

    public TaskLinkHandler(ITaskManager manager) {
        super(manager);
    }

    @Override
    public String getRequestPath() {
        return "/api/tasks/link";
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }

    @Override
    public HttpTaskResponse handle(HttpExchange exchange) {
        System.out.println("Запрос на связывание задач");
        String requestBody = getRequestBody(exchange);
        HttpLinkRequest request = new Gson().fromJson(requestBody, HttpLinkRequest.class);
        if (manager.linkTask(request)) {
            return HttpTaskResponse.ok(exchange, "Задачи успешно связаны.");
        } else {
            return HttpTaskResponse.bad(exchange, "Не удалось связать задачи");
        }
    }

}
