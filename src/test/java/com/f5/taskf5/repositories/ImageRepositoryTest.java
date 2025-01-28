package com.f5.taskf5.repositories;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import com.f5.taskf5.entities.Image;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/*
When you use @DataJpaTest for repository testing,
 an in-memory database like H2 is used by default.
This means that any data inserted during the test exists only for the duration of the test
and is cleared afterward.
*/

//just tried the two methods created by convention
@DataJpaTest
@Transactional
class ImageRepositoryTest {

    @Autowired
    private ImageRepository imageRepository;
    
    @BeforeEach
    void setUp() {
        imageRepository.deleteAll(); // Clears all data
    }    
    
    @Test
    @DisplayName("WHEN the method is called with a userID"
            +"    THEN it should return a list with the images of the given user ")
    void testFindByUserId() {
        // Arrange
        Image image1 = new Image("Title1", "URL1");
        image1.setUserId("user123");
        Image image2 = new Image("Title2", "URL2");
        image2.setUserId("user123");
        Image image3 = new Image("Title3", "URL3");
        image3.setUserId("user456");

        imageRepository.save(image1);
        imageRepository.save(image2);
        imageRepository.save(image3);

        // Act
        List<Image> images = imageRepository.findByUserId("user123");

        // Assert
        assertThat(images).hasSize(2);
        assertThat(images.get(0).getTitle()).isEqualTo("Title1");
        assertThat(images.get(1).getTitle()).isEqualTo("Title2");
    }
    
	@Test
	@DisplayName("WHEN the method is called with valids userID and imageId"
            +"    THEN it should return an Optional with the image found")
    void testFindByIdAndUserId() {
        // Arrange
        Image image = new Image("Title", "URL");
        image.setUserId("user123");
        Image savedImage = imageRepository.save(image);

        // Act
        Optional<Image> foundImage = imageRepository.findByIdAndUserId(savedImage.getId(), "user123");

        // Assert
        assertThat(foundImage).isPresent();
        assertThat(foundImage.get().getTitle()).isEqualTo("Title");
        assertThat(foundImage.get().getUrl()).isEqualTo("URL");
    }
    
    
    
}
