package com.hv.iot.configuration;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * <p>Title: RestTemplateConfig</p>
 * <p>Description: TODO</p>
 * <p>Company: www.h-visions.com</p>
 * <p>create date: 2021/03/11</p>
 *
 * @author :jqzhang
 * @version :1.0.0
 */
@Configuration
public class RestTemplateConfig {


    private final HttpClientProperties httpClientProperties;

    public RestTemplateConfig(HttpClientProperties httpClientProperties) {
        this.httpClientProperties = httpClientProperties;
    }


    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate(factory);
        ResponseErrorHandler responseErrorHandler = new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                //只需要将hasError方法的返回值更改为true就可以了，以后不管状态码是200还是其它的都会返回结果
                return true;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
            }
        };
        restTemplate.setErrorHandler(responseErrorHandler);
        return restTemplate;
    }


    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory(httpClient());
    }


    @Bean
    public HttpClient httpClient() {
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
        connectionManager.setMaxTotal(httpClientProperties.getMaxTotal());
        connectionManager.setDefaultMaxPerRoute(httpClientProperties.getDefaultMaxPerRoute());
        connectionManager.setValidateAfterInactivity(httpClientProperties.getValidateAfterInactivity());
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(httpClientProperties.getSocketTimeout())
                .setConnectTimeout(httpClientProperties.getConnectTimeout())
                .setConnectionRequestTimeout(httpClientProperties.getConnectionRequestTimeout())
                .build();
        return HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
                .build();
    }
}
