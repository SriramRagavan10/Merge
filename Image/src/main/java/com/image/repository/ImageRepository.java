package com.image.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.image.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
