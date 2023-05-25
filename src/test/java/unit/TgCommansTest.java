package unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import tgbot.commands.MarsCommand;
import tgbot.commands.PodCommand;
import tgbot.dto.mars.Camera;
import tgbot.dto.mars.MarsPhoto;
import tgbot.dto.mars.MarsPhotoResponse;
import tgbot.dto.mars.Rover;
import tgbot.dto.pod.NasaPictureOfADayObject;
import tgbot.service.HttpClientService;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static tgbot.commands.BotCommands.MARS;
import static tgbot.commands.BotCommands.POD;
import org.mockito.MockedStatic;

@ExtendWith(MockitoExtension.class)
public class TgCommansTest {

    @Mock
    private Update update;

    @Mock
    private Message message;

    @InjectMocks
    private MarsCommand marsCommand = new MarsCommand(MARS.getCommand(), MARS.getDescription());

    @InjectMocks
    private PodCommand podCommand = new PodCommand(POD.getCommand(), POD.getDescription());


    @Test
    public void MarsCommandTest (){
        try (MockedStatic<HttpClientService> httpClientServiceMock = Mockito.mockStatic(HttpClientService.class)) {

            // arrange
            long chatId = 123L;
            List<MarsPhoto> marsPhotos = new ArrayList<>();

            marsPhotos.add(new MarsPhoto(111,199, new Camera(1,"cam", 123, "curiosity"), "https://mars.com", "2024", new Rover(24,"rover","2020", "2020", "dd")));

            MarsPhotoResponse marsPhotoResponse = new MarsPhotoResponse(marsPhotos);

            when(update.getMessage()).thenReturn(message);
            when(update.getMessage().getChatId()).thenReturn(chatId);

            httpClientServiceMock.when(() -> HttpClientService.getMarsPhotos(anyString())).thenReturn(marsPhotoResponse); // mock to return the response for any non-null string

            // act
            SendMessage result = marsCommand.handle(update);

            // assert
            assertEquals("https://mars.com", result.getText().split(" ")[1]);
        }
    }

    @Test
    public void PodCommandTest() {
        try (MockedStatic<HttpClientService> httpClientServiceMock = Mockito.mockStatic(HttpClientService.class)) {

            // arrange
            long chatId = 123L;
            NasaPictureOfADayObject[] podObjects = new NasaPictureOfADayObject[1];
            podObjects[0] = new NasaPictureOfADayObject("copyright", "date", "explanation", "https://nasa.com", "mediaType", "serviceVersion", "title", "url");

            when(update.getMessage()).thenReturn(message);
            when(update.getMessage().getChatId()).thenReturn(chatId);

            httpClientServiceMock.when(() -> HttpClientService.getNasaPictureOfADayObject(anyString())).thenReturn(podObjects); // mock to return the response for any non-null string

            // act
            SendMessage result = podCommand.handle(update);

            // assert
            assertEquals("title https://nasa.com", result.getText());
        }
    }

}


