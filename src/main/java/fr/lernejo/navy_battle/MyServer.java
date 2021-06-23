package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MyServer {

    final int port;
    final String url;
    final HttpServer server;
    final String serverID;
    final RequestHandler handler;

     void generatcatHtml(HttpExchange exchange, int error) throws IOException {
        String body = String.format("<img class=\"fit-picture\" src=\"https://http.cat/%s\">", error);
        exchange.sendResponseHeaders(error, body.length());
        try (OutputStream os = exchange.getResponseBody()) { // (1)
            os.write(body.getBytes());
        }
    }

    final HttpHandler PingRespond = new HttpHandler() {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String body = String.format("OK");
            exchange.sendResponseHeaders(200, body.length());
            try (OutputStream os = exchange.getResponseBody()) { // (1)
                os.write(body.getBytes());
            }
        }
    };

    final HttpHandler StartRespond = new HttpHandler() {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (!exchange.getRequestMethod().equals("POST")) {
                generatcatHtml(exchange, 404);
                return;
            }
            handler.StartHandler(exchange);
        }
    };

    final HttpHandler DefaultRespond = new HttpHandler() {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            generatcatHtml(exchange, 404);
        }
    };

    MyServer(String port) throws IOException {
        this.port = Integer.parseInt(port);
        url = String.format("http://localhost:%s",port);
        Executor singlethread = Executors.newFixedThreadPool(1);
        server = HttpServer.create(new InetSocketAddress(this.port),50);
        server.setExecutor(singlethread);
        server.createContext("/ping",this.PingRespond);
        server.createContext("/api/game/start",this.StartRespond);
        server.createContext("/",this.DefaultRespond);
        this.serverID = UUID.randomUUID().toString();
        handler = new RequestHandler(this);
        server.start();
        System.out.println(String.format("Server started on port %s",port));
    }
}
