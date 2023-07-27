package tgbot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@RestController
@RequestMapping()
public class BotController {

    private final TelegramBot bot;

    public BotController(TelegramBot bot) {
        this.bot = bot;
    }

    @PostMapping("/")
    public BotApiMethod<?> webHook(@RequestBody Update update) {
        return bot.onWebhookUpdateReceived(update);
    }
}
