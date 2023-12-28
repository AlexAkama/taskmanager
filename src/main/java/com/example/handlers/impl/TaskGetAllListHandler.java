package com.example.handlers.impl;

import com.example.handlers.AbstractHandler;
import com.example.http.HttpTaskResponse;
import com.example.model.ITask;
import com.example.model.ITaskManager;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

import java.util.List;

public class TaskGetAllListHandler extends AbstractHandler {

    public TaskGetAllListHandler(ITaskManager manager) {
        super(manager);
    }

    @Override
    public String getRequestUrl() {
        return "/api/tasks/all";
    }

    @Override
    public HttpTaskResponse handle(HttpExchange exchange) {
        System.out.println("Запрос на получение всех задач");
        List<ITask> list = manager.getAllTasks();
        String json = new Gson().toJson(list);
        return HttpTaskResponse.ok(exchange, json);
    }

}
