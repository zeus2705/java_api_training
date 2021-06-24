package fr.lernejo.navy_battle;

import org.apache.commons.validator.routines.UrlValidator;

import java.io.*;
import java.net.*;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Launcher {

    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        MyServer server = null;
        try {
            server = new MyServer(args[0]);
            if (args.length > 1) {
                ClientHttp client = new ClientHttp(server, args[1]);
                client.ConnectToServer();
            }
        } catch (IOException | URISyntaxException e) {
            throw e;
        }
    }
}
