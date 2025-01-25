package com.f5.taskf5.services;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.f5.taskf5.entities.Image;
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
}