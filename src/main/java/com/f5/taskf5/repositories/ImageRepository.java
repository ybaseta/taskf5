package com.f5.taskf5.repositories;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.f5.taskf5.entities.Image;



@Repository
public interface ImageRepository extends JpaRepository<Image, Integer>{

	
	List<Image> findByUserId(String userId);
	Optional<Image> findByIdAndUserId(Long id, String userId);
}
