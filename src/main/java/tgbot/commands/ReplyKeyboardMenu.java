package tgbot.commands;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import java.util.ArrayList;
import java.util.List;
import static tgbot.commands.BotCommands.HELP;
import static tgbot.commands.BotCommands.MARS;
import static tgbot.commands.BotCommands.POD;

public class ReplyKeyboardMenu {

    private final ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
    private final List<KeyboardRow> keyboard = new ArrayList<>();
    private KeyboardRow row;

    public ReplyKeyboardMarkup getKeyboardMarkup() {

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

        return keyboardMarkup;
    }
}
