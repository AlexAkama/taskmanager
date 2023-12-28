package com.example.http;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class HttpTaskResponse {

    private final HttpExchange exchange;

    private final int statusCode;

    private final String body;

    private HttpTaskResponse(HttpExchange exchange, int statusCode, String body) {
        if (exchange == null || body == null) throw new RuntimeException("Поля не могут быть null");
        this.exchange = exchange;
        this.body = body;
        this.statusCode = statusCode;
    }

    public void sendResponse() {

        try {
            exchange.sendResponseHeaders(statusCode, body.getBytes().length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(body.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static HttpTaskResponse ok(HttpExchange exchange, String body) {
        return new HttpTaskResponse(exchange, 200, body);
    }

    public static HttpTaskResponse bad(HttpExchange exchange, String body) {
        return new HttpTaskResponse(exchange, 400, body);
    }

    public static HttpTaskResponse notFound(HttpExchange exchange) {
        return new HttpTaskResponse(exchange, 404, "Данные не найдены.");
    }

    public static HttpTaskResponse serverError(HttpExchange exchange) {
        return new HttpTaskResponse(exchange, 500, "Все плохо, приходите завтра...");
    }

    public static HttpTaskResponse notAllowed(HttpExchange exchange) {
        return new HttpTaskResponse(exchange, 405, "Упс, метод не реализован!");
    }


}
