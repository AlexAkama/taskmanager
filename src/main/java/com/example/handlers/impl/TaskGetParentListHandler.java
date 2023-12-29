package com.example.handlers.impl;

import com.example.handlers.HttpAbstractHandler;
import com.example.http.HttpMethod;
import com.example.http.HttpTaskResponse;
import com.example.model.ITask;
import com.example.model.ITaskManager;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

import java.util.List;

public class TaskGetParentListHandler extends HttpAbstractHandler {

    public TaskGetParentListHandler(ITaskManager manager) {
        super(manager);
    }

    @Override
    public String getRequestPath() {
        return "/api/tasks";
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }

    @Override
    public HttpTaskResponse handle(HttpExchange exchange) {
        System.out.println("Запрос на получение главных задач");
        List<ITask> list = manager.getParentTasks();
        String json = new Gson().toJson(list);
        return HttpTaskResponse.ok(exchange, json);
    }

}
