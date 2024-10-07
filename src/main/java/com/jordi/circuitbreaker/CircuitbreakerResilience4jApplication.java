package com.jordi.circuitbreaker;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.util.Timeout;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CircuitbreakerResilience4jApplication {

        public static void main(String[] args) {
                SpringApplication.run(CircuitbreakerResilience4jApplication.class, args);
        }

        @Bean
        public RestTemplate restTemplate() {

                // Create a SocketConfig with the desired timeout
                SocketConfig socketConfig = SocketConfig.custom()
                                .setSoTimeout(Timeout.ofSeconds(3)) // 3 seconds for socket timeout (read timeout)
                                .build();
                PoolingHttpClientConnectionManager connManager = PoolingHttpClientConnectionManagerBuilder.create()
                                .setDefaultSocketConfig(socketConfig)
                                .build();

                // Create a CloseableHttpClient with the custom SocketConfig
                CloseableHttpClient httpClient = HttpClients.custom()
                                .setConnectionManager(connManager)
                                .build();

                // Create a request factory with the custom HttpClient
                HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(
                                httpClient);
                requestFactory.setConnectTimeout(3000); // 3 seconds for connection establishment

                return new RestTemplate(requestFactory);

        }

}
