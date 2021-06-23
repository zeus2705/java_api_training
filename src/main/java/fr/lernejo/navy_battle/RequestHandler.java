package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class RequestHandler {
    final MyServer server;
    final JSON_Check jsck;
    RequestHandler(MyServer s) throws IOException {
        server = s;
        jsck = new JSON_Check();
    }

    static String GetBodyRequest(HttpExchange exchange) throws IOException {
        InputStreamReader isr =  new InputStreamReader(exchange.getRequestBody(),"utf-8");
        BufferedReader br = new BufferedReader(isr);
        int b;
        StringBuilder buf = new StringBuilder(512);
        while ((b = br.read()) != -1) {
            buf.append((char) b);
        }
        br.close();
        isr.close();
        System.out.println(buf.toString());
        return buf.toString();
    }

    public String StartHandler(HttpExchange exchange) throws IOException {
        try {
            String body = GetBodyRequest(exchange);
            if (!jsck.ValidateStartRequest(body)) {new Exception();}
        } catch (Exception e) {
            server.generatcatHtml(exchange, 400);
            return "";
        }
        String bodyresponse = String.format("{\"id\": \"%s\",\"url\": \"%s\",\"message\": \"%s\"}", server.serverID, server.url, "Cat will prevail");
        System.out.println(bodyresponse);
        exchange.sendResponseHeaders(202, bodyresponse.length());
        try (
            OutputStream os = exchange.getResponseBody()) { // (1)
            os.write(bodyresponse.getBytes());
        }
        return bodyresponse;
    }
}
