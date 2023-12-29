package com.example.handlers.config;

import com.example.handlers.HttpRequestHandler;
import com.example.handlers.impl.TaskAddHandler;
import com.example.handlers.impl.TaskDeleteHandler;
import com.example.handlers.impl.TaskGetAllListHandler;
import com.example.handlers.impl.TaskGetByIdHandler;
import com.example.handlers.impl.TaskGetParentListHandler;
import com.example.handlers.impl.TaskLinkHandler;
import com.example.handlers.impl.TaskUpdateHandler;
import com.example.handlers.util.PathUtils;
import com.example.service.TaskManagerService;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class HttpHandlerConfig {

    private HttpHandlerConfig() {
    }

    private static final TaskManagerService SERVICE = new TaskManagerService();
    private static final List<HttpRequestHandler> REQUEST_HANDLER_LIST = List.of(
            new TaskAddHandler(SERVICE),
            new TaskGetParentListHandler(SERVICE),
            new TaskGetAllListHandler(SERVICE),
            new TaskGetByIdHandler(SERVICE),
            new TaskUpdateHandler(SERVICE),
            new TaskDeleteHandler(SERVICE),
            new TaskLinkHandler(SERVICE)
    );

    private static final Map<String, HttpRequestHandler> STRING_REQUEST_HANDLER_MAP =
            REQUEST_HANDLER_LIST.stream()
                    .collect(Collectors.toMap(
                            h -> PathUtils.getRequestPathWithMethod(h.getHttpMethod().name(), h.getRequestPath()),
                            Function.identity()));

    public static Map<String, HttpRequestHandler> getHandlerMap() {
        return STRING_REQUEST_HANDLER_MAP;
    }

}
