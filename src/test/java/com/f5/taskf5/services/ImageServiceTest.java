package com.f5.taskf5.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.f5.taskf5.entities.Image;
import com.f5.taskf5.model.EditImage;
import com.f5.taskf5.model.NewImage;
import com.f5.taskf5.repositories.ImageRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
	
//here bussiness logic will be tested	
public class ImageServiceTest {

  private ImageRepository imageRepository;
  private ImageService imageService;

  @BeforeEach
  void setUp() {
	  // Manually mock the repository
	  imageRepository = Mockito.mock(ImageRepository.class);
	  // Inject the mocked repository into the service
	  imageService = new ImageServiceImpl(imageRepository);
  }

    @Test
    @DisplayName("WHEN the method is called with a userID"
            +"    THEN it should return a list with the images of the given user ")
    void testGetImagesByUserId() {
	    // Arrange
	    String userId = "user123";
	    Image image1 = new Image("Title1", "URL1");
	    image1.setUserId(userId);
	    Image image2 = new Image("Title2", "URL2");
	    image2.setUserId(userId);
	
	    when(imageRepository.findByUserId(userId)).thenReturn(Arrays.asList(image1, image2));

        // Act
        List<Image> images = imageService.getImagesByUserId(userId);

        // Assert
        assertThat(images).hasSize(2);
        assertThat(images.get(0).getTitle()).isEqualTo("Title1");
        assertThat(images.get(1).getTitle()).isEqualTo("Title2");
    }
    
    @Test
    @DisplayName("WHEN the method is called with an userID and a new image info"
            +"    THEN it should create the image using imageRepository")
    void testAddImage() {
        // Arrange
        String userId = "user123";
        NewImage newImage = new NewImage();
        newImage.setTitle("New Title");
        newImage.setUrl("http://example.com/new-image.jpg");

        Image savedImage = new Image("New Title", "http://example.com/new-image.jpg");
        savedImage.setUserId(userId);

        when(imageRepository.save(any(Image.class))).thenReturn(savedImage);

        // Act
        Image result = imageService.addImage(userId, newImage);

        // Assert
        assertThat(result.getTitle()).isEqualTo("New Title");
        assertThat(result.getUrl()).isEqualTo("http://example.com/new-image.jpg");
        assertThat(result.getUserId()).isEqualTo(userId);

        verify(imageRepository, times(1)).save(any(Image.class));
    }
    
    @Test
    @DisplayName("WHEN the method is called with valids userID and imageId"
            +"    THEN it should delete the image using imageRepository and return true ")
    void testDeleteImage() {
        // Arrange
        String userId = "user123";
        String imageId = "1";
        Image image = new Image("Title", "URL");
        image.setUserId(userId);

        when(imageRepository.findByIdAndUserId(Long.parseLong(imageId), userId)).thenReturn(Optional.of(image));

        // Act
        boolean isDeleted = imageService.deleteImage(imageId, userId);

        // Assert
        assertThat(isDeleted).isTrue();
        verify(imageRepository, times(1)).delete(image);
    }
    
    @Test
    @DisplayName("WHEN the method is called with non valids userID or imageId"
            +"    THEN it should  return false ")
    void testDeleteImageInvalid() {
        // Arrange
        String userId = "user123";
        String imageId = "1";       

        when(imageRepository.findByIdAndUserId(Long.parseLong(imageId), userId)).thenReturn(Optional.empty());

        // Act
        boolean isDeleted = imageService.deleteImage(imageId, userId);

        // Assert
        assertThat(isDeleted).isFalse();
        
    }

    @Test
    @DisplayName("WHEN the method is called with valids userID and imageId and a editImage"
            +"    THEN it should edit the image with the info provided and return the info of the updatedImage")
    void testEditImage() {
        // Arrange
        String userId = "user123";
        String imageId = "1";

        EditImage editImage = new EditImage();
        editImage.setTitle("Updated Title");
        editImage.setUrl("http://example.com/updated.jpg");

        Image existingImage = new Image("Old Title", "http://example.com/old.jpg");
        existingImage.setUserId(userId);

        when(imageRepository.findByIdAndUserId(Long.parseLong(imageId), userId)).thenReturn(Optional.of(existingImage));
        when(imageRepository.save(any(Image.class))).thenReturn(existingImage);

        // Act
        Image updatedImage = imageService.editImage(imageId, userId, editImage);

        // Assert
        assertThat(updatedImage.getTitle()).isEqualTo("Updated Title");
        assertThat(updatedImage.getUrl()).isEqualTo("http://example.com/updated.jpg");

        verify(imageRepository, times(1)).findByIdAndUserId(Long.parseLong(imageId), userId);
        verify(imageRepository, times(1)).save(existingImage);
    }    
}
