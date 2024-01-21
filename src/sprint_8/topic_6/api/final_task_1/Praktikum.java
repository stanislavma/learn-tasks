package sprint_8.topic_6.api.final_task_1;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Praktikum {
    enum Endpoint {GET_POSTS, GET_COMMENTS, POST_COMMENT, UNKNOWN}

    private static final int PORT = 8080;
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    public static void main(String[] args) throws IOException {
        HttpServer httpServer = HttpServer.create();

        httpServer.bind(new InetSocketAddress(PORT), 0);
        httpServer.createContext("/posts", new PostsHandler());
        httpServer.createContext("/writeResponse", new WriteResponseHandler());
        httpServer.start();

        System.out.println("HTTP-сервер запущен на " + PORT + " порту!");
    }

    static class PostsHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath();
            String method = exchange.getRequestMethod();

            Endpoint endpoint = getEndpoint(path, method);

            switch (endpoint) {
                case GET_POSTS: {
                    writeResponse(exchange, "Получен запрос на получение постов", 200);
                    break;
                }
                case GET_COMMENTS: {
                    writeResponse(exchange, "Получен запрос на получение комментариев", 200);
                    break;
                }
                case POST_COMMENT: {
                    writeResponse(exchange, "Получен запрос на добавление комментария", 200);
                    break;
                }
                default:
                    writeResponse(exchange, "Такого эндпоинта не существует", 404);
            }
        }

        private Endpoint getEndpoint(String requestPath, String requestMethod) {
            switch (requestMethod) {

                case "GET":
                    long pathParts = requestPath.split("/").length;

                    if (pathParts == 2) {
                        return Endpoint.GET_POSTS;
                    } else if (pathParts == 4) {
                        return Endpoint.GET_COMMENTS;
                    } else {
                        return Endpoint.UNKNOWN;
                    }
                case "POST":
                    return Endpoint.POST_COMMENT;
                default:
                    return Endpoint.UNKNOWN;
            }
        }

        private void writeResponse(HttpExchange exchange,
                                   String responseString,
                                   int responseCode) throws IOException {
            /* Реализуйте отправку ответа, который содержит responseString в качестве тела ответа
            и responseCode в качестве кода ответа.
            Учтите, что если responseString — пустая строка, то её не нужно передавать в ответе. */

            exchange.sendResponseHeaders(responseCode, 0);
            try (OutputStream os = exchange.getResponseBody()) {
                if (responseString.isEmpty()) {
                    os.write(0);
                    return;
                }
                os.write(responseString.getBytes());
            }
        }
    }

    static class WriteResponseHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {

            httpExchange.sendResponseHeaders(200, 0);
            String response = "";

            InputStream inputStream = httpExchange.getRequestBody();
            response = new String(inputStream.readAllBytes(), DEFAULT_CHARSET);

            try (OutputStream os = httpExchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }

}