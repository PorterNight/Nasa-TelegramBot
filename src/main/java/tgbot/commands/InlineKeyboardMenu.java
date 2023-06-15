package tgbot.commands;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import java.util.ArrayList;
import java.util.List;

import static tgbot.commands.BotCommands.*;

public class InlineKeyboardMenu {

    private final InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

    private final List<InlineKeyboardButton> row1 = new ArrayList<>();
    private final List<InlineKeyboardButton> row2 = new ArrayList<>();
    private final List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

    public InlineKeyboardMarkup getKeyboardMarkup() {

        row1.add(new InlineKeyboardButton(POD.getCommand()));
        row1.add(new InlineKeyboardButton(MARS.getCommand()));
        row2.add(new InlineKeyboardButton(HELP.getCommand()));

        rowList.add(row1);
        rowList.add(row2);
        inlineKeyboardMarkup.setKeyboard(rowList);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }
}
