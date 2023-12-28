package com.example.handlers;

import com.example.http.HttpTaskResponse;
import com.sun.net.httpserver.HttpExchange;

public interface RequestHandler {

    String getRequestUrl();

    HttpTaskResponse handle(HttpExchange exchange);

}
