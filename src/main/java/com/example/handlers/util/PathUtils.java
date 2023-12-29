package com.example.handlers.util;

import com.sun.net.httpserver.HttpExchange;

public final class PathUtils {

    private PathUtils() {
    }

    public static String getRequestPathWithMethod(HttpExchange exchange) {
        String path = exchange.getRequestURI().getPath();
        String method = exchange.getRequestMethod();
        return getRequestPathWithMethod(method, path);
    }

    public static String getRequestPathWithMethod(String method, String path) {
        return method + ": " + path;
    }


}
