package tgbot.commands;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
public class StartCommand extends BotCommandAbstract {

    public StartCommand(@NonNull String command, @NonNull String description) {
        super(command, description);
    }

    @Override
    public SendMessage handle(Update update) {
        SendMessage msg = new SendMessage();
        msg.setChatId(update.getMessage().getChatId());
        msg.setText("Hello! I am a bot that sends you foto from NASA archive, please choose category");
        return msg;
    }
}
