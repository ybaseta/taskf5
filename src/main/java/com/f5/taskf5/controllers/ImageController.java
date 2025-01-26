package com.f5.taskf5.controllers;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.f5.taskf5.entities.Image;
import com.f5.taskf5.model.EditImage;
import com.f5.taskf5.model.NewImage;
import com.f5.taskf5.services.ImageService;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    //List of images given a userId
    @GetMapping
    public ResponseEntity<List<Image>> getImages(@RequestParam String userId) {
        List<Image> images = imageService.getImagesByUserId(userId);
        return ResponseEntity.ok(images);
    }  
    
  
     //Delete an image given its id and a userId
     @DeleteMapping("/{id}")
     public ResponseEntity<Void> deleteImage(@PathVariable String id, @RequestParam String userId) {
        boolean isDeleted = imageService.deleteImage(id, userId);
        if (isDeleted) {
          return ResponseEntity.ok().build();
        } else {
        return ResponseEntity.notFound().build();
        }
     }
     
     //Add new image given an userId and new image info
     @PostMapping
     public ResponseEntity<Image> addImage(@RequestParam String userId, @RequestBody NewImage newImage) {
         Image createdImage = imageService.addImage(userId, newImage);
         return ResponseEntity.status(201).body(createdImage);
     }
     
     //edit an existing image
     @PutMapping("/{id}")
     public ResponseEntity<Image> editImage(
             @PathVariable String id,
             @RequestParam String userId,
             @RequestBody EditImage editImage) {
         Image updatedImage = imageService.editImage(id, userId, editImage);
         if (updatedImage != null) {
             return ResponseEntity.ok(updatedImage);
         } else {
             return ResponseEntity.notFound().build();
         }
     }


}   