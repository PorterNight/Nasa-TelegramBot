package tgbot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import tgbot.commands.BotCommandAbstract;
import tgbot.commands.HelpCommand;
import tgbot.commands.ReplyKeyboardMenu;
import tgbot.commands.MarsCommand;
import tgbot.commands.PictureOfTheDayCommand;
import tgbot.commands.StartCommand;
import tgbot.config.BotConfig;
import tgbot.service.ClientService;

@Slf4j
@Component
public class TelegramBot extends TelegramWebhookBot {

    private final List<BotCommandAbstract> commands;
    private final BotConfig botConfig;

    public TelegramBot(ClientService clientService, BotConfig botConfig) {
        super(botConfig.TG_BOT_TOKEN);
        this.botConfig = botConfig;
        commands = new ArrayList<>();
        commands.add(new StartCommand());
        commands.add(new PictureOfTheDayCommand(clientService));
        commands.add(new MarsCommand(clientService));
        commands.add(new HelpCommand(commands));
    }

    @Override
    public String getBotUsername() {
        return botConfig.TG_BOT_USERNAME;
    }

    @Override
    public String getBotPath() {
        return botConfig.WEBHOOK_URL;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            String cmd = update.getMessage().getText();

            // process user commands
            for (BotCommandAbstract command : commands) {

                if (cmd.equalsIgnoreCase(command.getCommand())) {
                    log.info("command processed: " + command.getCommand());
                    try {
                        return sendMessage(command.handle(update));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return null;
    }

    private SendMessage sendMessage(SendMessage msg) throws TelegramApiException {
        msg.setReplyMarkup((new ReplyKeyboardMenu()).getKeyboardMarkup());
        return msg;
    }

}
