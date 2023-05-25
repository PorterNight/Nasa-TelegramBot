package tgbot.service;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import tgbot.dto.mars.MarsPhotoResponse;
import tgbot.dto.pod.NasaPictureOfADayObject;

@Slf4j
public class HttpClientService {

    private static final int CONNECT_TIMEOUT = 10000;
    private static final int SOCKET_TIMEOUT = 20000;

    private HttpClientService() {
    }

    private static final CloseableHttpClient HTTP_CLIENT = HttpClientBuilder.create()
        .setDefaultRequestConfig(RequestConfig.custom()
            .setConnectTimeout(CONNECT_TIMEOUT)
            .setSocketTimeout(SOCKET_TIMEOUT)
            .setRedirectsEnabled(false)
            .build())
        .build();

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static NasaPictureOfADayObject[] getNasaPictureOfADayObject(String url) throws Exception {

        CloseableHttpResponse httpResponse = HTTP_CLIENT.execute(new HttpGet(url));
        NasaPictureOfADayObject[] nasaPictureOfADayObject = null;

        try {
            nasaPictureOfADayObject = OBJECT_MAPPER.readValue(httpResponse.getEntity().getContent(),
                NasaPictureOfADayObject[].class);
        } catch (StreamReadException e) {
            log.warn("NasaPictureOfADayObject StreamReadException !");
        } catch (DatabindException e) {
            log.warn("NasaPictureOfADayObject DatabindException !");
        }

        return nasaPictureOfADayObject;
    }

    public static MarsPhotoResponse getMarsPhotos(String url) throws Exception {
        CloseableHttpResponse httpResponse = HTTP_CLIENT.execute(new HttpGet(url));
        MarsPhotoResponse marsPhotoResponse = null;

        try {
            marsPhotoResponse = OBJECT_MAPPER.readValue(httpResponse.getEntity().getContent(),
                MarsPhotoResponse.class);
        } catch (StreamReadException e) {
            log.warn("MarsPhotoResponse StreamReadException !");
        } catch (DatabindException e) {
            log.warn("MarsPhotoResponse DatabindException !");
        }

        return marsPhotoResponse;
    }
}
