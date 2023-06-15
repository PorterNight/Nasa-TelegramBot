package tgbot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import tgbot.commands.BotCommandAbstract;
import tgbot.commands.HelpCommand;
import tgbot.commands.ReplyKeyboardMenu;
import tgbot.commands.MarsCommand;
import tgbot.commands.PodCommand;
import tgbot.commands.StartCommand;
import tgbot.config.BotConfig;
import tgbot.service.ClientService;

@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    private final List<BotCommandAbstract> commands;

    public TelegramBot(ClientService clientService) throws TelegramApiException {
        commands = new ArrayList<>();
        commands.add(new StartCommand());
        commands.add(new PodCommand(clientService));
        commands.add(new MarsCommand(clientService));
        commands.add(new HelpCommand(commands));

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
                    sendMessage(command.handle(update));
                    break;
                }
            }
        }
    }

    private void sendMessage(SendMessage msg) {
        try {
            msg.setReplyMarkup((new ReplyKeyboardMenu()).getKeyboardMarkup());
            execute(msg);
        } catch (TelegramApiException e) {
            log.warn("error while sending message to telegram-bot");
            throw new RuntimeException(e);
        }
    }
}
