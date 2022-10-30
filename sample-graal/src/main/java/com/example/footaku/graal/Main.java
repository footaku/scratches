package com.example.footaku.graal;

import com.fasterxml.jackson.databind.json.JsonMapper;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        execute();
    }

    private static void execute() {
        var content = new Client(new JsonMapper()).get();

        var user = "user";
        var pass = "pass";
        try(var con = new DBConnection(user, pass).get()) {
            con.setAutoCommit(false);

            var inserted = new Writer(con).write(content);
            System.out.printf("Row inserted: %s%n", inserted);

            var contents = new Reader(con).read(content);
            System.out.println(contents);

            var deleted = new Writer(con).delete(content);
            System.out.printf("Row deleted: %s%n", deleted);

            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
