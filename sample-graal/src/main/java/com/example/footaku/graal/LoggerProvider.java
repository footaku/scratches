package com.example.footaku.graal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.*;

public class LoggerProvider {
    static {
        LogManager.getLogManager().reset();
    }

    private final Logger logger;

    private LoggerProvider(Class<?> clazz) {
        logger = Logger.getLogger(clazz.getName());
        logger.addHandler(new StandardOutConsoleHandler());
    }

    public static LoggerProvider of(Class<?> clazz) {
        return new LoggerProvider(clazz);
    }

    public Logger instance() {
        return logger;
    }

    private static class StandardOutConsoleHandler extends StreamHandler {
        public StandardOutConsoleHandler() {
            super(System.out, new JsonFormatter());
        }

        @Override
        public synchronized void publish(LogRecord record) {
            super.publish(record);
            flush();
        }

        @Override
        public synchronized void close() throws SecurityException {
            flush();
        }
    }

    private static class JsonFormatter extends Formatter {
        private final JsonMapper mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();

        @Override
        public String format(LogRecord record) {
            try {
                ZonedDateTime time = ZonedDateTime.ofInstant(
                        record.getInstant(), ZoneId.systemDefault());
                var property = new Property(
                        time.format(DateTimeFormatter.ISO_DATE_TIME),
                        record.getLevel().getName(),
                        record.getSourceClassName(),
                        record.getSourceMethodName(),
                        record.getMessage(),
                        record.getThrown()
                );
                return mapper.writeValueAsString(property) + "\n";
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        private record Property(
                String time,
                String level,
                String className,
                String methodName,
                String message,
                Throwable thrown
        ) {}
    }
}
