package com.f5.taskf5.services;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.f5.taskf5.entities.Image;
import com.f5.taskf5.model.EditImage;
import com.f5.taskf5.model.NewImage;
import com.f5.taskf5.repositories.ImageRepository;



@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public List<Image> getImagesByUserId(String userId) {
        return imageRepository.findByUserId(userId);
    }
    
    @Override
    public boolean deleteImage(String id, String userId) {
        Optional<Image> image = imageRepository.findByIdAndUserId(Long.parseLong(id), userId);
        if (image.isPresent()) {
            imageRepository.delete(image.get());
            return true;
        }
        return false;
    }
    @Override
    public Image addImage(String userId, NewImage newImage) {
        Image image = new Image(newImage.getTitle(), newImage.getUrl());
        image.setUserId(userId);
        return imageRepository.save(image);
    }
    
    @Override
    public Image editImage(String id, String userId, EditImage editImage) {
        Optional<Image> optionalImage = imageRepository.findByIdAndUserId(Long.parseLong(id), userId);
        if (optionalImage.isPresent()) {
            Image image = optionalImage.get();
            image.setTitle(editImage.getTitle());
            image.setUrl(editImage.getUrl());
            return imageRepository.save(image);
        }
        return null;
    }
    
    
    
    
}