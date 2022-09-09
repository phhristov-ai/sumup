package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import entity.Task;
import entity.TaskOutput;
import service.ProcessJson;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HttpHandlerJson implements HttpHandler {

    @Override
    public void handle(final HttpExchange httpExchange) throws IOException {
        final String jsonData = new String(httpExchange.getRequestBody().readAllBytes());
        final Optional<ArrayList<Task>> tasks = ProcessJson.convertJsonToObject(jsonData);
        final OutputStream responseBody = httpExchange.getResponseBody();
        String response;
        if (tasks.isPresent()) {
            final List<TaskOutput> taskOutputs = tasks.get().stream()
                    .map(task -> new TaskOutput(task))
                    .collect(Collectors.toList());

            response = ProcessJson.convertObjectToJson((ArrayList<TaskOutput>) taskOutputs);

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
