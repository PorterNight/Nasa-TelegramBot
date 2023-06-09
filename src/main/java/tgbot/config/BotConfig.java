package tgbot.config;

import lombok.extern.slf4j.Slf4j;
import java.util.stream.Stream;

@Slf4j
public class BotConfig {


    public static final String TG_BOT_USERNAME = System.getenv("NASA_TGBOT_USERNAME");
    public static final String TG_BOT_TOKEN = System.getenv("NASA_TGBOT_TOKEN");
    public static final String NASA_TOKEN = System.getenv("NASA_TOKEN");
    public static final String POD_URL = "https://api.nasa.gov/planetary/apod?count=1&api_key=" + NASA_TOKEN;
    private static final int MAX_SOL = 1000;  // mars max sol (earth day equivalent)

    private BotConfig() {
        assert Stream.of(
            TG_BOT_USERNAME,
            TG_BOT_TOKEN,
            NASA_TOKEN).anyMatch(env -> env != null && !env.isBlank());
    }

    public static String getMarsUrl() {
        int sol = (int) (Math.random() * MAX_SOL);
        return "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=" + sol + "&camera=fhaz&api_key="
            + NASA_TOKEN;
    }
}

