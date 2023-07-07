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
import org.springframework.stereotype.Component;
import tgbot.dto.mars.MarsPhotoResponse;
import tgbot.dto.pod.NasaPictureOfTheDayObject;
import java.io.IOException;

@Slf4j
@Component
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
    public MarsPhotoResponse getMarsPhotos(String url) throws IOException {

        CloseableHttpResponse httpResponse = null;

        try {
            httpResponse = httpClient.execute(new HttpGet(url));
        } catch (IOException e){
            log.warn("MarsPhotoResponse IOexception !");
        }

        MarsPhotoResponse marsPhotoResponse = null;
        log.warn("http response: " + httpResponse.getEntity().getContent().toString());

        try {
            assert httpResponse != null;
            marsPhotoResponse = objectMapper.readValue(httpResponse.getEntity().getContent(),
                MarsPhotoResponse.class);
            log.warn("marsfoto mapping: " + marsPhotoResponse.toString());
        } catch (StreamReadException e) {
            log.warn("MarsPhotoResponse StreamReadException !");
        } catch (DatabindException e) {
            log.warn("MarsPhotoResponse DatabindException !");
        }

        return marsPhotoResponse;
    }
}
