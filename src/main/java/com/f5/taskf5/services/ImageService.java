package com.f5.taskf5.services;



import java.util.List;

import com.f5.taskf5.entities.Image;
import com.f5.taskf5.model.EditImage;
import com.f5.taskf5.model.NewImage;



public interface ImageService {

	List<Image> getImagesByUserId(String userId);

	boolean deleteImage(String id, String userId);

	Image addImage(String userId, NewImage newImage);

	Image editImage(String id, String userId, EditImage editImage);

}
