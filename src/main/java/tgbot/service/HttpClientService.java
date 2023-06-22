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
import tgbot.dto.pod.NasaPictureOfTheDayObject;

@Slf4j
public class HttpClientService implements ClientService {

    private final int connectTimeout = 10000;
    private final int socketTimeout = 20000;

    public HttpClientService() {
    }

    private final CloseableHttpClient httpClient = HttpClientBuilder.create()
        .setDefaultRequestConfig(RequestConfig.custom()
            .setConnectTimeout(connectTimeout)
            .setSocketTimeout(socketTimeout)
            .setRedirectsEnabled(false)
            .build())
        .build();

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public NasaPictureOfTheDayObject[] getNasaPictureOfTheDayObject(String url) throws Exception {

        CloseableHttpResponse httpResponse = httpClient.execute(new HttpGet(url));
        NasaPictureOfTheDayObject[] nasaPictureOfADayObject = null;

        try {
            nasaPictureOfADayObject = objectMapper.readValue(httpResponse.getEntity().getContent(),
                NasaPictureOfTheDayObject[].class);
        } catch (StreamReadException e) {
            log.warn("NasaPictureOfADayObject StreamReadException !");
        } catch (DatabindException e) {
            log.warn("NasaPictureOfADayObject DatabindException !");
        }

        return nasaPictureOfADayObject;
    }

    @Override
    public MarsPhotoResponse getMarsPhotos(String url) throws Exception {
        CloseableHttpResponse httpResponse = httpClient.execute(new HttpGet(url));
        MarsPhotoResponse marsPhotoResponse = null;

        try {
            marsPhotoResponse = objectMapper.readValue(httpResponse.getEntity().getContent(),
                MarsPhotoResponse.class);
        } catch (StreamReadException e) {
            log.warn("MarsPhotoResponse StreamReadException !");
        } catch (DatabindException e) {
            log.warn("MarsPhotoResponse DatabindException !");
        }

        return marsPhotoResponse;
    }
}
