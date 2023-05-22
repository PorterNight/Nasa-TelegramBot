package tgbot.commands;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import tgbot.config.BotConfig;
import tgbot.dto.NasaPictureOfADayObject;
import tgbot.service.HttpClientService;

@Slf4j
public class PodCommand extends BotCommand {

    public PodCommand(@NonNull String command, @NonNull String description) {
        super(command, description);
    }

    public SendMessage handle(Update update) {

        SendMessage msg = new SendMessage();
        msg.setChatId(update.getMessage().getChatId());

        log.info(BotConfig.POD_URL);

        try {
            NasaPictureOfADayObject[] obj = HttpClientService.getNasaObject(BotConfig.POD_URL);
            msg.setText(obj[0].getTitle() + " " + obj[0].getHdurl());
        } catch (Exception e) {
            log.warn("error getting objects !");
            throw new RuntimeException(e);
        }
        return msg;
    }

}
