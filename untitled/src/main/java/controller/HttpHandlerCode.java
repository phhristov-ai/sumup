package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import entity.Task;
import service.ProcessJson;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class HttpHandlerCode implements HttpHandler {

    @Override
    public void handle(final HttpExchange httpExchange) throws IOException {
        final String jsonData = new String(httpExchange.getRequestBody().readAllBytes());
        final Optional<ArrayList<Task>> tasks = ProcessJson.convertJsonToObject(jsonData);
        final OutputStream responseBody = httpExchange.getResponseBody();
        String response;
        if (tasks.isPresent()) {
            response = tasks.get()
                    .stream()
                    .map(task -> task.getCommand())
                    .collect(Collectors.joining("\n", "#!/usr/bin/env bash \n", ""));

            httpExchange.sendResponseHeaders(200, response.length());
        } else {
            response = "Error. There is a cycle";
            httpExchange.sendResponseHeaders(400, response.length());
        }



        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.flush();
        outputStream.close();

    }

}
