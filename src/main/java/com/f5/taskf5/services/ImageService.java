package com.f5.taskf5.services;



import java.util.List;

import com.f5.taskf5.entities.Image;



public interface ImageService {

	List<Image> getImagesByUserId(String userId);

	boolean deleteImage(String id, String userId);

}
