package com.pwc.complaint.complaint;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TestConfig {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}