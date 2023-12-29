package com.example.handlers;

import com.example.http.HttpMethod;
import com.example.http.HttpTaskResponse;
import com.sun.net.httpserver.HttpExchange;

public interface HttpRequestHandler {

    String getRequestPath();

    HttpMethod getHttpMethod();

    HttpTaskResponse handle(HttpExchange exchange);

}
