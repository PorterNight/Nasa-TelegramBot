package tgbot.commands;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import static tgbot.commands.BotCommands.START;

@Slf4j
public class StartCommand extends BotCommandAbstract {

    public StartCommand() {
        super(START.getCommand(), START.getDescription());
    }

    @Override
    public SendMessage handle(Update update) {
        SendMessage msg = new SendMessage();
        msg.setChatId(update.getMessage().getChatId());
        msg.setText("Hello! I am bot that sends you foto from NASA archive, please choose category");
        return msg;
    }
}
