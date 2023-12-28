package com.example;

import com.example.handlers.impl.TaskDeleteHandler;
import com.example.handlers.impl.TaskGetAllListHandler;
import com.example.handlers.impl.TaskGetByIdHandler;
import com.example.handlers.impl.TaskLinkHandler;
import com.example.handlers.impl.TaskUpdateHand;
import com.example.http.HttpTaskResponse;
import com.example.handlers.RequestHandler;
import com.example.handlers.impl.TaskGetParentListHandler;
import com.example.handlers.impl.TaskAddHandler;
import com.example.service.TaskManagerService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TaskManagerServer {

    private static final int PORT = 8080;
    private static final TaskManagerService SERVICE = new TaskManagerService();

    private static final List<RequestHandler> REQUEST_HANDLER_LIST = List.of(
            new TaskAddHandler(SERVICE),
            new TaskGetParentListHandler(SERVICE),
            new TaskGetAllListHandler(SERVICE),
            new TaskGetByIdHandler(SERVICE),
            new TaskUpdateHand(SERVICE),
            new TaskDeleteHandler(SERVICE),
            new TaskLinkHandler(SERVICE)
    );
    private static final Map<String, RequestHandler> STRING_REQUEST_HANDLER_MAP =
            REQUEST_HANDLER_LIST.stream()
                    .collect(Collectors.toMap(RequestHandler::getRequestUrl, Function.identity()));

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/api/tasks", new TaskHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("TaskManagerServer is running on port: " + PORT);
    }

    static class TaskHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String requestPath = exchange.getRequestURI().getPath();

            Optional.ofNullable(STRING_REQUEST_HANDLER_MAP.get(requestPath))
                    .map(requestHandler -> requestHandler.handle(exchange))
                    .orElseGet(() -> HttpTaskResponse.notAllowed(exchange))
                    .sendResponse();
        }

    }

}
