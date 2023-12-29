package com.example.handlers;

import com.example.model.ITaskManager;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public abstract class HttpAbstractHandler implements HttpRequestHandler {

    protected final ITaskManager manager;

    protected HttpAbstractHandler(ITaskManager manager) {
        this.manager = manager;
    }

    protected String getRequestBody(HttpExchange exchange) {
        byte[] requestBodyBytes;
        try {
            requestBodyBytes = exchange.getRequestBody().readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new String(requestBodyBytes);
    }

}
