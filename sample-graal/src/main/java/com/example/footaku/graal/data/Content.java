package com.example.footaku.graal.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Content {
    private final int code;
    private final String description;

    public Content(
            @JsonProperty("code") int code,
            @JsonProperty("description") String description
    ) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Content{" +
                "code=" + code +
                ", description='" + description + '\'' +
                '}';
    }
}
