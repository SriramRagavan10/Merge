package com.image1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/images")
public class ImageController {

	@Autowired
	ImageRepo repo;

	@PostMapping("/upload")
	public String upload(@RequestParam MultipartFile file) throws IOException {

		Image img = new Image();

		img.setData(file.getBytes());

		repo.save(img);

		return "Image Saved Successfully";
	}

	@GetMapping("get/{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
		Optional<Image> optionalImage = repo.findById(id);
		if (optionalImage.isPresent()) {
			Image image = optionalImage.get();
			return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image.getData());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

//	
//	Optional<Image> dbImg = imageRepository.findById(id);
//	byte[] imgs = ImageUtils.decompressImage(dbImg.get().getData());
//	return imgs;

}
