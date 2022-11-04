package com.example.footaku.graal;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.commons.cli.*;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        if (!isValidArgs(args)) {
            System.exit(1);
        };
        execute();
        System.exit(0);
    }

    private static boolean isValidArgs(String[] args) {
        var options = new Options();
        options.addOption(
                Option.builder("a")
                        .longOpt("aaa")
                        .hasArg(true)
                        .required(true)
                        .desc("Set aaa")
                        .build()
        );
        options.addOption(
                Option.builder("b")
                        .longOpt("bbb")
                        .hasArg(false)
                        .required(false)
                        .desc("Set BbB")
                        .build()
        );

        var parser = new DefaultParser();
        try {
            var command = parser.parse(options, args);
            System.out.printf("a has been set: %s\n", command.hasOption("a"));
            System.out.printf("b has been set: %s\n", command.hasOption("b"));
            return true;
        } catch (ParseException e) {
            new HelpFormatter().printHelp("sample-graal -a <param> [-b]", options);
            return false;
        }
    }

    private static void execute() {
        var content = new Client(new JsonMapper()).get();

        var user = "user";
        var pass = "pass";
        try (var con = new DBConnection(user, pass).get()) {
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
