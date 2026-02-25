package com.automation.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Ova klasa služi za logovanje poruka tokom testiranja.
public class Logger {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final String LOG_PREFIX = "[TEST-LOG]";

    // Loguje običnu poruku
    public static void log(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.out.println(String.format("%s [%s] %s", LOG_PREFIX, timestamp, message));
    }

    // Loguje grešku
    public static void error(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.err.println(String.format("%s [ERROR] [%s] %s", LOG_PREFIX, timestamp, message));
    }
}
