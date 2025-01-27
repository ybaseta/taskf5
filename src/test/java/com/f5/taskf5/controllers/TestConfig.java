package com.f5.taskf5.controllers;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.f5.taskf5.services.ImageService;

//best alternative to create the mocks than using @MockBean which is deprecated

@Configuration
public class TestConfig {

    @Bean
    public ImageService imageService() {
        return Mockito.mock(ImageService.class);
    }
}
