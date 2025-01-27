package com.f5.taskf5.controllers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.f5.taskf5.entities.Image;
import com.f5.taskf5.services.ImageService;

//this act as a unit test for the controller as we will mock the dependencies

@WebMvcTest(ImageController.class)
//this is a test slice only the bean controller is available
//tomcat will not run, bd will not run and bean services won´t be available
@Import(TestConfig.class)


class ImageControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ImageService imageService;
	
	private ImageController imageController;
	
	@BeforeEach
    void setUp() {       

        // Create the controller and inject the mock
         imageController = new ImageController(imageService);

        //Create test data
        Image image1= new Image(1L,"title test1", "urltest1", "userTest1");
        Image image2= new Image(2L,"title test2", "urltest2", "userTest1");
        Image image3= new Image(3L,"title test3", "urltest3", "userTest2");
        Image image4= new Image(4L,"title test4", "urltest4", "userTest3");
        
        //configuring mock behaviour
        Mockito
          .when(imageService.getImagesByUserId("userTest1"))
          .thenReturn(Arrays.asList(image1, image2));
    }
	

	@Test
	//happy path
	@DisplayName("WHEN a GET /image request with a userId"
	        +"    THEN it should respond with 200 http status and a JSON list with info of that user images")
	void getImages() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/images?userId=userTest1"))
		       .andExpect(MockMvcResultMatchers.status().isOk())
		       .andExpect(MockMvcResultMatchers.jsonPath("@.[1].url").value("urltest2"));
	}
	
	@Test
	//not happy path
	@DisplayName("WHEN a GET /image request without a userId"
	        +"    THEN it should respond with 4xx http status ")
	void getImagesWitoutuserId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/images"))
		       .andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}       

}
	
	
