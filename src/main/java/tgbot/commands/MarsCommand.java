package tgbot.commands;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import tgbot.config.BotConfig;
import tgbot.dto.mars.MarsPhotoResponse;
import tgbot.service.HttpClientService;

@Slf4j
public class MarsCommand extends BotCommand implements CommandHandler {

    public MarsCommand(@NonNull String command, @NonNull String description) {
        super(command, description);
    }

    public SendMessage handle(Update update) {

        SendMessage msg = new SendMessage();
        msg.setChatId(update.getMessage().getChatId());

        log.info(BotConfig.MARS_URL);

        try {
            MarsPhotoResponse obj = HttpClientService.getMarsPhotos(BotConfig.MARS_URL);
            msg.setText(obj.getPhotos().get(0).getEarthDate() + " " + obj.getPhotos().get(0).getImgSrc());
        } catch (Exception e) {
            log.warn("error getting objects !");
            throw new RuntimeException(e);
        }
        return msg;
    }
}
