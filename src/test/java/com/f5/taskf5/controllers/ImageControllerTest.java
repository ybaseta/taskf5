package com.f5.taskf5.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.f5.taskf5.entities.Image;
import com.f5.taskf5.model.EditImage;
import com.f5.taskf5.services.ImageService;

//this act as a unit test for the controller as we will mock the dependencies

@WebMvcTest(ImageController.class)
//this is a test slice only the bean controller is available
//tomcat will not run, bd will not run and bean services won´t be available
@Import(TestControllerConfig.class)


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
        
        //configuring mock behavior
        Mockito
          .when(imageService.getImagesByUserId("userTest1"))
          .thenReturn(Arrays.asList(image1, image2));
        Mockito
          .when(imageService.deleteImage("1","userTestValid"))
          .thenReturn(true);
        Mockito
          .when(imageService.deleteImage("2","userTestNonValid"))
          .thenReturn(false);
        
        Mockito        
          .when(imageService.editImage(eq("3"), eq("userTest2"), any(EditImage.class)))
          .thenReturn(new Image(3L, "updatedTitle", "updatedUrl", "userTest2"));
        Mockito        
        .when(imageService.editImage(eq("3"), eq("userTestNonValid"), any(EditImage.class)))
        .thenReturn(null);
        
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
	void getImagesWithoutuserId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/images"))
		       .andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}     
	
	@Test
	//happy path
	@DisplayName("WHEN a DELETE /image request with valid userId and image id"
	        +"    THEN it should respond with 200 http status")
	void deleteImage() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/images/1?userId=userTestValid"))
		       .andExpect(MockMvcResultMatchers.status().isOk());
		       
	}
	
	@Test
	//not happy path
	@DisplayName("WHEN a DELETE /image request with a non valid userId and image id"
	        +"    THEN it should respond with 404 http status")
	void deleteImageNotValidUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/images/2?userId=userTestNonValid"))
		       .andExpect(MockMvcResultMatchers.status().isNotFound());
		       
	}
	
	@Test
	@DisplayName("WHEN a ADD /image request with an userId and image info"
	        +"    THEN it should respond with 201 http status")
	void addImage() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/images?userId=userTest")
		       .contentType(MediaType.APPLICATION_JSON)
               .content("{\"title\": \"someTitle\", \"url\": \"someUrl\"}"))
		       .andExpect(MockMvcResultMatchers.status().isCreated());
		       
	}
	
	/* Note: here i use a diferent way to indicate PathParam and ParamVariable
	 * i do it for educational purposes
	 * in an production app it is important not to do that, so this will also serves as an example of a bad practice 
	 */
	
	@Test
	//happy path
	@DisplayName("WHEN a PUT /images/{id} request with valid id, userId, and editImage payload is made"
	        + "    THEN it should respond with 200 http status and return the updated image")
	void editImage() throws Exception {
		 mockMvc.perform(MockMvcRequestBuilders.put("/images/{id}", 3)
		            .param("userId", "userTest2")
		            .contentType(MediaType.APPLICATION_JSON)
		            .content("{\"title\": \"updatedTitle\", \"url\": \"someUrl\"}"))
		           .andExpect(MockMvcResultMatchers.status().isOk())
		           .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("updatedTitle"))
		           .andExpect(MockMvcResultMatchers.jsonPath("$.url").value("updatedUrl"));
		}
	
	@Test
	//not happy path
	@DisplayName("WHEN a PUT /images/{id} request with a non valid  userId,  is made"
	        + "    THEN it should respond with 404 http status ")
	void editImageNonValid() throws Exception {
		 mockMvc.perform(MockMvcRequestBuilders.put("/images/{id}", 3)
		            .param("userId", "userTestNonValid")
		            .contentType(MediaType.APPLICATION_JSON)
		            .content("{\"title\": \"updatedTitle\", \"url\": \"someUrl\"}"))
		            .andExpect(MockMvcResultMatchers.status().isNotFound());
		           
		}


}
	
	
