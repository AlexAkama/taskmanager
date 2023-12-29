package com.example;

import com.example.handlers.HttpRequestHandler;
import com.example.handlers.config.HttpHandlerConfig;
import com.example.handlers.util.PathUtils;
import com.example.http.HttpTaskResponse;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.Optional;

public class TaskManagerServer {

    private static final int PORT = 8080;
    private static final Map<String, HttpRequestHandler> STRING_REQUEST_HANDLER_MAP = HttpHandlerConfig.getHandlerMap();

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/api/tasks", new TaskHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("TaskManagerServer is running on port: " + PORT);
    }

    static class TaskHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) {
            String key = PathUtils.getRequestPathWithMethod(exchange);
            Optional.ofNullable(STRING_REQUEST_HANDLER_MAP.get(key))
                    .map(httpRequestHandler -> httpRequestHandler.handle(exchange))
                    .orElseGet(() -> HttpTaskResponse.notAllowed(exchange))
                    .sendResponse();
        }

    }

}
