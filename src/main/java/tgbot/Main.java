package tgbot;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {

    private Main() {
    }

    public static void main(String[] args) throws TelegramApiException {
        new TelegramBot();
    }
}
