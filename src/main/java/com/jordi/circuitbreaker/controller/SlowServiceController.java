package com.jordi.circuitbreaker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/slow")
@RestController
public class SlowServiceController {

    private static int count = 0;


    @GetMapping
    public String getRandomSlowService() throws Exception {
        // this method take long time to execute from 3 times
        count++;
        if (count >= 3) {
            Thread.sleep(5000);
        }
        return "slowService is called " + count + " times";

    }
    
}
