package tgbot.commands;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.util.List;
import static tgbot.commands.BotCommands.HELP;

@Slf4j
public class HelpCommand extends BotCommandAbstract {

    private final List<BotCommandAbstract> commands;

    public HelpCommand(List<BotCommandAbstract> commands) {
        super(HELP.getCommand(), HELP.getDescription());
        this.commands = commands;
    }

    public SendMessage handle(Update update) {
        SendMessage msg = new SendMessage();
        msg.setChatId(update.getMessage().getChatId());

        StringBuilder commandsText = new StringBuilder();
        for (BotCommandAbstract command : commands) {
            commandsText.append(command.getCommand()).append(": ").append(command.getDescription()).append("\n");
        }

        msg.setText(commandsText.toString());
        return msg;
    }
}
