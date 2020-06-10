package org.spmart.tphcrabbot;

import org.spmart.tphcrabbot.util.Logger;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;


public class Main {
    public static void main(String[] args) {

        // Use TOR as proxy on JVM level
        //System.getProperties().put("proxySet", true);
        //System.getProperties().put("socksProxyHost", "127.0.0.1");
        //System.getProperties().put("socksProxyPort", "9150");

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        Logger logger = Logger.INSTANCE;

        logger.write("CRABBOT IS STARTED!\n");
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            logger.write("Can's start! Check your network connection.\n");
            e.printStackTrace();
        }
    }
}
