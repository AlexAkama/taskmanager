package com.example.http;

public enum HttpMethod {
    GET,
    POST,
    PATCH,
    DELETE;

    public static HttpMethod get(String name) {
        name = name.toUpperCase();
        return switch (name) {
            case "GET" -> GET;
            case "POST" -> POST;
            case "PATCH" -> PATCH;
            case "DELETE" -> DELETE;
            default -> throw new IllegalArgumentException(
                    HttpMethod.class.getName() + " элемент не найден. Запрошенное имя:" + name);
        };
    }

}
