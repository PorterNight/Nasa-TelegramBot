package tgbot.commands;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import tgbot.config.BotConfig;
import tgbot.dto.mars.MarsPhotoResponse;
import tgbot.service.HttpClientService;
import static tgbot.config.BotConfig.getMarsUrl;

@Slf4j
public class MarsCommand extends BotCommandAbstract {

    public MarsCommand(@NonNull String command, @NonNull String description) {
        super(command, description);
    }

    public SendMessage handle(Update update) {

        SendMessage msg = new SendMessage();
        msg.setChatId(update.getMessage().getChatId());

        log.info(BotConfig.getMarsUrl());

        try {
            MarsPhotoResponse obj = HttpClientService.getMarsPhotos(getMarsUrl());
            msg.setText(obj.getPhotos().get(0).getEarthDate() + " " + obj.getPhotos().get(0).getImgSrc());
        } catch (Exception e) {
            log.warn("error getting objects !");
            throw new RuntimeException(e);
        }
        return msg;
    }
}
