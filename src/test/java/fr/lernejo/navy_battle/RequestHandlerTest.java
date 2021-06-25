package fr.lernejo.navy_battle;

import com.sun.net.httpserver.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.net.ssl.SSLSession;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.*;

class RequestHandlerTest {

    @Test
    void GoodStartrequest(){
        MyServer s = null;
        try {
            s = MyServerTest.StartTestServer("9879");
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertEquals(0,1,"Number of exception on server");
        }
        HttpExchange exchange = new HttpsExchange() {
            @Override
            public SSLSession getSSLSession() {
                return null;
            }

            @Override
            public Headers getRequestHeaders() {
                return null;
            }

            @Override
            public Headers getResponseHeaders() {
                return null;
            }

            @Override
            public URI getRequestURI() {
                return null;
            }

            @Override
            public String getRequestMethod() {
                return null;
            }

            @Override
            public HttpContext getHttpContext() {
                return null;
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getRequestBody() {
                return new ByteArrayInputStream(Charset.forName("UTF-8").encode("{\"id\": \"2aca7611-0ae4-49f3-bf63-75bef4769028\",\"url\": \"http://localhost:9876\",\"message\": \"May the best code win\"}").array());
            }

            @Override
            public OutputStream getResponseBody() {
                return new ByteArrayOutputStream(512);
            }

            @Override
            public void sendResponseHeaders(int i, long l) throws IOException {

            }

            @Override
            public InetSocketAddress getRemoteAddress() {
                return null;
            }

            @Override
            public int getResponseCode() {
                return 0;
            }

            @Override
            public InetSocketAddress getLocalAddress() {
                return null;
            }

            @Override
            public String getProtocol() {
                return null;
            }

            @Override
            public Object getAttribute(String s) {
                return null;
            }

            @Override
            public void setAttribute(String s, Object o) {

            }

            @Override
            public void setStreams(InputStream inputStream, OutputStream outputStream) {

            }

            @Override
            public HttpPrincipal getPrincipal() {
                return null;
            }
        };
        RequestHandler handler = new RequestHandler(s);
        try {
            handler.StartHandler(exchange);
        } catch (IOException | InterruptedException e) {
            Assertions.assertEquals(0,1,"Number of exception on request");
        }
        MyServerTest.DestroyTestServer(s);
    }

    @Test
    void BadStartrequest(){
        MyServer s = null;
        try {
            s = MyServerTest.StartTestServer("9880");
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertEquals(0,1,"Number of exception on server");
        }
        HttpExchange exchange = new HttpsExchange() {
            @Override
            public SSLSession getSSLSession() {
                return null;
            }

            @Override
            public Headers getRequestHeaders() {
                return null;
            }

            @Override
            public Headers getResponseHeaders() {
                return null;
            }

            @Override
            public URI getRequestURI() {
                return null;
            }

            @Override
            public String getRequestMethod() {
                return null;
            }

            @Override
            public HttpContext getHttpContext() {
                return null;
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getRequestBody() {
                return new ByteArrayInputStream(Charset.forName("UTF-8").encode("{\"url\": \"http://localhost:9876\",\"message\": \"May the best code win\"}").array());
            }

            @Override
            public OutputStream getResponseBody() {
                return new ByteArrayOutputStream(512);
            }

            @Override
            public void sendResponseHeaders(int i, long l) throws IOException {

            }

            @Override
            public InetSocketAddress getRemoteAddress() {
                return null;
            }

            @Override
            public int getResponseCode() {
                return 0;
            }

            @Override
            public InetSocketAddress getLocalAddress() {
                return null;
            }

            @Override
            public String getProtocol() {
                return null;
            }

            @Override
            public Object getAttribute(String s) {
                return null;
            }

            @Override
            public void setAttribute(String s, Object o) {

            }

            @Override
            public void setStreams(InputStream inputStream, OutputStream outputStream) {

            }

            @Override
            public HttpPrincipal getPrincipal() {
                return null;
            }
        };
        RequestHandler handler = new RequestHandler(s);
        try {
            handler.StartHandler(exchange);
        } catch (IOException | InterruptedException e) {
            Assertions.assertEquals(0,1,"Number of exception on request");
        }
        MyServerTest.DestroyTestServer(s);
    }

}
