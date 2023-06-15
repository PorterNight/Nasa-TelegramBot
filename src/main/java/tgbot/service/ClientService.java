package tgbot.service;

import tgbot.dto.mars.MarsPhotoResponse;
import tgbot.dto.pod.NasaPictureOfADayObject;

public interface ClientService {

    NasaPictureOfADayObject[] getNasaPictureOfADayObject(String url) throws Exception;

    MarsPhotoResponse getMarsPhotos(String url) throws Exception;

}
