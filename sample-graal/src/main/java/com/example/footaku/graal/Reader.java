package com.example.footaku.graal;

import com.example.footaku.graal.data.Content;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Reader {
    private final JsonMapper jsonMapper;

    public Reader(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    public Content read() {
        Content content;
        try {
            content = jsonMapper.readValue(http(), Content.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return content;
    }

    private String http() {
        URI uri;
        try {
            uri = new URI("https://httpstat.us/200");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        HttpRequest request = HttpRequest.newBuilder(uri)
                .GET()
                .setHeader("Accept", "application/json")
                .timeout(Duration.ofSeconds(3))
                .build();

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(3))
                .build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response.body();
    }
}
