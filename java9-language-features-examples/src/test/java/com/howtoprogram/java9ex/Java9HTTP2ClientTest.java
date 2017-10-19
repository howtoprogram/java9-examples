package com.howtoprogram.java9ex;


import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;
import jdk.incubator.http.HttpTimeoutException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.ProxySelector;
import java.net.URI;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Java9HTTP2ClientTest {


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
    public void basicAuthGetTest() throws Exception {

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .authenticator(new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("user", "passwd".toCharArray());
                    }
                })
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://httpbin.org/basic-auth/user/passwd"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandler.asString());
        assertEquals(200, response.statusCode());

    }

    @Test
    public void basicPostTest() throws Exception {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://httpbin.org/post")).POST(
                        HttpRequest.BodyProcessor.fromString("param1=foo,param2=bar"))
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandler.asString());
        assertEquals(200, response.statusCode());
        System.out.println(response.body());

    }

    @Test
    public void postByteArrayTest() throws Exception {

        HttpClient client = HttpClient.newHttpClient();
        byte[] body = "Java 9 HTTP 2 Client API Example".getBytes();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://httpbin.org/post")).POST(
                        HttpRequest.BodyProcessor.fromByteArray(body))
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandler.asString());
        assertEquals(200, response.statusCode());
        System.out.println(response.body());

    }


    @Test
    public void uploadFileTest() throws Exception {

        HttpClient client = HttpClient.newHttpClient();

        File file = new File("src/test/resources/LoremIpsum.txt");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://httpbin.org/post")).POST(
                        HttpRequest.BodyProcessor.fromFile(file.toPath()))
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandler.asString());
        assertEquals(200, response.statusCode());
        System.out.println(response.body());

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

    @Test
    public void setTimeoutTest() throws Exception {

        HttpClient client = HttpClient.newHttpClient();

        int delay = 10; //seconds

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://httpbin.org/delay/" + delay))
                .version(HttpClient.Version.HTTP_1_1)
                .GET()
                .timeout(Duration.ofSeconds(delay - 5))
                .build();

        assertThrows(HttpTimeoutException.class, () -> {

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandler.asString());

        });
    }
    @Test
    public void setProxyTest() throws Exception {

        HttpClient client = HttpClient.newBuilder()
                .proxy(ProxySelector.getDefault())
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://publicobject.com/helloworld.txt"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandler.asString());
        assertEquals(200, response.statusCode());
    }
}
