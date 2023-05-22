package tgbot.commands;

import lombok.NonNull;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

public abstract class BotCommandAbstract extends BotCommand {

    public BotCommandAbstract(@NonNull String command, @NonNull String description) {
        super(command, description);
    }

    public SendMessage handle(Update update) {
        return null;
    }

}
