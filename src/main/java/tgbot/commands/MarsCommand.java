package tgbot.commands;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import tgbot.config.BotConfig;
import tgbot.dto.mars.MarsPhotoResponse;
import tgbot.service.ClientService;
import java.io.IOException;
import static tgbot.commands.BotCommands.MARS;
import static tgbot.config.BotConfig.getMarsUrl;

@Slf4j
public class MarsCommand extends BotCommandAbstract {

    private final ClientService clientService;

    public MarsCommand(ClientService clientService) {
        super(MARS.getCommand(), MARS.getDescription());
        this.clientService = clientService;
    }

    public SendMessage handle(Update update) {

        SendMessage msg = new SendMessage();
        msg.setChatId(update.getMessage().getChatId());

        log.info(getMarsUrl());

        try {
            MarsPhotoResponse obj = clientService.getMarsPhotos(getMarsUrl());
            msg.setText(obj.getPhotos().get(0).getEarthDate() + " " + obj.getPhotos().get(0).getImgSrc());
        } catch (Exception e) {
            log.warn("error getting objects !");
            msg.setText("");
            //throw new RuntimeException(e);
        }
        return msg;
    }
}
