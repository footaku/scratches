package com.example.footaku.graal;

import com.fasterxml.jackson.databind.json.JsonMapper;

public class Main {
    public static void main(String[] args) {
        execute();
    }

    private static void execute() {
        var reader = new Reader(new JsonMapper());
        System.out.println(reader.read());
    }
}
