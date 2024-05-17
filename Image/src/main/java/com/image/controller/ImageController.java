package com.image.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.image.entity.Image;
import com.image.service.ImageService;

import java.io.IOException;

@RestController
@RequestMapping("/images")
public class ImageController {

	@Autowired
	ImageService imageService;

	@PostMapping("/upload")
	public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
		String uploadImage = imageService.uploadImage(file);
		return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
	}

	@GetMapping("get/{id}")
	public ResponseEntity<?> getImage(@PathVariable Long id) {
		byte[] data = imageService.dowload(id);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(data);
	}
}
