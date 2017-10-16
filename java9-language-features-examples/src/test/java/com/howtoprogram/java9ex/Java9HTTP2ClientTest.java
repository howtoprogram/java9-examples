package com.howtoprogram.java9ex;


import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;
import org.junit.jupiter.api.Test;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Java9HTTP2ClientTest {

    public void dosth() {
        HttpClient defaultClient = HttpClient.newHttpClient();

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .authenticator(new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("username", "password".toCharArray());
                    }
                })
                .build();
    }

    @Test
    public void synchronousGetTest() throws Exception {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://publicobject.com/helloworld.txt"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandler.asString());
        assertEquals(200, response.statusCode());

    }

    @Test
    public void getWithQueryParametersTest() throws Exception {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://publicobject.com/helloworld.txt"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandler.asString());
        assertEquals(200, response.statusCode());

    }

    @Test
    public void asynchronousGetTest() throws Exception {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://publicobject.com/helloworld.txt"))
                .GET()
                .build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(request,
                HttpResponse.BodyHandler.asString());

        HttpResponse<String> actualResponse = response.get(1000, TimeUnit.MINUTES);
        assertEquals(200, actualResponse.statusCode());

    }
}
