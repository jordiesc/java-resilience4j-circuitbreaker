package com.techprimers.circuitbreaker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techprimers.circuitbreaker.service.GenericServiceService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/service")
@RestController
public class GenericServiceController {

    
    private GenericServiceService genericServiceService;

    public GenericServiceController(GenericServiceService genericServiceService) {
        this.genericServiceService = genericServiceService;
    }

    @GetMapping
  
    public String getRandomGenericService() throws Exception {
        return genericServiceService.getRandomGenericService();
    }


}

