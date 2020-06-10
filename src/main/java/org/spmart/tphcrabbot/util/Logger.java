package org.spmart.tphcrabbot.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public enum Logger {
    INSTANCE;

    private static final Path PATH_TO_LOG = Paths.get("crabbot.log");

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

    private String getTimeStamp() {
        return ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
    }
}
