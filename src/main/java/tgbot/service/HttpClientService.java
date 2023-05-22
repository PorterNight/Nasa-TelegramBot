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
import tgbot.dto.NasaPictureOfADayObject;

@Slf4j
public class HttpClientService {

    private static final int CONNECT_TIMEOUT = 3000;
    private static final int SOCKET_TIMEOUT = 10000;

    private HttpClientService() {
    }

    private static CloseableHttpClient httpClient = HttpClientBuilder.create()
        .setDefaultRequestConfig(RequestConfig.custom()
            .setConnectTimeout(CONNECT_TIMEOUT)
            .setSocketTimeout(SOCKET_TIMEOUT)
            .setRedirectsEnabled(false)
            .build())
        .build();

    private static ObjectMapper objectMapper = new ObjectMapper();


    public static NasaPictureOfADayObject[] getNasaObject(String url) throws Exception {

        CloseableHttpResponse httpResponse = httpClient.execute(new HttpGet(url));
        NasaPictureOfADayObject[] nasaObjects = null;

        try {
            nasaObjects = objectMapper.readValue(httpResponse.getEntity().getContent(),
                NasaPictureOfADayObject[].class);
        } catch (StreamReadException e) {
            log.warn("StreamReadException !");
        } catch (DatabindException e) {
            log.warn("DatabindException !");
        }

        return nasaObjects;
    }
}
