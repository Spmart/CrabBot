package org.spmart.tphcrabbot.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Logger singleton.
 * Log4j is overkill for that little project.
 */
public enum Logger {
    INSTANCE;

    // If project built in JAR, log file will be created in same workin dir, with JAR.
    private static final Path PATH_TO_LOG = Paths.get("crabbot.log");

    /**
     * Write a string into a log file.
     * @param message Message for logging.
     */
    public void write(String message) {
        File logFile = new File(PATH_TO_LOG.toString());
        try {
            logFile.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        message = String.format("%s %s", getTimeStamp(), message);

        try {
            Files.write(PATH_TO_LOG, message.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Get date and time in nice format like:
     * Wed, 10 Jun 2020 09:26:04 +0300 CRABBOT IS STARTED!
     * @return Current local date/time.
     */
    private String getTimeStamp() {
        return ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
    }
}
