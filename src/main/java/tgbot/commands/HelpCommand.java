package tgbot.commands;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import java.util.List;

@Slf4j
public class HelpCommand extends BotCommand implements CommandHandler {

    private final List<CommandHandler> commandHandlers;

    public HelpCommand(@NonNull String command, @NonNull String description, List<CommandHandler> commandHandlers) {
        super(command, description);
        this.commandHandlers = commandHandlers;
    }

    public SendMessage handle(Update update) {
        SendMessage msg = new SendMessage();
        msg.setChatId(update.getMessage().getChatId());

        StringBuilder commandsText = new StringBuilder();
        for (CommandHandler commandHandler : commandHandlers) {
            BotCommand command = (BotCommand) commandHandler;
            commandsText.append(command.getCommand()).append(": ").append(command.getDescription()).append("\n");
        }

        msg.setText(commandsText.toString());
        return msg;
    }
}
