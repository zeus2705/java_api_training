package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class ClientHttpTest {

    //ClientHttp() and  public boolean ConnectToServer() fully tested in Launchertest

    @Test
    void AcceptClient() throws IOException, URISyntaxException, InterruptedException {
        ClientHttp ci = new ClientHttp(new MyServer("1237"), "http://localhost:1237");
        ci.ConnectToServer();
    }
}
