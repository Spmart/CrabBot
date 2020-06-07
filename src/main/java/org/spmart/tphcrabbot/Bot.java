package org.spmart.tphcrabbot;

import org.spmart.tphcrabbot.util.BotIdentifier;
import org.spmart.tphcrabbot.util.DNSRequest;
import org.spmart.tphcrabbot.util.DNSResponse;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    private static final String NS1_REG_RU = "ns1.reg.ru";
    private static final String NS2_REG_RU = "ns2.reg.ru";
    private static final String NS1_HOSTING_REG_RU = "ns1.hosting.reg.ru";
    private static final String NS2_HOSTING_REG_RU = "ns2.hosting.reg.ru";
    private static final String NS5_HOSTING_REG_RU = "ns5.hosting.reg.ru";
    private static final String NS6_HOSTING_REG_RU = "ns6.hosting.reg.ru";
    private static final String NS7_HOSTING_REG_RU = "ns7.hosting.reg.ru";
    private static final String NS8_HOSTING_REG_RU = "ns8.hosting.reg.ru";

    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            Long chatID = update.getMessage().getChatId();
            String inMessageText = update.getMessage().getText();

            ArrayList<DNSResponse> dnsResponses = sendRequests(inMessageText);

            String outMessageText = buildMessage(dnsResponses);

            SendMessage message = new SendMessage()
                    .setChatId(chatID)
                    .setText(outMessageText);
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public String getBotUsername() {
        return BotIdentifier.getUsername();
    }

    public String getBotToken() {
        return BotIdentifier.getApiToken();
    }

    private ArrayList<DNSResponse> sendRequests(String domainName) {

        ArrayList<String> dnsServers = new ArrayList<>(List.of(NS1_REG_RU, NS2_REG_RU, NS1_HOSTING_REG_RU,
                NS2_HOSTING_REG_RU, NS5_HOSTING_REG_RU, NS6_HOSTING_REG_RU, NS7_HOSTING_REG_RU, NS8_HOSTING_REG_RU));
        ArrayList<DNSResponse> dnsResponses = new ArrayList<>();

        DNSRequest requestGoogle = new DNSRequest(domainName); // give domain
        DNSResponse responseFromGoogle = requestGoogle.send();  // get back IP
        dnsResponses.add(responseFromGoogle);

        for (String server : dnsServers) {
            DNSRequest request = new DNSRequest(server, domainName);
            dnsResponses.add(request.send());
        }
        return dnsResponses;
    }

    private String buildMessage(ArrayList<DNSResponse> responses) {

        String message = "";
        message += responses.get(0).getDomain() + " \n";

        for (DNSResponse response : responses) {
            if (response.getIp().isEmpty()) {
                message += response.getDnsServer() + ": no data\n";
            } else {
                message += response.getDnsServer() + ": " + response.getIp() + ": " + response.getPtr() + "\n";
            }
        }
        return message;
    }

}
