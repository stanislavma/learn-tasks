package sprint_8.topic_6.api.final_task_2;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Post {
    private int id;
    private String text;
    private List<Comment> commentaries = new ArrayList<>();

    private Post() {
    }

    public Post(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public void addComment(Comment comment) {
        commentaries.add(comment);
    }

    public List<Comment> getCommentaries() {
        return commentaries;
    }

    public int getId() {
        return id;
    }
}

class Comment {
    private String user;
    private String text;

    private Comment() {
    }

    public Comment(String user, String text) {
        this.user = user;
        this.text = text;
    }

    public String getUser() {
        return user;
    }

    public String getText() {
        return text;
    }
}

public class Praktikum {
    private static final int PORT = 8080;
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private static Gson gson = new Gson();
    private static List<Post> posts = new ArrayList<>();

    static {
        Post post1 = new Post(1, "Это первый пост, который я здесь написал.");
        post1.addComment(new Comment("Пётр Первый", "Я успел откомментировать первым!"));
        posts.add(post1);

        Post post2 = new Post(22, "Это будет второй пост. Тоже короткий.");
        posts.add(post2);

        Post post3 = new Post(333, "Это пока последний пост.");
        posts.add(post3);
    }


    public static void main(String[] args) throws IOException {
        HttpServer httpServer = HttpServer.create();

        httpServer.bind(new InetSocketAddress(PORT), 0);
        httpServer.createContext("/posts", new PostsHandler());
        httpServer.start(); // запускаем сервер

        System.out.println("HTTP-сервер запущен на " + PORT + " порту!");
    }

    static class PostsHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            Endpoint endpoint = getEndpoint(exchange.getRequestURI().getPath(), exchange.getRequestMethod());

            switch (endpoint) {
                case GET_POSTS: {
                    handleGetPosts(exchange);
                    break;
                }
                case GET_COMMENTS: {
                    handleGetComments(exchange);
                    break;
                }
                case POST_COMMENT: {
                    handlePostComments(exchange);
                    break;
                }
                default:
                    writeResponse(exchange, "Такого эндпоинта не существует", 404);
            }
        }

        private Endpoint getEndpoint(String requestPath, String requestMethod) {
            String[] pathParts = requestPath.split("/");

            if (pathParts.length == 2 && pathParts[1].equals("posts")) {
                return Endpoint.GET_POSTS;
            }
            if (pathParts.length == 4 && pathParts[1].equals("posts") && pathParts[3].equals("comments")) {
                if (requestMethod.equals("GET")) {
                    return Endpoint.GET_COMMENTS;
                }
                if (requestMethod.equals("POST")) {
                    return Endpoint.POST_COMMENT;
                }
            }
            return Endpoint.UNKNOWN;
        }

        private void handleGetPosts(HttpExchange exchange) throws IOException {
            // верните JSON, представляющий список постов. Код ответа должен быть 200.

            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, 0);

            try (OutputStream os = exchange.getResponseBody()) {
                String postSerialized = gson.toJson(posts);
                os.write(postSerialized.getBytes());
            }
        }

        private void handleGetComments(HttpExchange exchange) throws IOException {
            Optional<Integer> postIdOpt = getPostId(exchange);

            /* Верните комментарии к указанному посту. Код ответа должен быть 200. 
            Если запрос был составлен неверно, верните сообщение об ошибке. */

            if (postIdOpt.isPresent()) {

                try (OutputStream os = exchange.getResponseBody()) {
                    List<Comment> postComments;
                    String result = "";

                    Optional<Post> foundPost = posts.stream()
                            .filter(post -> post.getId() == postIdOpt.get())
                            .findFirst();

                    if (foundPost.isPresent()) {
                        exchange.sendResponseHeaders(200, 0);
                        postComments = foundPost.get().getCommentaries();
                        result = gson.toJson(postComments);
                    } else {
                        exchange.sendResponseHeaders(404, 0);
                        result = "Пост с идентификатором " + postIdOpt.get() + " не найден";
                    }

                    os.write(result.getBytes());
                }
            } else {
                exchange.sendResponseHeaders(400, 0);
            }
        }

        private Optional<Integer> getPostId(HttpExchange exchange) {
            /* Реализуйте метод получения идентификатора поста.
            Если идентификатор не является числом, верните Optional.empty(). */

            String requestPath = exchange.getRequestURI().getPath();
            String idStr = requestPath.split("/")[2];

            try {
                int id = Integer.parseInt(idStr);
                return Optional.of(id);
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        }

        private void handlePostComments(HttpExchange exchange) throws IOException {
            writeResponse(exchange, "Этот эндпоинт пока не реализован", 200);
        }

        private void writeResponse(HttpExchange exchange,
                                   String responseString,
                                   int responseCode) throws IOException {
            if (responseString.isBlank()) {
                exchange.sendResponseHeaders(responseCode, 0);
            } else {
                byte[] bytes = responseString.getBytes(DEFAULT_CHARSET);
                exchange.sendResponseHeaders(responseCode, bytes.length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(bytes);
                }
            }
            exchange.close();
        }

        enum Endpoint {GET_POSTS, GET_COMMENTS, POST_COMMENT, UNKNOWN}
    }
}