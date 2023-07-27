package tgbot.commands;

public enum BotCommands {
    START("/start", " - command to register new user"),
    HELP("/help", " - command is used to get help"),
    POD("/picture of the day", " - command is used to get random picture of the day"),
    MARS("/mars", " - command is used to get random picture from Mars");

    private final String command;
    private final String description;

    BotCommands(String command, String description) {
        this.command = command;
        this.description = description;
    }

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }
}
