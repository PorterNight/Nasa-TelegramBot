package tgbot.config;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BotConfig {

    private BotConfig() {
    }

    public static final String TG_BOT_USERNAME = System.getenv("WNASA_TGBOT_USERNAME");
    public static final String TG_BOT_TOKEN = System.getenv("WNASA_TGBOT_TOKEN");
    public static final String NASA_TOKEN = System.getenv("WNASA_TOKEN");
    public static final String POD_URL = "https://api.nasa.gov/planetary/apod?count=1&api_key=" + NASA_TOKEN;
    public static final String MARS_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity" +
        "/photos?sol=1000&camera=fhaz&api_key=" + NASA_TOKEN;
}

