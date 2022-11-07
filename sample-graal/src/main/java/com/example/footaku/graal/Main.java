package com.example.footaku.graal;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.commons.cli.*;

import java.sql.SQLException;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = LoggerProvider.of(Main.class).instance();
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
            logger.info("a has been set: " + command.hasOption("a"));
            logger.info("b has been set: " + command.hasOption("b"));
            return true;
        } catch (ParseException e) {
            new HelpFormatter().printHelp("sample-graal -a <param> [-b]", options);
            return false;
        }
    }

    private static void execute() {
        var content = new Sender(new JsonMapper()).get();

        var user = "user";
        var pass = "pass";
        try (var con = new DBConnection(user, pass).get()) {
            con.setAutoCommit(false);

            var inserted = new Writer(con).write(content);
            logger.info("Row inserted: " + inserted);

            var contents = new Reader(con).read(content);
            logger.info(contents.toString());

            var deleted = new Writer(con).delete(content);
            logger.info("Row deleted: " + deleted);

            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
