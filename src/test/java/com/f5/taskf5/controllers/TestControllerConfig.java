package com.f5.taskf5.controllers;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.f5.taskf5.services.ImageService;

//alternative to create the mocks other than using @MockBean which is been deprecated

@Configuration
public class TestControllerConfig {
	
	 @Bean
	    public ImageService imageService() {
	        return Mockito.mock(ImageService.class);
   
    }
}
