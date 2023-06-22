package tgbot.service;

import tgbot.dto.mars.MarsPhotoResponse;
import tgbot.dto.pod.NasaPictureOfTheDayObject;

public interface ClientService {

    NasaPictureOfTheDayObject[] getNasaPictureOfTheDayObject(String url) throws Exception;

    MarsPhotoResponse getMarsPhotos(String url) throws Exception;

}
