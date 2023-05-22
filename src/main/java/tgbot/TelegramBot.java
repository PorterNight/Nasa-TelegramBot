package tgbot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import tgbot.commands.BotCommandAbstract;
import tgbot.commands.HelpCommand;
import tgbot.commands.MarsCommand;
import tgbot.commands.PodCommand;
import tgbot.commands.StartCommand;
import tgbot.config.BotConfig;
import static tgbot.commands.BotCommands.*;

@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    private final List<BotCommandAbstract> commands;

    public TelegramBot() throws TelegramApiException {
        commands = new ArrayList<>();
        commands.add(new StartCommand(START.getCommand(), START.getDescription()));
        commands.add(new PodCommand(POD.getCommand(), POD.getDescription()));
        commands.add(new MarsCommand(MARS.getCommand(), MARS.getDescription()));
        commands.add(new HelpCommand(HELP.getCommand(), HELP.getDescription(), commands));

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(this);
    }

    @Override
    public String getBotUsername() {
        return BotConfig.TG_BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BotConfig.TG_BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            String cmd = update.getMessage().getText();

            // process user commands
            for (BotCommandAbstract command : commands) {

                if (cmd.equalsIgnoreCase(command.getCommand())) {
                    sendMessageWithMenu(command.handle(update));
                    break;
                }
            }
        }
    }

    private void sendMessage(SendMessage msg) {
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            log.warn("error while sending message to telegram-bot");
            throw new RuntimeException(e);
        }
    }

    public void sendMessageWithMenu(SendMessage msg) {

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row;

        // first row of 2 buttons
        row = new KeyboardRow();
        row.add(POD.getCommand());
        row.add(MARS.getCommand());
        keyboard.add(row);

        // second row of 1 button
        row = new KeyboardRow();
        row.add(HELP.getCommand());
        keyboard.add(row);

        keyboardMarkup.setKeyboard(keyboard);
        keyboardMarkup.setResizeKeyboard(true);
        msg.setReplyMarkup(keyboardMarkup);

        sendMessage(msg);
    }
}
