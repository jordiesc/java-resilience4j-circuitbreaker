package com.techprimers.circuitbreaker.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GenericServiceService {


    private RestTemplate restTemplate;

    // private final String REMOTE_API = "https://catfact.ninja/fact";
     private final String REMOTE_API = "http://localhost:8080/slow";

    public GenericServiceService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    @CircuitBreaker(name = "randomGenericService", fallbackMethod = "fallbackRandomGenericService")
    public String getRandomGenericService() throws Exception {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(REMOTE_API, String.class);
        String response = responseEntity.getBody();
        log.info("GenericService received: " + response);
        if (false)
            throw new Exception("mi error forzado");
        return response;
    }

    public String fallbackRandomGenericService(Throwable throwable) {
        return "Error catched in fallback"+ throwable.getMessage();
    }
    
}
