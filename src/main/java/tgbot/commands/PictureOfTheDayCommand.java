package tgbot.commands;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import tgbot.config.BotConfig;
import tgbot.dto.pod.NasaPictureOfTheDayObject;
import tgbot.service.ClientService;
import static tgbot.commands.BotCommands.POD;

@Slf4j
public class PictureOfTheDayCommand extends BotCommandAbstract {

    private final ClientService clientService;

    public PictureOfTheDayCommand(ClientService clientService) {
        super(POD.getCommand(), POD.getDescription());
        this.clientService = clientService;
    }

    public SendMessage handle(Update update) {

        SendMessage msg = new SendMessage();
        msg.setChatId(update.getMessage().getChatId());

        log.info(BotConfig.POD_URL);

        try {
            NasaPictureOfTheDayObject[] obj = clientService.getNasaPictureOfTheDayObject(BotConfig.POD_URL);
            msg.setText(obj[0].getTitle() + " " + obj[0].getHdurl());
        } catch (Exception e) {
            log.warn("error getting objects !");
            throw new RuntimeException(e);
        }
        return msg;
    }
}
